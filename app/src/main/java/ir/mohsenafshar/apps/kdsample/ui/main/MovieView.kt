package ir.mohsenafshar.apps.kdsample.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel

class MovieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var dataModel: DataModel? = null

    private lateinit var root: MaterialCardView
    private lateinit var tvName: TextView

    private fun initView() {
        // Inflate And Add Child
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        val view = LayoutInflater.from(context).inflate(R.layout.view_movie, this, true)

        // Find Child Views
        root = findViewById(R.id.root)
        tvName = view.findViewById(R.id.tvName)

    }

    fun setData(data: DataModel) {
        dataModel = data

        tvName.text = data.title
    }

    fun setOnRootClickListener(onContentViewClicked: (view: View, data: DataModel?) -> Unit) {
        root.setOnClickListener {
            onContentViewClicked.invoke(root, dataModel)
        }
    }

    init {
        initView()
    }

}