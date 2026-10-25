package com.example.hanif_3tia.Module

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.hanif_3tia.databinding.ItemModuleBinding

class ModuleAdapter(
    context: Context,
    private val messages: List<ModuleModel>
    ) : ArrayAdapter<ModuleModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemModuleBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = messages[position]
        // Muat gambar dari URL ke ImageView
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        view.setOnClickListener {
            val i = Intent(context, ModuleActivity::class.java)
            i.putExtra("SENDER", data.senderName)
            i.putExtra("MESSAGE", data.messageText)
            context.startActivity(i)

        }

        return view
    }
}