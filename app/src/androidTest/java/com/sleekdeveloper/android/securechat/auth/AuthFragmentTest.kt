package com.sleekdeveloper.android.securechat.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.sleekdeveloper.android.securechat.data.source.FakeRepository
import com.sleekdeveloper.android.securechat.di.AppRepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
@UninstallModules(AppRepositoryModule::class)
@HiltAndroidTest
class AuthFragmentTest {
    @get:Rule
    private val hiltRule = HiltAndroidRule(this)

    @Inject
    private lateinit var repository: FakeRepository

    @Before
    fun init() {
        hiltRule.inject()
    }
}