package com.example.android.shoppinglist.domain.usecase

import com.example.android.shoppinglist.domain.pojo.ShopItem
import com.example.android.shoppinglist.domain.repository.ShopListRepository
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}