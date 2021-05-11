package ir.mohsenafshar.apps.kdsample.data.remote.network.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("data")
    val data: Result,

    @SerializedName("status")
    val status: Boolean
)

//data class MyListResponse(
//    @SerializedName("data")
//    val data: ListResult,
//
//    @SerializedName("status")
//    val status: Boolean
//)

data class Result(
    val data: Map<String, Any>,
    val error: Error? = null
)

//data class ListResult(
//    val data: PagingList,
//    val error: Error? = null
//)

data class PagingList(
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    val results: List<Any>,
)

data class Error(
    @SerializedName("code")
    val code: Int,

    @SerializedName("data")
    val message: String
)