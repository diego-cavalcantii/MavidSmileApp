package com.example.mavidsmileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mavidsmileapp.databinding.FragmentAwardsBinding
import com.example.mavidsmileapp.databinding.FragmentMetasBinding


class AwardsFragment : Fragment() {
    private var _binding: FragmentAwardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var clienteHelper: ClienteHelper
    private lateinit var premiosAdapter: PremiosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentAwardsBinding.inflate(inflater, container, false)
        clienteHelper = ClienteHelper(requireContext()) // Inicializa o ClienteHelper
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura o RecyclerView e o adapter de prêmios
        premiosAdapter = PremiosAdapter(emptyList())
        binding.recyclerViewPremios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPremios.adapter = premiosAdapter

        // Carrega os dados do cliente e atualiza o adapter com os prêmios recebidos
        clienteHelper.fetchClient("201", binding.layoutBio) { cliente ->
            premiosAdapter.updateData(cliente.premiosRecebidos)
        }

        clienteHelper.fetchProgressoCliente("201",binding.layoutBio)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }

}