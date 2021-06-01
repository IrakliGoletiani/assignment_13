package com.example.homework10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework10.databinding.FragmentCountriesBinding


class CountriesFragment : Fragment() {

    private val viewModel: CountriesViewModel by viewModels()

    lateinit var binding: FragmentCountriesBinding

    lateinit var countriesAdapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesBinding.inflate(layoutInflater)

        viewModel.init()

        initAdapter()

        liveDataObserve()

        binding.refreshLayout.setOnRefreshListener {
            countriesAdapter.clearData()
            viewModel.init()
        }

        return binding.root
    }

    private fun liveDataObserve() {
        viewModel._liveDataLoading.observe(viewLifecycleOwner, {
            binding.refreshLayout.isRefreshing = it
        })

        viewModel._countriesLiveData.observe(viewLifecycleOwner, {
            countriesAdapter.setDataToList(it.toMutableList())
        })
    }

    private fun initAdapter() {
        countriesAdapter = CountriesAdapter()
        binding.rvJson.layoutManager = LinearLayoutManager(requireContext())
        binding.rvJson.adapter = countriesAdapter
    }
}