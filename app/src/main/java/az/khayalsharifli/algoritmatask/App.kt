package az.khayalsharifli.algoritmatask

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import az.khayalsharifli.algoritmatask.di.appModule
import az.khayalsharifli.algoritmatask.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //Close Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            val list = listOf(appModule, networkModule)
            modules(list)
            androidContext(this@App)
        }
    }
}