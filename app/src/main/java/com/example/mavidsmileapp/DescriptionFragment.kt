package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!  // Safe property access

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
        // Configura os botões do menu
        setupMenuButtons()

        // Configura os listeners dos botões de navegação internos
        setupButtonListeners()
    }

    private fun setupMenuButtons() {
        // Acessa os botões do menu incluído
        val menuBottom = binding.menuBottom



        val rankingButton = menuBottom.rankingButton
        val premioButton = menuBottom.premioButton
        val cameraButton = menuBottom.cameraButton

        // Navegação entre fragments usando os botões do menu
        rankingButton.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_rankingFragment)
        }

        premioButton.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_rewardsFragment)
        }

        cameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_mainFragment)
        }
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
