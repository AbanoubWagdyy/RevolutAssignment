package com.revolut.ui.converter.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.revolut.R
import com.revolut.extensions.showSnackbar
import com.revolut.di.Injectable
import com.revolut.di.injectViewModel
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject
import com.revolut.data.Result
import com.revolut.extensions.hide
import com.revolut.extensions.show
import com.revolut.ui.converter.ui.adapter.ItemMoveCallbackListener
import com.revolut.ui.converter.ui.adapter.OnStartDragListener
import com.revolut.ui.converter.ui.adapter.RatesAdapter

class ConverterFragment : Fragment(R.layout.fragment_converter), Injectable, OnStartDragListener {

    companion object {
        fun newInstance() =
            ConverterFragment()
    }

    init {
        lifecycleScope.launchWhenStarted {
            initOnStart()
        }
    }

    private fun initOnStart() {
        viewModel = injectViewModel(viewModelFactory)
        initializeRecyclerViewAdapter()
        viewModel.getRates(getString(R.string.default_base_currency))
            .observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        progressBar.hide()
                        result.data?.let {
                            if (it.rates != null) {
                                val listRates = it.rates.toRecyclerRateList()
                                listRates.let { it1 -> adapter!!.setUpdatedRates(it1) }
                            }
                        }
                    }
                    Result.Status.LOADING -> progressBar.show()
                    Result.Status.ERROR -> {
                        progressBar.hide()
                        showSnackbar(result.message!!)
                    }
                }
            })
    }

    private fun initializeRecyclerViewAdapter() {
        val linearLayoutManager = LinearLayoutManager(context)
        rvRates.layoutManager = linearLayoutManager
        rvRates.setHasFixedSize(true)
        adapter = RatesAdapter(this)
        val callback: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter!!)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rvRates)
        rvRates.adapter = adapter
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var adapter: RatesAdapter? = null
    private lateinit var viewModel: ConverterViewModel
    lateinit var touchHelper: ItemTouchHelper

    override fun onStartDrag(
        viewHolder: RecyclerView.ViewHolder?
    ) {
        viewHolder?.let { touchHelper.startDrag(it) }
    }

    override fun onSetBaseCurrency(base: String) {
        viewModel.setBaseCurrency(base)
    }
}