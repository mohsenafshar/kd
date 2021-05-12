package ir.mohsenafshar.apps.kdsample.ui.detail

import android.util.Log
import androidx.navigation.fragment.navArgs
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.base.BaseFragment
import ir.mohsenafshar.apps.kdsample.databinding.FragmentDetailBinding
import ir.mohsenafshar.apps.kdsample.ui.HomeActivity
import ir.mohsenafshar.apps.kdsample.util.data.with
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_detail

    private val viewModel: DetailViewModel by viewModel()
    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onCreateBinding() {
        initView()
        initClickListeners()
        initObservers()

        viewModel.getDetail(navArgs.id)
    }

    private fun initView() {
        binding.vm = viewModel
    }

    private fun initClickListeners() {
    }

    private fun initObservers() {
        viewModel.detail.with(this).callbacks {
            onSuccess {
                Log.d(HomeActivity.TAG, "onCreate: $it")
                binding.detail = it
            }

            onFailed {  }

            onLoading {  }

            doFinally {  }
        }
    }
}