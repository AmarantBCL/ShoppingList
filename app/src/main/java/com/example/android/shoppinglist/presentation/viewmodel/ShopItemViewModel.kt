package com.example.android.shoppinglist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.shoppinglist.domain.pojo.ShopItem
import com.example.android.shoppinglist.domain.usecase.AddShopItemUseCase
import com.example.android.shoppinglist.domain.usecase.EditShopItemUseCase
import com.example.android.shoppinglist.domain.usecase.GetShopItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopItemViewModel @Inject constructor(
    private val addShopItemUseCase: AddShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val getShopItemUseCase: GetShopItemUseCase
) : ViewModel() {

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _closeActivity = MutableLiveData<Unit>()
    val closeActivity: LiveData<Unit>
        get() = _closeActivity

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val validFields = validateInput(name, count)
        if (validFields) {
            viewModelScope.launch {
                val item = ShopItem(name, count, true)
                addShopItemUseCase.addShopItem(item)
                closeScreen()
            }
        }
    }

    fun updateShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val validFields = validateInput(name, count)
        if (validFields) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopItem(item)
                    closeScreen()
                }
            }
        }
    }

    fun getShopItem(id: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(id)
            _shopItem.value = item
        }
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: DEFAULT_ITEM_COUNT
        } catch (e: RuntimeException) {
            DEFAULT_ITEM_COUNT
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    private fun closeScreen() {
        _closeActivity.value = Unit
    }

    private companion object {
        private const val DEFAULT_ITEM_COUNT = 1
    }
}