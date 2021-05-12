package ir.mohsenafshar.apps.kdsample.ui.main

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.base.BaseFragment
import ir.mohsenafshar.apps.kdsample.databinding.FragmentMainBinding
import ir.mohsenafshar.apps.kdsample.domain.entity.DataModel
import ir.mohsenafshar.apps.kdsample.util.data.with
import ir.mohsenafshar.apps.kdsample.util.ui.EndlessRecyclerOnScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {
    companion object {
        const val TAG = "MainFragment"
    }

    override fun getLayoutRes(): Int = R.layout.fragment_main

    private val viewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter
    private lateinit var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener
    private val dataList = arrayListOf<DataModel>()

    override fun onCreateBinding() {
        initView()
        initClickListeners()
        initAdapter()
        initObservers()
        getFirstPage()
    }

    private fun initView() {
        binding.vm = viewModel
    }

    private fun initClickListeners() {
    }

    private fun initAdapter() {
        mainAdapter = MainAdapter(dataList) { _, model ->
            if (model == null) return@MainAdapter
            navController.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(model.id))
        }

        with(binding.recyclerView) {
            val localLayoutManager = LinearLayoutManager(context)
            layoutManager = localLayoutManager
            endlessRecyclerOnScrollListener = object : EndlessRecyclerOnScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(currentPage: Int) {
                    viewModel.getTopRated(currentPage)
                    Log.e(TAG, "onLoadMore: $currentPage", )
                }
            }
            addOnScrollListener(endlessRecyclerOnScrollListener)
            adapter = mainAdapter
        }
    }

    private fun initObservers() {
        viewModel.topRated.with(viewLifecycleOwner).callbacks {
            onSuccess {
                val oldSize = dataList.size
                dataList.addAll(it)
                mainAdapter.notifyItemRangeInserted(oldSize, it.size)
            }

            onFailed {
                Log.d(TAG, "initObservers: ${apiErrorHandler.getErrorDescription(it)}")
                apiErrorHandler.handleError(it)
            }

            onLoading { }

            doFinally { }
        }
    }

    private fun getFirstPage() {
        viewModel.getTopRated(1)
//        endlessRecyclerOnScrollListener.current_page++
    }
}