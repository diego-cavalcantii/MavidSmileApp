// RewardsFragment.kt
package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.databinding.FragmentRewardsBinding

class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var clienteHelper: ClienteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        clienteHelper = ClienteHelper(requireContext()) // Inicializa o ClienteHelper
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Chama a função para buscar o nome do cliente e exibir no TextView
        clienteHelper.fetchClient("201", binding.layoutBio)
        clienteHelper.fetchProgressoCliente("201", binding.layoutBio)

        // Configuração dos botões
        binding.buttonMetas.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_metasFragment)
        }

        binding.buttonAwards.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_awardsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
