package com.example.android.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.android.shoppinglist.domain.pojo.ShopItem

interface ShopListRepository {
    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(id: Int): ShopItem
}