package ir.mohsenafshar.apps.kdsample.ui.main

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Handler
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.base.BaseFragment
import ir.mohsenafshar.apps.kdsample.databinding.FragmentMainBinding
import ir.mohsenafshar.apps.kdsample.util.data.autoCleared
import ir.mohsenafshar.apps.kdsample.util.data.with
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : BaseFragment<FragmentMainBinding>() {
    companion object {
        const val TAG = "MainFragment"
    }

    override fun getLayoutRes(): Int = R.layout.fragment_main

    private val viewModel: MainViewModel by viewModel()

    private lateinit var calendar: Calendar

    var mainAdapter by autoCleared<MainAdapter>()

    override fun onCreateBinding() {
        initView()
        initClickListeners()
        initAdapter()
        initObservers()
    }


    private fun initView() {
        binding.vm = viewModel
        initDateFilteringListeners()
    }

    private fun initClickListeners() {
    }

    private fun initAdapter() {
        mainAdapter = MainAdapter()

        with(binding.recyclerView) {
            val localLayoutManager = LinearLayoutManager(context)
            layoutManager = localLayoutManager
            adapter = mainAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == mainAdapter.itemCount - 1) {
                        viewModel.loadNextPage()
                    }
                }
            })
        }
    }

    private fun initObservers() {
        viewModel.movies.with(viewLifecycleOwner).callbacks {
            onSuccess {
                mainAdapter.submitList(it)
                Handler().postDelayed({Log.d(TAG, "initObservers: ${mainAdapter.currentList.size}")}, 400)
            }

            onFailed {
                Log.d(TAG, "initObservers: ${apiErrorHandler.getErrorDescription(it)}")
                apiErrorHandler.handleError(it)
            }

            onLoading { }

            doFinally { }
        }
    }

    private fun getFilteredMovies() {
        val query = binding.editText.text.toString()
        viewModel.setQuery(query)
    }

    // todo: Create DatePickerFragment
    private fun initDateFilteringListeners() {
        calendar = Calendar.getInstance()

        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        binding.editText.setOnClickListener {
            DatePickerDialog(
                requireContext(), date, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.editText.doAfterTextChanged {
            getFilteredMovies()
        }
    }

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.editText.setText(sdf.format(calendar.time))
    }
}