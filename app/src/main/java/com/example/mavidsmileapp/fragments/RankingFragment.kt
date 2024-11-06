// RankingFragment.kt
package com.example.mavidsmileapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mavidsmileapp.ClienteHelper
import com.example.mavidsmileapp.Menu
import com.example.mavidsmileapp.adapters.RankingAdapter
import com.example.mavidsmileapp.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!
    private lateinit var rankingAdapter: RankingAdapter
    private lateinit var clienteHelper: ClienteHelper
    private lateinit var menu: Menu

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

        menu = Menu(binding.root, findNavController())
        menu.setupMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
