// AwardsFragment.kt
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
import com.example.mavidsmileapp.adapters.PremiosAdapter
import com.example.mavidsmileapp.databinding.FragmentAwardsBinding

class AwardsFragment : Fragment() {
    private var _binding: FragmentAwardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var clienteHelper: ClienteHelper
    private lateinit var premiosAdapter: PremiosAdapter
    private lateinit var niveisAdapter: NiveisAdapter
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAwardsBinding.inflate(inflater, container, false)
        clienteHelper = ClienteHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração do RecyclerView e Adapter
        premiosAdapter = PremiosAdapter(emptyList())
        binding.recyclerViewPremios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPremios.adapter = premiosAdapter

        // Carrega os dados do cliente e exibe os prêmios recebidos
        clienteHelper.fetchClient("201", binding.layoutBio) { cliente ->
            premiosAdapter.updateData(cliente.premiosRecebidos) // Atualiza o adapter com os prêmios
        }


        clienteHelper.fetchProgressoCliente("201", binding.layoutBio)

        menu = Menu(binding.root, findNavController())
        menu.setupMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
