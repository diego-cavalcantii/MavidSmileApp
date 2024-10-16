package com.example.mavidsmileapp

import android.app.Activity
import android.content.Intent
import android.widget.ImageButton

// Classe utilitária para configurar a navegação entre telas
object NavigationUtil {

    // Função que configura os botões para navegação entre as atividades
    fun setupNavigation(
        activity: Activity,
        cameraButton: ImageButton,
        rankingButton: ImageButton,
        premioButton: ImageButton
    ) {
        // Configura o botão para ir para a tela principal (MainActivity)
        cameraButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }

        // Configura o botão para ir para a tela de ranking (RankingActivity)
        rankingButton.setOnClickListener {
            val intent = Intent(activity, RankingActivity::class.java)
            activity.startActivity(intent)
        }

        // Configura o botão para ir para a tela de recompensas (RewardsActivity)
        premioButton.setOnClickListener {
            val intent = Intent(activity, RewardsActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
