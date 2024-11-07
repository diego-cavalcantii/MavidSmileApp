package com.example.mavidsmileapp.services

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.mavidsmileapp.Api
import com.example.mavidsmileapp.R
import com.example.mavidsmileapp.domains.ProgressoCliente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgressoService(private val context: Context) {

    fun fetchProgressoCliente(clienteId: String, binding: ViewBinding) {
        val service = Api.buildServiceProgressoCliente()
        val call = service.getProgressoCliente(clienteId)

        call.enqueue(object : Callback<ProgressoCliente> {
            override fun onResponse(call: Call<ProgressoCliente>, response: Response<ProgressoCliente>) {
                if (response.isSuccessful) {
                    val progressoCliente = response.body()
                    progressoCliente?.let {
                        binding.root.findViewById<TextView>(R.id.registrosCliente).text =
                            "${it.registros} ${if (it.registros > 1) "registros" else "registro"}"
                    }
                }
            }

            override fun onFailure(call: Call<ProgressoCliente>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
