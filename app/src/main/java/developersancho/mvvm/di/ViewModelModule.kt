package developersancho.mvvm.di

import developersancho.mvvm.ui.databindingtest.DViewModel
import developersancho.mvvm.ui.main.MainViewModel
import developersancho.mvvm.ui.viewbindingtest.VViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DViewModel(get()) }
    viewModel { VViewModel(get()) }
}