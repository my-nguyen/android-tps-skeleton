package com.tps.challenge.features.storefeed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tps.challenge.Constants.DEFAULT_LATITUDE
import com.tps.challenge.Constants.DEFAULT_LONGITUDE
import com.tps.challenge.MyViewModel
import com.tps.challenge.R
import com.tps.challenge.TCApplication
import com.tps.challenge.ViewModelFactory
import com.tps.challenge.network.TPSCallService
import com.tps.challenge.network.TPSCoroutineService
import com.tps.challenge.network.TPSRxService
import com.tps.challenge.network.model.StoreResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    @Inject
    lateinit var rxService: TPSRxService

    private val viewModel by viewModels<MyViewModel> {
        ViewModelFactory(coroutineService)
    }

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
        // useCoroutineService()
        // useRxService()

        viewModel.getStoreFeed(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
        viewModel.stores.observe(viewLifecycleOwner) { list ->
            stores.addAll(list)
            storeFeedAdapter.notifyDataSetChanged()
        }

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

    private fun useRxService() {
        rxService.getStoreFeed(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<StoreResponse>> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError $e")
                }

                override fun onSuccess(responses: List<StoreResponse>) {
                    Log.e(TAG, "onSuccess")
                    stores.addAll(responses)
                    storeFeedAdapter.notifyDataSetChanged()
                }
            })
    }
}
