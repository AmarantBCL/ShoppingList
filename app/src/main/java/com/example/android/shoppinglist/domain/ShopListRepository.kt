package com.example.android.shoppinglist.domain

interface ShopListRepository {
    fun getShopList(): List<ShopItem>

    fun addShopItem(item: ShopItem)

    fun deleteShopItem(item: ShopItem)

    fun editShopItem(item: ShopItem)

    fun getShopItem(id: Int): ShopItem
}