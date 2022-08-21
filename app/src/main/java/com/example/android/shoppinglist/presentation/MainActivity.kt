package com.example.android.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppinglist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun initViews() {
        val button = findViewById<FloatingActionButton>(R.id.btn_add_item)
        button.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        with(recyclerView) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.LAYOUT_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.LAYOUT_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setupRecyclerViewClicks()
        setupRecyclerViewSwipes(recyclerView)
    }

    private fun setupRecyclerViewClicks() {
        shopListAdapter.longClickListener = { viewModel.changeEnabledState(it) }
        shopListAdapter.clickListener = {
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupRecyclerViewSwipes(recyclerView: RecyclerView) {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}