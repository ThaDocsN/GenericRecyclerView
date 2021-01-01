package com.example.android.genericrecyclerview

class MyAdapter:BaseAdapter() {

    override fun getLayoutIdForType(viewType: Int): Int {
        return R.layout.item_list_layout
    }

}