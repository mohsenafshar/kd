package ir.mohsenafshar.apps.kdsample.util.data

import retrofit2.Response
import java.lang.reflect.Type

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiResponse<T> {
            return ApiErrorResponse(ApiError.Unknown(error))
        }

        fun <T> create(responseModel: Response<T>, dataType: Type): ApiResponse<T> {
            return when {
                responseModel.code() == 200 -> {
                    val body = responseModel.body()
                    when {
                        body == null -> ApiErrorResponse(ApiError.NoContent)
                        else -> ApiSuccessResponse(body, dataType)
                    }
                }
                responseModel.code() == 500 -> ApiErrorResponse(ApiError.Internal)
                else -> ApiErrorResponse(ApiError.BadResponseCode(responseModel.code()))
            }
        }
    }
}

data class ApiSuccessResponse<T>(val body: T, val dataType: Type) : ApiResponse<T>()

data class ApiErrorResponse<T>(val reason: ApiError) : ApiResponse<T>()
