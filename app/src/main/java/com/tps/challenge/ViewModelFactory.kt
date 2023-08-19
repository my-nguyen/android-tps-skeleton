package com.tps.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.tps.challenge.network.TPSCoroutineService
import dagger.Lazy
import javax.inject.Inject

/**
 * A Factory class that reduces the boilerplate to initialize the ViewModel within the Activity or a Fragment.
 * Check for usage examples in this codebase and feel free to copy-paste if it will be necessary.
 *
 * Usage example:
 *
 *    @Inject
 *    lateinit var viewModelFactory: ViewModelFactory<StoreFeedViewModel>
 *
 *    private val viewModel: StoreFeedViewModel by lazy {
 *        viewModelFactory.get<StoreFeedViewModel>(
 *            requireActivity()
 *        )
 *    }
 */
/*class ViewModelFactory<T: ViewModel>
@Inject constructor(private val service: TPSCoroutineService) : ViewModelProvider.Factory {*/
class ViewModelFactory(private val service: TPSCoroutineService) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    /**
     * Returns an instance of a defined ViewModel class.
     */
    /*inline fun <reified R: T> get(viewModelStoreOwner: ViewModelStoreOwner): T {
        return ViewModelProvider(viewModelStoreOwner, this)[R::class.java]
    }*/
}
