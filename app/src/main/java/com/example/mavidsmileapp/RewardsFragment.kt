// RewardsFragment.kt
package com.example.mavidsmileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mavidsmileapp.databinding.FragmentRewardsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ajustes de insets
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuração dos botões
        binding.buttonMetas.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_metasFragment)
        }

        binding.buttonAwards.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_awardsFragment)
        }

        // Chamar a função para buscar o nome do cliente e exibir no TextView
        fetchClientName("201") // Substitua "1" pelo ID específico do cliente
    }

    private fun fetchClientName(clientId: String) {
        val service = Api.buildService()
        val call = service.getCliente(clientId)

        call.enqueue(object : Callback<Cliente> {
            override fun onResponse(call: Call<Cliente>, response: Response<Cliente>) {
                if (response.isSuccessful) {
                    val cliente = response.body()
                    cliente?.let {
                        binding.layoutBio.nomeCliente.text = it.nomeCompleto
                    }
                } else {
                    Toast.makeText(context, "Erro ao buscar cliente: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Cliente>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa o binding quando a view é destruída
    }
}
