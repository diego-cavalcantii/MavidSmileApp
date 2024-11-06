// RankingFragment.kt
package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mavidsmileapp.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!
    private lateinit var rankingAdapter: RankingAdapter
    private lateinit var clienteHelper: ClienteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        clienteHelper = ClienteHelper(requireContext()) // Inicializa o ClienteHelper
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o RecyclerView e o RankingAdapter
        rankingAdapter = RankingAdapter(emptyList())
        binding.recyclerViewRanking.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRanking.adapter = rankingAdapter

        // Carrega os dados do ranking usando o clienteHelper
        clienteHelper.fetchRankingData("201", rankingAdapter) // Passe o ID do cliente
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
