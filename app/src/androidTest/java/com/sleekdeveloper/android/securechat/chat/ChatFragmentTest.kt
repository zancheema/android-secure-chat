package com.sleekdeveloper.android.securechat.chat

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.sleekdeveloper.android.securechat.R
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import com.sleekdeveloper.android.securechat.data.source.domain.ChatMessage
import com.sleekdeveloper.android.securechat.data.source.domain.UserDetail
import com.sleekdeveloper.android.securechat.data.source.saveMessageBlocking
import com.sleekdeveloper.android.securechat.data.source.saveUserDetailBlocking
import com.sleekdeveloper.android.securechat.di.AppRepositoryModule
import com.sleekdeveloper.android.securechat.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@MediumTest
@RunWith(AndroidJUnit4::class)
@UninstallModules(AppRepositoryModule::class)
@HiltAndroidTest
class ChatFragmentTest {

    private val me = UserDetail("12345674389", "Jane", "Doe", msgToken = "other_token")
    private val otherUser = UserDetail("12345676347", "John", "Doe", msgToken = "other_token")
    private val unrelatedUser =
        UserDetail("12345656381", "Dwayne", "Johnson", msgToken = "other_token")
    private val msg1 = ChatMessage("Hey!", otherUser, me)
    private val msg2 = ChatMessage("Hi!", me, otherUser, isMine = true)
    private val msg3 = ChatMessage("Lets grab some coffee", otherUser, me)
    private val msg4 = ChatMessage("Alright", me, otherUser, isMine = true)
    private val msg5 = ChatMessage("Great!", otherUser, me)
    private val msg6 = ChatMessage("Hello!", unrelatedUser, me)
    private val allMessages = listOf(
        msg1, msg2, msg3, msg4, msg5, msg6
    )
    private val relatedMessages = listOf(
        msg1, msg2, msg3, msg4, msg5
    )
    private val unrelatedMessages = listOf(msg6)

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: AppRepository

    @Before
    fun init() {
        hiltRule.inject()
        repository.saveUserDetailBlocking(me)
        allMessages.forEach {
            repository.saveMessageBlocking(it)
        }
    }

    @Test
    fun openingChatWithNoUser_ShowsNoChatAndDoesNotThrowAnyException() {
        val bundle = Bundle()
        bundle.putParcelable("otherUserDetail", otherUser)
        launchFragmentInHiltContainer<ChatFragment>(bundle, R.style.Theme_SecureChat)
    }

    @Test
    fun messageListShowsChatMessagesWithOtherSingleUser() {
        val bundle = Bundle()
        bundle.putParcelable("otherUserDetail", otherUser)
        launchFragmentInHiltContainer<ChatFragment>(bundle, R.style.Theme_SecureChat)

        for (m in relatedMessages) {
            onView(withText(m.message)).check(matches(isDisplayed()))
        }
        for (m in unrelatedMessages) {
            onView(withText(m.message)).check(doesNotExist())
        }
    }

    @Test
    fun sendBlankMessage_MessageNotSent() {
        val bundle = Bundle()
        bundle.putParcelable("otherUserDetail", otherUser)
        launchFragmentInHiltContainer<ChatFragment>(bundle, R.style.Theme_SecureChat)

        val newMessage = "How about 9am? "
        onView(withText(newMessage)).check(doesNotExist())
        onView(withId(R.id.writeMessageText))
            .perform(typeText(newMessage), closeSoftKeyboard())
        onView(withId(R.id.sendMessageButton))
            .perform(click())
        onView(withId(R.id.writeMessageText))
            .check(matches(withText("")))
        onView(withId(R.id.messageList))
            .check(matches(hasDescendant(withText(newMessage))))
    }

    @Test
    fun sendNewMessage_ShowsInChat() {
        val bundle = Bundle()
        bundle.putParcelable("otherUserDetail", otherUser)
        launchFragmentInHiltContainer<ChatFragment>(bundle, R.style.Theme_SecureChat)

        val newMessage = "How about 9am? "
        onView(withText(newMessage)).check(doesNotExist())
        onView(withId(R.id.writeMessageText))
            .perform(typeText(newMessage), closeSoftKeyboard())
        onView(withId(R.id.sendMessageButton))
            .perform(click())
        onView(withId(R.id.writeMessageText))
            .check(matches(withText("")))
        onView(withId(R.id.messageList))
            .check(matches(hasDescendant(withText(newMessage))))
    }
}