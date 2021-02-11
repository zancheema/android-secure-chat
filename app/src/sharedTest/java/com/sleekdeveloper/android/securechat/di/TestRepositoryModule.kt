package com.sleekdeveloper.android.securechat.di

import com.sleekdeveloper.android.securechat.data.source.AppRepository
import com.sleekdeveloper.android.securechat.data.source.FakeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TestRepositoryModule {
    @Singleton
    @Binds
    abstract fun provideTestRepository(repository: FakeRepository): AppRepository
}