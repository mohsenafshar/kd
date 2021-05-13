package ir.mohsenafshar.apps.kdsample.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.card.MaterialCardView
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.domain.entity.movie.MovieItem

class MovieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var movieItem: MovieItem? = null

    private lateinit var root: MaterialCardView
    private lateinit var tvName: TextView
    private lateinit var tvReleaseDate: TextView

    private fun initView() {
        // Inflate And Add Child
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        val view = LayoutInflater.from(context).inflate(R.layout.view_movie, this, true)

        // Find Child Views
        root = findViewById<MaterialCardView>(R.id.root).apply {
            setOnClickListener {
                if (movieItem == null) return@setOnClickListener
                it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(movieItem!!.id))
            }
        }
        tvName = view.findViewById(R.id.tvName)
        tvReleaseDate = view.findViewById(R.id.tvReleaseDate)

    }

    fun setData(data: MovieItem) {
        movieItem = data

        tvName.text = data.title
        tvReleaseDate.text = data.releaseDate
    }

    fun setOnRootClickListener(onContentViewClicked: (view: View, data: MovieItem?) -> Unit) {
        root.setOnClickListener {
            onContentViewClicked.invoke(root, movieItem)
        }
    }

    init {
        initView()
    }

}