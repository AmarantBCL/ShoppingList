package com.example.android.shoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.shoppinglist.R
import com.example.android.shoppinglist.domain.pojo.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopListViewHolder>(ShopItemDiffCallback()) {
    var clickListener: ((ShopItem) -> Unit)? = null
    var longClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val layoutId = when (viewType) {
            LAYOUT_ENABLED -> R.layout.item_shop_enabled
            LAYOUT_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layoutId,
            parent,
            false
        )
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            tvName.text = item.name
            tvCount.text = item.count.toString()
            view.setOnLongClickListener {
                longClickListener?.invoke(item)
                true
            }
            view.setOnClickListener { clickListener?.invoke(item) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            LAYOUT_ENABLED
        } else {
            LAYOUT_DISABLED
        }
    }

    companion object {
        const val LAYOUT_ENABLED = 1
        const val LAYOUT_DISABLED = 2

        const val MAX_POOL_SIZE = 15
    }
}