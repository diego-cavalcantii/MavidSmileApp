package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.databinding.FragmentMetasBinding

class MetasFragment : Fragment() {

    private var _binding: FragmentMetasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentMetasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        MenuNavigationUtil.setupMenuNavigation(
            binding.menuBottom,
            onRankingClick = {
                findNavController().navigate(R.id.action_metasFragment_to_rankingFragment)
            },
            onCameraClick = {
                findNavController().navigate(R.id.action_metasFragment_to_mainFragment)
            },
            onPremioClick = {
                findNavController().navigate(R.id.action_metasFragment_to_rewardsFragment)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}