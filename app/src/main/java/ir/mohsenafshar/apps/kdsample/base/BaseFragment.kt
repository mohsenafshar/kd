package ir.mohsenafshar.apps.kdsample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.util.data.GlobalApiErrorHandler
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment<BINDING : ViewDataBinding> : Fragment() {

    protected val apiErrorHandler: GlobalApiErrorHandler by inject { parametersOf(requireContext()) }
    protected val navController: NavController by lazy { findNavController() }

    protected lateinit var binding: BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onCreateBinding()
        return binding.root
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun onCreateBinding()
}