// MetasFragment.kt
package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mavidsmileapp.databinding.FragmentMetasBinding

class MetasFragment : Fragment() {

    private var _binding: FragmentMetasBinding? = null
    private val binding get() = _binding!!
    private lateinit var clienteHelper: ClienteHelper
    private lateinit var premiosAdapter: PremiosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMetasBinding.inflate(inflater, container, false)
        clienteHelper = ClienteHelper(requireContext()) // Inicializa o ClienteHelper
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Atualiza a chamada para `fetchClient` com o callback
        clienteHelper.fetchClient("201", binding.layoutBio) { cliente ->
            premiosAdapter.updateData(cliente.premiosRecebidos)
        }

        clienteHelper.fetchProgressoCliente("201",binding.layoutBio)

        premiosAdapter = PremiosAdapter(emptyList())
        binding.recyclerViewAllPremios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAllPremios.adapter = premiosAdapter
        clienteHelper.fetchPremios(premiosAdapter)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
