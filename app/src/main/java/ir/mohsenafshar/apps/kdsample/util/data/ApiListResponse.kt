package ir.mohsenafshar.apps.kdsample.util.data

import ir.mohsenafshar.apps.kdsample.data.remote.network.model.PagingList
import retrofit2.Response
import java.lang.reflect.Type

sealed class ApiListResponse<T> {
    companion object {

        fun createList(error: Throwable): ApiErrorListResponse {
            return ApiErrorListResponse(ApiError.Unknown(error))
        }

        fun createList(
            response: Response<PagingList>,
            dataType: Type
        ): ApiListResponse<PagingList> {
            return when {
                response.code() == 200 -> {
                    val body = response.body()
                    when {
                        body == null -> ApiErrorListResponse(ApiError.NoContent)
                        else -> ApiSuccessListResponse(body, dataType)
                    }
                }
                response.code() == 500 -> ApiErrorListResponse(ApiError.Internal)
                else -> ApiErrorListResponse(ApiError.BadResponseCode(response.code()))
            }
        }
    }
}

data class ApiSuccessListResponse(val body: PagingList, val dataType: Type) :
    ApiListResponse<PagingList>()

data class ApiErrorListResponse(val reason: ApiError) : ApiListResponse<PagingList>()

