package com.tps.challenge.features.storefeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tps.challenge.R

/**
 * A RecyclerView.Adapter to populate the screen with a store feed.
 */
class StoreFeedAdapter: RecyclerView.Adapter<StoreItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        TODO("Not yet implemented")

        with(holder.itemView) {
            findViewById<TextView>(R.id.name).text = TODO("provide store name")
            findViewById<TextView>(R.id.description).text = TODO("provide store description")
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
