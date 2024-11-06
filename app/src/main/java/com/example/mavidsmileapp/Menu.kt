// Menu.kt
package com.example.mavidsmileapp

import android.view.View
import androidx.navigation.NavController
import com.example.mavidsmileapp.R

class Menu(
    private val rootView: View,
    private val navController: NavController
) {
    // Método para configurar os cliques dos botões
    fun setupMenu() {
        // Configuração do botão de prêmio
        rootView.findViewById<View>(R.id.premioButton)?.setOnClickListener {
            navController.navigate(R.id.rewardsFragment)
        }

        // Configuração do botão de câmera
        rootView.findViewById<View>(R.id.cameraButton)?.setOnClickListener {
            // Verifica se estamos no MainFragment
            if (navController.currentDestination?.id == R.id.mainFragment) {
                // Executa a lógica de visibilidade do card apenas se estivermos no MainFragment
                val cardEscovaEFioDental = rootView.findViewById<View>(R.id.cardEscovaEFioDental)
                cardEscovaEFioDental?.visibility =
                    if (cardEscovaEFioDental?.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            } else {
                // Caso contrário, navega para o MainFragment
                navController.navigate(R.id.mainFragment)
            }
        }

        // Configuração do botão de ranking
        rootView.findViewById<View>(R.id.rankingButton)?.setOnClickListener {
            navController.navigate(R.id.rankingFragment)
        }
    }
}
