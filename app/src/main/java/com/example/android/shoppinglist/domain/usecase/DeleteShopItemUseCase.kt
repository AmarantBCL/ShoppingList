package com.example.android.shoppinglist.domain.usecase

import com.example.android.shoppinglist.domain.pojo.ShopItem
import com.example.android.shoppinglist.domain.repository.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}