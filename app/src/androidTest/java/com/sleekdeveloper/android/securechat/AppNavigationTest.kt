package com.sleekdeveloper.android.securechat

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.source.saveUserBlocking
import com.sleekdeveloper.android.securechat.di.AppRepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
@UninstallModules(AppRepositoryModule::class)
@HiltAndroidTest
class AppNavigationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: AppRepository

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun unauthenticatedUser_ShowsAuthFragment() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.authFragmentLayout))
            .check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun authenticatedUser_ShowsChatsFragment() {
        repository.saveUserBlocking(User("+14325557956"))
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.chatsFragmentLayout))
            .check(matches(isDisplayed()))

        activityScenario.close()
    }
}