package ir.mohsenafshar.apps.kdsample.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.mohsenafshar.apps.kdsample.base.BaseViewHolder
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel

typealias ClickCallback = (view: View, model: DataModel?) -> Unit

class MainAdapter(private val dataList: List<DataModel>, private val onMovieItemClicked: ClickCallback) : RecyclerView.Adapter<BaseViewHolder>() {
    companion object {
        const val TAG = "MainAdapter"
        const val PAGE_SIZE = 20

    }

//    private val diffCallback: DiffUtil.ItemCallback<DataModel> =
//        object : DiffUtil.ItemCallback<DataModel>() {
//            override fun areItemsTheSame(
//                oldItem: DataModel,
//                newItem: DataModel
//            ): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(
//                oldItem: DataModel,
//                newItem: DataModel
//            ): Boolean {
//                return oldItem == newItem
//            }
//        }
//
//    private var asyncListDiffer: AsyncListDiffer<DataModel> = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val movieView = MovieView(parent.context).apply {
            setOnRootClickListener(onMovieItemClicked)
        }
        return BaseViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (holder.itemView as MovieView).setData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

//    fun submitData(list: List<DataModel>) {
//        asyncListDiffer.submitList(list)
//    }
}