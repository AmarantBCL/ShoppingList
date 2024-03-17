package com.example.android.shoppinglist.presentation

import android.app.Application
import com.example.android.shoppinglist.di.DaggerApplicationComponent

class ShopApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}