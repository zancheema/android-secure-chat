package com.sleekdeveloper.android.securechat

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sleekdeveloper.android.securechat.MainViewModel.AuthenticationState
import com.sleekdeveloper.android.securechat.data.source.FakeRepository
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.source.saveUserBlocking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private lateinit var repository: FakeRepository
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = FakeRepository()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun userDoesNotExist_authenticationStateIsUNAUTHENTICATED() {
        val authenticationState = viewModel.authenticationState.getOrAwaitValue()

        assertThat(
            authenticationState.getContentIfNotHandled(),
            `is`(AuthenticationState.UNAUTHENTICATED)
        )
    }

    @Test
    fun userExists_authenticationStateIsAUTHENTICATED() {
        repository.saveUserBlocking(User("+14365552311"))
        val authenticationState = viewModel.authenticationState.getOrAwaitValue()

        assertThat(
            authenticationState.getContentIfNotHandled(),
            `is`(AuthenticationState.AUTHENTICATED)
        )
    }
}