package com.example.hanif_3tia.Module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hanif_3tia.databinding.ItemDictionaryBinding

// Modul 10 Tahap 4.4: RecyclerView Adapter untuk Kamus dengan lambda onItemClick
class DictionaryAdapter(
    private val dictionaryList: List<DictionaryModel>,
    private val onItemClick: (DictionaryModel) -> Unit
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

    inner class DictionaryViewHolder(val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val binding = ItemDictionaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DictionaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val item = dictionaryList[position]
        with(holder.binding) {
            tvWord.text = item.word
            tvPhonetic.text = item.phonetic
            tvMeaning.text = item.meaning
            tvExample.text = item.example
            tvPartOfSpeech.text = item.partOfSpeech
            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = dictionaryList.size
}
