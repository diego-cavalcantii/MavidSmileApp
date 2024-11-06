// RankingAdapter.kt
package com.example.mavidsmileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mavidsmileapp.databinding.CardAmigoBinding
import com.example.mavidsmileapp.domains.ClienteRanking

class RankingAdapter(private var list: List<ClienteRanking>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardAmigoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ClienteRanking) {
            binding.nomeAmigo.text = item.nomeCompleto
            binding.pontosAmigo.text = "${item.pontos} pontos"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardAmigoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    // Método para atualizar a lista de dados
    fun updateData(newList: List<ClienteRanking>) {
        list = newList
        notifyItemRangeInserted(0, newList.size)  // Atualiza o RecyclerView apenas para o novo tamanho
    }

}
