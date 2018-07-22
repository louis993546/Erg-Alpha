package io.github.louistsaitszho.erg2

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin(this, listOf(myModule))
    }
}

val myModule : Module = module {
    single { RepositoryImpl() as Repository }
    //TODO single appdatabase
    viewModel { HistoryFragmentViewModel(get()) }
    viewModel { NewSessionFragmentViewModel(get()) }
}