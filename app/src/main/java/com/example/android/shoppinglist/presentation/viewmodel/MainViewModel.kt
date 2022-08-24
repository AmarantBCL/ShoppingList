package com.example.android.shoppinglist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android.shoppinglist.data.ShopListRepositoryImpl
import com.example.android.shoppinglist.domain.usecase.DeleteShopItemUseCase
import com.example.android.shoppinglist.domain.usecase.EditShopItemUseCase
import com.example.android.shoppinglist.domain.usecase.GetShopListUseCase
import com.example.android.shoppinglist.domain.pojo.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}