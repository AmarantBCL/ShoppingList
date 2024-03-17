package com.example.android.shoppinglist.di

import android.app.Application
import com.example.android.shoppinglist.data.AppDatabase
import com.example.android.shoppinglist.data.ShopListDao
import com.example.android.shoppinglist.data.ShopListRepositoryImpl
import com.example.android.shoppinglist.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}