package ir.mohsenafshar.apps.kdsample.util.data

import ir.mohsenafshar.apps.kdsample.data.remote.network.model.Error

sealed class ApiError {

    class Unknown(val throwable: Throwable) : ApiError()

    class BadResponseCode(val responseCode: Int) : ApiError()

    object Parse : ApiError()

    object NoConnection : ApiError()

    object NoContent : ApiError()

    object Internal : ApiError()

    sealed class HandledError(val error: Error) : ApiError() {
        companion object {
            const val BAD_REQUEST = 400
            const val FORBIDEN = 403
            const val NOT_FOUND = 404
            const val REQUEST_TIMEOUT = 408
            const val CONFLICT = 409
        }

        class NotFound(val message: String) : HandledError(Error(NOT_FOUND, message))
        class Forbidden(val message: String) : HandledError(Error(FORBIDEN, message))
        class BadRequest(val message: String) : HandledError(Error(BAD_REQUEST, message))
        class Global(val globalError: Error) : HandledError(globalError)
    }

}


