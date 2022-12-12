package az.khayalsharifli.algoritmatask.di

import az.khayalsharifli.algoritmatask.ui.content.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        HomeViewModel(application = androidApplication(), socket = get(), repository = get())
    }
}