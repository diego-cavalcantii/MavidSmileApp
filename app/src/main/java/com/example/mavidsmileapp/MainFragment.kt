package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    private lateinit var cameraButton: ImageButton
    private lateinit var cardEscovaEFioDental: CardView
    private lateinit var rankingButton: ImageButton
    private lateinit var premioButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
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
//        cardEscovaEFioDental = view.findViewById(R.id.cardEscovaEFioDental)
        rankingButton = view.findViewById(R.id.rankingButton)
        premioButton = view.findViewById(R.id.premioButton)

        // Lógica para exibir/ocultar o CardView
        cardEscovaEFioDental.visibility = View.GONE

        // Navegação usando ações do nav_graph
        rankingButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rankingFragment)
        }

        premioButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rewardsFragment)
        }

        cameraButton.setOnClickListener {
            cardEscovaEFioDental.visibility =
                if (cardEscovaEFioDental.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}
