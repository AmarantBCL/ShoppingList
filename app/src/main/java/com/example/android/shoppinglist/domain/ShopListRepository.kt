package com.example.android.shoppinglist.domain

interface ShopListRepository {
    fun getShopList(): List<ShopItem>

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int): ShopItem
}