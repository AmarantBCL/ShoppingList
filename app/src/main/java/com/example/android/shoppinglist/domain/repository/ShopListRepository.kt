package com.example.android.shoppinglist.domain.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.android.shoppinglist.domain.pojo.ShopItem

interface ShopListRepository {
    fun getShopList(): LiveData<List<ShopItem>>

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int): ShopItem
}