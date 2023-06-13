package com.the12smb.capstonefrucheck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.data.remote.response.BuahItem
import com.the12smb.capstonefrucheck.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvContent.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvContent.addItemDecoration(itemDecoration)

        homeViewModel.listBuah.observe(viewLifecycleOwner) { listBuah ->
            binding.rvContent.adapter = showRecyclerView(listBuah)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showRecyclerView(list: List<BuahItem>?): BuahAdapter {
        val buahList = ArrayList<BuahItem>()

        list?.let {
            buahList.clear()
            buahList.addAll(it)
        }

        return BuahAdapter(buahList)
    }
}