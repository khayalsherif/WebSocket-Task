package az.khayalsharifli.algoritmatask.di

import androidx.room.Room
import az.khayalsharifli.algoritmatask.data.local.LocalDataBase
import az.khayalsharifli.algoritmatask.data.local.LocalDataSource
import az.khayalsharifli.algoritmatask.data.local.LocalDataSourceImpl
import az.khayalsharifli.algoritmatask.data.repository.HomeRepository
import az.khayalsharifli.algoritmatask.data.repository.HomeRepositoryImpl
import io.socket.client.IO
import io.socket.client.Socket
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.net.URI

val networkModule = module {
    single<Socket> {
        val url = URI.create("https://q.investaz.az")
        val opts = IO.Options()
        opts.path = "/live"
        IO.socket(url, opts)
    }



    single { get<LocalDataBase>().dao() }

    single<LocalDataSource> { LocalDataSourceImpl(dao = get()) }

    single<HomeRepository> { HomeRepositoryImpl(dataSource = get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            LocalDataBase::class.java,
            "data"
        ).fallbackToDestructiveMigration()
            .build()
    }


}