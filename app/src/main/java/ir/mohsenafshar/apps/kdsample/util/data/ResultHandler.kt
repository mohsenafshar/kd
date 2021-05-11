package ir.mohsenafshar.apps.kdsample.util.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import ir.mohsenafshar.apps.kdsample.util.data.*
import java.io.IOException

class ResultHandlerFactory() {

    fun <T> create(f: () -> LiveData<ApiResponse<T>>): ResultHandler<T> =
        object : ResultHandler<T>() {
            override fun createCall(): LiveData<ApiResponse<T>> = f()
        }

    fun <T> createList(f: () -> LiveData<ApiListResponse<T>>): ListResultHandler<T> =
        object : ListResultHandler<T>() {
            override fun createCall(): LiveData<ApiListResponse<T>> = f()
        }
}

abstract class ResultHandler<T> {
    private val result = MediatorLiveData<Resource<T>>()

    init {
        fetchFromNetwork()
    }

    @Suppress("UNCHECKED_CAST")
    private fun fetchFromNetwork() {
        val responseLiveData: LiveData<ApiResponse<T>> = createCall()
        result.value = Resource.loading() as Resource<T>
        result.addSource(responseLiveData) {
            when (it) {
                is ApiSuccessResponse -> {
                    val res = getResultAsObject(it)
                    if (res == null) {
                        result.value = Resource.error(ApiError.Parse)
                        return@addSource
                    }

                    result.value = Resource.success(res)
                }
                is ApiErrorResponse -> {
                    result.value = Resource.error(it.reason)
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<T>>

    private fun getResultAsObject(apiResponse: ApiSuccessResponse<T>): T? {
        return try {
            val gson = Gson()
            return gson.fromJson<T>(gson.toJson(apiResponse.body), apiResponse.dataType)
        } catch (e: IOException) {
            null
        }
    }

    abstract fun createCall(): LiveData<ApiResponse<T>>
}

abstract class ListResultHandler<T> {
    private val result = MediatorLiveData<Resource<List<T>>>()

    init {
        fetchFromNetwork()
    }

    @Suppress("UNCHECKED_CAST")
    private fun fetchFromNetwork() {
        val responseLiveData: LiveData<ApiListResponse<T>> = createCall()
        result.value = Resource.loading() as Resource<List<T>>
        result.addSource(responseLiveData) {
            when (it) {
                is ApiSuccessListResponse -> {
                    val res = TypeConverter.getResultAsList<T>(it.dataType, it.body.results)
                    if (res == null) {
                        result.value = Resource.error(ApiError.Parse)
                        return@addSource
                    }

                    result.value = Resource.success(res)
                }
                is ApiErrorListResponse -> {
                    result.value = Resource.error(it.reason)
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<List<T>>>
    abstract fun createCall(): LiveData<ApiListResponse<T>>
}
