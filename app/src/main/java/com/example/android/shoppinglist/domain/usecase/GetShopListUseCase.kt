package com.example.android.shoppinglist.domain.usecase

import androidx.lifecycle.LiveData
import com.example.android.shoppinglist.domain.pojo.ShopItem
import com.example.android.shoppinglist.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}