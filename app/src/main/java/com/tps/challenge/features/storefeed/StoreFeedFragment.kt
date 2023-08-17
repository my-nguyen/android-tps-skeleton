package com.tps.challenge.features.storefeed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tps.challenge.Constants.DEFAULT_LATITUDE
import com.tps.challenge.Constants.DEFAULT_LONGITUDE
import com.tps.challenge.R
import com.tps.challenge.TCApplication
import com.tps.challenge.network.TPSCallService
import com.tps.challenge.network.TPSCoroutineService
import com.tps.challenge.network.model.StoreResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Displays the list of Stores with its title, description and the cover image to the user.
 */
class StoreFeedFragment : Fragment() {
    companion object {
        const val TAG = "StoreFeedFragment"
    }
    private lateinit var storeFeedAdapter: StoreFeedAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var stores: MutableList<StoreResponse>

    @Inject
    lateinit var callService: TPSCallService
    @Inject
    lateinit var coroutineService: TPSCoroutineService

    override fun onCreate(savedInstanceState: Bundle?) {
        TCApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_feed, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        // Enable if Swipe-To-Refresh functionality will be needed
        swipeRefreshLayout.isEnabled = false

        stores = mutableListOf()
        storeFeedAdapter = StoreFeedAdapter(stores)
        recyclerView = view.findViewById(R.id.stores_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            // TODO uncomment the line below whe Adapter is implemented
            adapter = storeFeedAdapter
        }

        // useCallService()
        useCoroutineService()

        return view
    }

    private fun useCallService() {
        callService.getStoreFeed(DEFAULT_LATITUDE, DEFAULT_LONGITUDE).enqueue(object: Callback<List<StoreResponse>> {
            override fun onResponse(call: Call<List<StoreResponse>>, response: Response<List<StoreResponse>>) {
                stores.addAll(response.body()!!)
                storeFeedAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<StoreResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure $t")
            }
        })
    }

    private fun useCoroutineService() {
        CoroutineScope(Dispatchers.Main).launch {
            stores.addAll(coroutineService.getStoreFeed(DEFAULT_LATITUDE, DEFAULT_LONGITUDE))
            storeFeedAdapter.notifyDataSetChanged()
        }
    }
}
