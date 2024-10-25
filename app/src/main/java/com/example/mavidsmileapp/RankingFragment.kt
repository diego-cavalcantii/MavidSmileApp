package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.UiUtil.adjustMenuInsets
import com.example.mavidsmileapp.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
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
        premioButton.setOnClickListener {
            findNavController().navigate(R.id.action_rankingFragment_to_rewardsFragment)
        }

        cameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_rankingFragment_to_mainFragment)
        }

        // Navegação para Ranking Fragment (pode não ser necessário neste fragment)
        rankingButton.setOnClickListener {
            // Caso precise adicionar alguma ação para o rankingButton
        }
        adjustMenuInsets(menuBottom)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
