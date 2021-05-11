package ir.mohsenafshar.apps.kdsample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ir.mohsenafshar.apps.kdsample.R
import ir.mohsenafshar.apps.kdsample.ui.detail.DetailViewModel
import ir.mohsenafshar.apps.kdsample.util.data.with
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private val viewModel: MainViewModel by viewModel()
    private val viewModel2: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.topRated.with(this).callbacks {
            onSuccess {
                Log.d(TAG, "onCreate: $it")

                viewModel2.getDetail(it.first().id)
            }

            onFailed {  }

            onLoading {  }

            doFinally {  }
        }

        viewModel.getTopRated(1)

        viewModel2.detail.with(this).callbacks {
            onSuccess {
                Log.d(TAG, "onCreate: $it")
            }

            onFailed {  }

            onLoading {  }

            doFinally {  }
        }
    }
}