package com.example.mavidsmileapp

import androidx.navigation.NavController
import com.example.mavidsmileapp.databinding.MenuBinding

object MenuNavigationUtil {

    fun setupMenuNavigation(
        menuBinding: MenuBinding,
        onRankingClick: () -> Unit,
        onCameraClick: () -> Unit,
        onPremioClick: () -> Unit
    ) {
        val rankingButton = menuBinding.rankingButton
        val premioButton = menuBinding.premioButton
        val cameraButton = menuBinding.cameraButton

        // Navegação usando os botões do menu, mas as ações são passadas dinamicamente
        rankingButton.setOnClickListener { onRankingClick() }
        cameraButton.setOnClickListener { onCameraClick() }
        premioButton.setOnClickListener { onPremioClick() }
    }

}