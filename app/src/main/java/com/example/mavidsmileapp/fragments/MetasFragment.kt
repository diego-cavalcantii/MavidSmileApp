// MetasFragment.kt
package com.example.mavidsmileapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mavidsmileapp.Menu
import com.example.mavidsmileapp.adapters.NiveisAdapter
import com.example.mavidsmileapp.databinding.FragmentMetasBinding
import com.example.mavidsmileapp.services.ClienteService
import com.example.mavidsmileapp.services.NivelService
import com.example.mavidsmileapp.services.ProgressoService

class MetasFragment : Fragment() {

    private var _binding: FragmentMetasBinding? = null
    private val binding get() = _binding!!
    private lateinit var niveisAdapter: NiveisAdapter
    private lateinit var menu: Menu
    private lateinit var nivelService: NivelService
    private lateinit var progressoService: ProgressoService
    private lateinit var clienteService: ClienteService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMetasBinding.inflate(inflater, container, false)
        nivelService = NivelService(requireContext())
        progressoService = ProgressoService(requireContext())
        clienteService = ClienteService(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração do RecyclerView e Adapter
        niveisAdapter = NiveisAdapter(emptyList())
        binding.recyclerViewAllPremios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAllPremios.adapter = niveisAdapter

        // Carrega todos os níveis
        nivelService.fetchNiveis { niveis ->
            niveisAdapter.updateData(niveis)
        }

        // Carrega o progresso do cliente
        progressoService.fetchProgressoCliente("201", binding.layoutBio)

        // Carrega os dados do cliente e utiliza o callback para manipular o cliente diretamente
        clienteService.fetchClient("201", binding.layoutBio) { cliente ->
            binding.layoutBio.nomeCliente.text = cliente.nomeCompleto
            binding.layoutBio.nivelCliente.text = cliente.nomeNivel
            binding.layoutBio.pontosCliente.text = "${cliente.pontos}pts"
        }

        // Configuração do menu
        menu = Menu(binding.root, findNavController())
        menu.setupMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
