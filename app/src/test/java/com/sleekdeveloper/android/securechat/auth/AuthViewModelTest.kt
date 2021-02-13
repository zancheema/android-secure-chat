package com.sleekdeveloper.android.securechat.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sleekdeveloper.android.securechat.R
import com.sleekdeveloper.android.securechat.data.source.FakeRepository
import com.sleekdeveloper.android.securechat.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthViewModelTest {

    private lateinit var repository: FakeRepository
    private lateinit var viewModel: AuthViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = FakeRepository()
        viewModel = AuthViewModel(repository)
    }

    @Test
    fun verifyUserWithInvalidPhoneNumber_GeneratesInvalidCredentialsEvent() {
        viewModel.phoneNumber.value = "567"
        viewModel.verifyPhoneNumber()

        val invalidCredentialsEvent = viewModel.invalidCredentialsEvent.getOrAwaitValue()
        assertThat(
            invalidCredentialsEvent.getContentIfNotHandled(),
            `is`(R.string.invalid_phone_number)
        )
    }
}