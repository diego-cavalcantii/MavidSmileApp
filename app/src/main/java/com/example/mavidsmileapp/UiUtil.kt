package com.example.mavidsmileapp

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mavidsmileapp.databinding.MenuBinding

object UiUtil {
    fun adjustMenuInsets(binding: MenuBinding) {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Ajuste os paddings para garantir que o menu não sobreponha os botões do sistema
            view.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }
    }


}
