package com.example.android.shoppinglist.domain.usecase

import com.example.android.shoppinglist.domain.pojo.ShopItem
import com.example.android.shoppinglist.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun getShopItem(id: Int): ShopItem {
        return shopListRepository.getShopItem(id)
    }
}