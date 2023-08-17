package com.tps.challenge.features.storefeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tps.challenge.R
import com.tps.challenge.network.model.StoreResponse

/**
 * A RecyclerView.Adapter to populate the screen with a store feed.
 */
class StoreFeedAdapter(val stores: List<StoreResponse>): RecyclerView.Adapter<StoreItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val store = stores[position]

        with(holder.itemView) {
            findViewById<TextView>(R.id.name).text = store.name
            findViewById<TextView>(R.id.description).text = store.description
        }
    }

    override fun getItemCount() = stores.size
}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
