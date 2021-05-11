package ir.mohsenafshar.apps.kdsample

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import ir.mohsenafshar.apps.kdsample.di.appModule
import ir.mohsenafshar.apps.kdsample.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule))
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
//        MultiDex.install(this)
    }
}