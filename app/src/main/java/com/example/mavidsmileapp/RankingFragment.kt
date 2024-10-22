package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController

class RankingFragment : Fragment() {

    private lateinit var cameraButton: ImageButton
    private lateinit var rankingButton: ImageButton
    private lateinit var premioButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplicando ajustes de insets
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa os botões
        cameraButton = view.findViewById(R.id.cameraButton)
        rankingButton = view.findViewById(R.id.rankingButton)
        premioButton = view.findViewById(R.id.premioButton)

        // Navegação usando ações do nav_graph

        premioButton.setOnClickListener {
            findNavController().navigate(R.id.action_rankingFragment_to_rewardsFragment)
        }

        cameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_rankingFragment_to_mainFragment)
        }
    }
}
