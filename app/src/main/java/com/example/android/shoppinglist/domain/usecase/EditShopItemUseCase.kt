package com.example.android.shoppinglist.domain.usecase

import com.example.android.shoppinglist.domain.pojo.ShopItem
import com.example.android.shoppinglist.domain.repository.ShopListRepository
import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}