package com.sleekdeveloper.android.securechat.di

import com.sleekdeveloper.android.securechat.data.source.AppRepository
import com.sleekdeveloper.android.securechat.data.source.DefaultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppRepositoryModule {
    @Singleton
    @Provides
    fun provideAppRepository(): AppRepository {
        return DefaultRepository()
    }
}
