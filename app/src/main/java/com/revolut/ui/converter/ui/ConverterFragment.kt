package com.revolut.ui.converter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.revolut.R
import com.revolut.data.showSnackbar
import com.revolut.di.Injectable
import com.revolut.di.injectViewModel
import com.revolut.ui.converter.ui.adapter.RatesAdapter
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject
import com.revolut.data.Result
import com.revolut.data.hide
import com.revolut.data.show

class ConverterFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() =
            ConverterFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var adapter: RatesAdapter? = null

    private lateinit var viewModel: ConverterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        lifecycleScope.launchWhenStarted {
            initializeRecyclerViewAdapter()
            viewModel.getRates(getString(R.string.default_base_currency))
                .observe(viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            progressBar.hide()
                            result.data?.let { Log.d("result", result.data.toString()) }
                        }
                        Result.Status.LOADING -> progressBar.show()
                        Result.Status.ERROR -> {
                            progressBar.hide()
                            showSnackbar(result.message!!)
                        }
                    }
                })
        }
    }

    private fun initializeRecyclerViewAdapter() {
        val linearLayoutManager = LinearLayoutManager(context)
        rvRates.layoutManager = linearLayoutManager
        rvRates.setHasFixedSize(true)
        adapter = RatesAdapter(null)
        rvRates.adapter = adapter
    }
}