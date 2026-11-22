package com.example.hanif_3tia.Module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.ItemVocabularyBinding

class VocabularyAdapter(
    private val vocabularyList: List<VocabularyModel>,
    private val onItemClick: (VocabularyModel) -> Unit
) : RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>() {

    inner class VocabularyViewHolder(val binding: ItemVocabularyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder {
        val binding = ItemVocabularyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VocabularyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) {
        val item = vocabularyList[position]
        with(holder.binding) {
            tvVocabTitle.text = item.title
            tvVocabSubtitle.text = item.subtitle
            tvVocabCount.text = item.count

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.logo_light)
                .error(R.drawable.logo_light)
                .into(imgVocabulary)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = vocabularyList.size
}
