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
import androidx.navigation.fragment.findNavController
import ir.mohsenafshar.apps.kdsample.util.data.GlobalApiErrorHandler
import ir.mohsenafshar.apps.kdsample.util.data.autoCleared
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment<BINDING : ViewDataBinding> : Fragment() {

    protected val apiErrorHandler: GlobalApiErrorHandler by inject { parametersOf(requireContext()) }
    protected val navController: NavController by lazy { findNavController() }

    var binding by autoCleared<BINDING>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        onCreateBinding()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun onCreateBinding()
}