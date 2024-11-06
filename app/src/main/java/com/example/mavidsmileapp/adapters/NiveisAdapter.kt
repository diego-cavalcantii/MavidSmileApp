// PremiosAdapter.kt
package com.example.mavidsmileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mavidsmileapp.databinding.CardNivelBinding
import com.example.mavidsmileapp.domains.Nivel


class NiveisAdapter(private var nivelList: List<Nivel>) : RecyclerView.Adapter<NiveisAdapter.NivelViewHolder>() {

    inner class NivelViewHolder(private val binding: CardNivelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nivel: Nivel) {
            binding.nomePremio.text = nivel.premio.nomePremio
            binding.descricaoPremio.text = nivel.premio.descricaoPremio
            binding.pontosNecessarios.text = nivel.pontosNecessarios.toString()
            binding.nomeNivel.text = nivel.nomeNivel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NivelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardNivelBinding.inflate(layoutInflater, parent, false)
        return NivelViewHolder(binding)
    }

    override fun getItemCount() = nivelList.size

    override fun onBindViewHolder(holder: NivelViewHolder, position: Int) {
        holder.bind(nivelList[position])
    }

    // Método para atualizar a lista de prêmios
    fun updateData(newNivelList: List<Nivel>) {
        nivelList = newNivelList
        notifyDataSetChanged() // Notifica o RecyclerView que os dados mudaram
    }
}
