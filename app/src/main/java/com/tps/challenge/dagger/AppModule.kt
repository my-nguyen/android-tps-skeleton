package com.tps.challenge.dagger

import android.app.Application
import com.tps.challenge.TCApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: TCApplication) {
    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }
}
