package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.databinding.FragmentRewardsBinding

class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplicando ajustes de insets
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Acessa os botões no menu incluído
        val menuBottom = binding.menuBottom

        val rankingButton = menuBottom.rankingButton
        val premioButton = menuBottom.premioButton
        val cameraButton = menuBottom.cameraButton

        // Navegação usando os botões do menu
        rankingButton.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_rankingFragment)
        }

        cameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_mainFragment)
        }

        // Ação para o botão de prêmio, se necessário
        premioButton.setOnClickListener {
            // Adicione uma ação aqui se precisar para o prêmio
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
