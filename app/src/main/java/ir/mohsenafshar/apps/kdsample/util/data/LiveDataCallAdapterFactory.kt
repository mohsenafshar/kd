package ir.mohsenafshar.apps.kdsample.util.data

import androidx.lifecycle.LiveData
import ir.mohsenafshar.apps.kdsample.data.remote.network.model.PagingList
import ir.mohsenafshar.apps.kdsample.data.remote.network.model.ResponseModel
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

typealias LiveDataResponse = LiveData<ApiResponse<Map<String, String>>>
typealias LiveDataListResponse = LiveData<ApiListResponse<PagingList>>

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        require(returnType is ParameterizedType) { "returnType must be parameterized" }
        val observableType = getParameterUpperBound(0, returnType)

        require(observableType is ParameterizedType) { "returnType must be parameterized" }

        val dataType = getParameterUpperBound(0, observableType)

        return when (getRawType(observableType)) {
            ApiResponse::class.java -> {
                LiveDataCallAdapter(dataType)
            }
            ApiListResponse::class.java -> {
                LiveDataListCallAdapter(dataType)
            }
            else -> {
                throw Exception("type must be a ApiResponse or ApiListResponse")
            }
        }

    }
}