// RewardsFragment.kt
package com.example.mavidsmileapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.Menu
import com.example.mavidsmileapp.R
import com.example.mavidsmileapp.databinding.FragmentRewardsBinding
import com.example.mavidsmileapp.services.ClienteService
import com.example.mavidsmileapp.services.ProgressoService

class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var menu: Menu
    private lateinit var clienteService: ClienteService
    private lateinit var progressoService: ProgressoService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        clienteService = ClienteService(requireContext())
        progressoService = ProgressoService(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clienteService.fetchClient("201", binding.layoutBio) { cliente ->
            binding.layoutBio.nomeCliente.text = cliente.nomeCompleto
            binding.layoutBio.nivelCliente.text = cliente.nomeNivel
            binding.layoutBio.pontosCliente.text = "${cliente.pontos}pts"
        }

        progressoService.fetchProgressoCliente("201",binding.layoutBio)


        // Configuração dos botões
        binding.buttonMetas.setOnClickListener {
            findNavController().navigate(R.id.metasFragment)
        }

        binding.buttonAwards.setOnClickListener {
            findNavController().navigate(R.id.awardsFragment)
        }

        menu = Menu(binding.root, findNavController())
        menu.setupMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
