package com.example.android.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.example.android.shoppinglist.presentation.viewmodel.MainViewModel
import com.example.android.shoppinglist.presentation.viewmodel.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    @Binds
    fun bindShopItemViewModel(viewModel: ShopItemViewModel): ViewModel
}