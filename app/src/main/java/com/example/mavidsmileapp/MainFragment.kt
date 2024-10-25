package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.UiUtil.adjustMenuInsets
import com.example.mavidsmileapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_mainFragment_to_rankingFragment)
        }

        premioButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rewardsFragment)
        }

        cameraButton.setOnClickListener {
            binding.cardEscovaEFioDental.visibility =
                if (binding.cardEscovaEFioDental.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        adjustMenuInsets(menuBottom)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
