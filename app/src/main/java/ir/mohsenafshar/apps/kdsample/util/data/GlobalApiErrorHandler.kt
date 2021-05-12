package ir.mohsenafshar.apps.kdsample.util.data

import android.content.Context
import android.widget.Toast
import ir.mohsenafshar.apps.kdsample.R
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


class GlobalApiErrorHandler(private val context: Context) {

    fun handleError(error: ApiError) {
        Toast.makeText(context, getErrorDescription(error), Toast.LENGTH_SHORT).show()
    }

    fun getErrorDescription(error: ApiError): String {
        return when (error) {
            is ApiError.BadResponseCode, ApiError.NoContent, ApiError.Parse ->
                context.getString(R.string.err_bad_response)
            is ApiError.Internal -> context.getString(R.string.err_server_unreachable)
            is ApiError.NoConnection -> context.getString(R.string.err_no_internet)
            is ApiError.HandledError -> error.error.message
            is ApiError.Unknown -> when (error.throwable) {
                is UnknownHostException -> context.getString(R.string.err_unknown_host)
                is SSLHandshakeException -> context.getString(R.string.err_ssl_handshake)
                else -> "خطایی در دسترسی به سرور رخ داده است. لطفا دوباره تلاش کنید."
            }
        }
    }
}