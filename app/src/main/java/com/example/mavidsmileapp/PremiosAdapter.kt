// PremiosAdapter.kt
package com.example.mavidsmileapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mavidsmileapp.databinding.CardPremioBinding

class PremiosAdapter(private var premiosList: List<Premio>) : RecyclerView.Adapter<PremiosAdapter.PremioViewHolder>() {

    inner class PremioViewHolder(private val binding: CardPremioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(premio: Premio) {
            binding.nomePremio.text = premio.nomePremio
            binding.descricaoPremio.text = premio.descricaoPremio
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardPremioBinding.inflate(layoutInflater, parent, false)
        return PremioViewHolder(binding)
    }

    override fun getItemCount() = premiosList.size

    override fun onBindViewHolder(holder: PremioViewHolder, position: Int) {
        holder.bind(premiosList[position])
    }

    // Método para atualizar a lista de prêmios
    fun updateData(newPremiosList: List<Premio>) {
        premiosList = newPremiosList
        notifyDataSetChanged() // Notifica o RecyclerView que os dados mudaram
    }
}
