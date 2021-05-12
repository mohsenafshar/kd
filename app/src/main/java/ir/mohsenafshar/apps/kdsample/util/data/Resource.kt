package ir.mohsenafshar.apps.kdsample.util.data


sealed class Resource<T>(val status: Status, val data: T? = null) {
    object Loading : Resource<Nothing>(Status.LOADING)

    class Success<T>(data: T) : Resource<T>(Status.SUCCESS, data)

    class Fail<T>(val error: ApiError) : Resource<T>(Status.ERROR)

    companion object {
        fun loading(): Resource<Nothing> = Loading
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> error(error: ApiError): Resource<T> = Fail(error)
    }
}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    FAILURE
}