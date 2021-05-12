package ir.mohsenafshar.apps.kdsample.util.data

import android.os.Handler
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

typealias OnSuccessCallback<T> = (T) -> Unit
typealias OnFailedCallback = (ApiError) -> Unit

class CallbackStack<T> {
    var onSuccess: (OnSuccessCallback<T>)? = null
    var onFailed: (OnFailedCallback)? = null
    var doFinally: (() -> Unit)? = null
    var onLoading: (() -> Unit)? = null

    fun onSuccess(cb: OnSuccessCallback<T>) {
        this.onSuccess = cb
    }

    fun onFailed(cb: OnFailedCallback) {
        this.onFailed = cb
    }

    fun onLoading(cb: () -> Unit) {
        this.onLoading = cb
    }

    fun doFinally(cb: () -> Unit) {
        this.doFinally = cb
    }
}

class ObserveStarter<T>(private val owner: LifecycleOwner, private val liveData: LiveData<Resource<T>>) {

    var isStarted = false

    fun callbacks(action: CallbackStack<T>.() -> Unit) {
        val stack = CallbackStack<T>()
        stack.action()
        liveData.observe(owner, Observer {
            when (it) {
                is Resource.Loading -> {
                    isStarted = true
                    stack.onLoading?.invoke()
                }
                is Resource.Success<T> -> {
                    it.data.let { data -> stack.onSuccess?.invoke(data!!) }
                }
                is Resource.Fail<T> -> {
                    if (isStarted)
                        stack.onFailed?.invoke(it.error)
                }
            }

            if (it !is Resource.Loading && isStarted) {
                isStarted = false
                stack.doFinally?.invoke()
            }
        })
    }
}

fun <T> LiveData<Resource<T>>.with(owner: LifecycleOwner): ObserveStarter<T> = ObserveStarter(owner, this)
