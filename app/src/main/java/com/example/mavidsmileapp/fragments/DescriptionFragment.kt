package com.example.mavidsmileapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.Menu
import com.example.mavidsmileapp.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!  // Safe property access
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
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

        // Configura os listeners dos botões de navegação internos
        setupButtonListeners()

        menu = Menu(binding.root, findNavController())
        menu.setupMenu()
    }


    private fun setupButtonListeners() {
        // Ao clicar no botão "nextButtonOne", exibe o segundo card
        binding.nextButtonCardOne.setOnClickListener {
            showContainer(binding.cardContainerTwo)
        }

        // Ao clicar no botão "nextButtonTwo", exibe o terceiro card
        binding.nextButtonCardTwo.setOnClickListener {
            showContainer(binding.cardContainerThree)
        }

        // Ao clicar no botão "backButtonOne", volta para o primeiro card
        binding.backButtonCardTwo.setOnClickListener {
            showContainer(binding.cardContainerOne)
        }

        // Ao clicar no botão "backButtonTwo", volta para o segundo card
        binding.backButtonCardThree.setOnClickListener {
            showContainer(binding.cardContainerTwo)
        }
    }

    // Função para mostrar um container e esconder os outros
    private fun showContainer(visibleContainer: View) {
        binding.cardContainerOne.visibility = View.GONE
        binding.cardContainerTwo.visibility = View.GONE
        binding.cardContainerThree.visibility = View.GONE

        visibleContainer.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
