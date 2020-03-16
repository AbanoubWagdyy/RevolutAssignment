package com.revolut.ui.converter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope

import com.revolut.R
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConverterFragment : Fragment() {

    companion object {
        fun newInstance() =
            ConverterFragment()
    }

    @Inject
    lateinit var viewModel: ConverterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.getRates(getString(R.string.default_base_currency))
                .observe(viewLifecycleOwner, Observer {
                    
                })
        }
    }
}