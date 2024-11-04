// MainActivity.kt
package com.example.mavidsmileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mavidsmileapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        // Configura o comportamento personalizado para o botão MainFragment
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> {
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        // Obtém uma referência ao MainFragment e chama o método para alternar a visibilidade do CardView
                        val mainFragment = getCurrentFragment() as? MainFragment
                        mainFragment?.toggleCardEscovaEFioDental()
                        true
                    } else {
                        // Caso contrário, navega para o MainFragment normalmente
                        navController.navigate(R.id.mainFragment)
                        true
                    }
                }
                else -> {
                    navController.navigate(item.itemId)
                    true
                }
            }
        }
    }

    // Função auxiliar para obter o fragmento atual do NavHostFragment
    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as? NavHostFragment
        return navHostFragment?.childFragmentManager?.fragments?.firstOrNull()
    }
}
