package ir.mohsenafshar.apps.kdsample.data.remote.network.model

import com.google.gson.annotations.SerializedName

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

    @SerializedName("message")
    val message: String
)