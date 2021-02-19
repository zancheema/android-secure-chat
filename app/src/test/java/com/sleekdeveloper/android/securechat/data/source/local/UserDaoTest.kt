package com.sleekdeveloper.android.securechat.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.sleekdeveloper.android.securechat.MainCoroutineRule
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.source.domain.asDatabaseEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    private lateinit var database: AppDatabase

    /**
     * [UserDao]'s (class under test) object
     */
    private lateinit var userDao: UserDao

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = database.userDao()
    }

    @After
    fun closeDb() = database.close()

        @ExperimentalCoroutinesApi
    @Test
    fun getUserReturnsNullWhenUserIsNotInserted() = runBlockingTest {
        assertThat(userDao.getUser(), `is`(nullValue()))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertAndGetUser() = runBlockingTest {
        val user = User("+12345556743")
        userDao.insertUser(user.asDatabaseEntity())

        val loaded = userDao.getUser()

        assertThat(loaded, `is`(notNullValue()))
        loaded as DbUser
        assertThat(loaded.asDomainModel(), `is`(user))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertAndDeleteAllUsers_GetUsersReturnsNull() = runBlockingTest {
        val user = User("+12345556743")
        userDao.insertUser(user.asDatabaseEntity())

        userDao.deleteAllUsers()

        assertThat(userDao.getUser(), `is`(nullValue()))
    }
}