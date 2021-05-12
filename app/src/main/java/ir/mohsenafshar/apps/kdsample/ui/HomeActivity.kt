package ir.mohsenafshar.apps.kdsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.databinding.ActivityMainBinding
import ir.mohsenafshar.apps.kdsample.ui.detail.DetailViewModel
import ir.mohsenafshar.apps.kdsample.ui.main.MainViewModel
import ir.mohsenafshar.apps.kdsample.util.data.with
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy { findNavController(R.id.navHostFragment) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}