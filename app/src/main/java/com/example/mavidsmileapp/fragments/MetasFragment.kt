// MetasFragment.kt
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
import com.example.mavidsmileapp.adapters.NiveisAdapter
import com.example.mavidsmileapp.databinding.FragmentMetasBinding

class MetasFragment : Fragment() {

    private var _binding: FragmentMetasBinding? = null
    private val binding get() = _binding!!
    private lateinit var clienteHelper: ClienteHelper
    private lateinit var niveisAdapter: NiveisAdapter
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMetasBinding.inflate(inflater, container, false)
        clienteHelper = ClienteHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração do RecyclerView e Adapter
        niveisAdapter = NiveisAdapter(emptyList())
        binding.recyclerViewAllPremios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAllPremios.adapter = niveisAdapter

        // Carrega todos os níveis
        clienteHelper.fetchNiveis { niveis ->
            niveisAdapter.updateData(niveis) // Atualiza o adapter com todos os níveis
        }

        // Carrega o progresso do cliente
        clienteHelper.fetchProgressoCliente("201", binding.layoutBio)

        // Configuração do menu
        menu = Menu(binding.root, findNavController())
        menu.setupMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
