package ir.mohsenafshar.apps.kdsample.util.data

import ir.mohsenafshar.apps.kdsample.data.remote.network.model.PagingList
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter(private val dataType: Type) :
    CallAdapter<Map<String, String>, LiveDataResponse> {

    override fun responseType() = dataType

    override fun adapt(call: Call<Map<String, String>>): LiveDataResponse {
        return object : LiveDataResponse() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<Map<String, String>> {
                        override fun onResponse(
                            call: Call<Map<String, String>>,
                            responseModel: Response<Map<String, String>>
                        ) {
                            postValue(ApiResponse.create(responseModel, dataType))
                        }

                        override fun onFailure(call: Call<Map<String, String>>, throwable: Throwable) {
                            postValue(ApiResponse.create(throwable))
                        }
                    })
                }
            }
        }
    }
}


class LiveDataListCallAdapter(private val dataType: Type) :
    CallAdapter<PagingList, LiveDataListResponse> {

    override fun responseType() = PagingList::class.java

    override fun adapt(call: Call<PagingList>): LiveDataListResponse {
        return object : LiveDataListResponse() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<PagingList> {
                        override fun onResponse(
                            call: Call<PagingList>,
                            response: Response<PagingList>
                        ) {
                            postValue(ApiListResponse.createList(response, dataType))
                        }

                        override fun onFailure(call: Call<PagingList>, throwable: Throwable) {
                            postValue(ApiListResponse.createList(throwable))
                        }
                    })
                }
            }
        }
    }
}