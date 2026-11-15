package com.example.hanif_3tia.Module

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// Modul 10 Tahap 1.3: Adapter FragmentStateAdapter untuk mengatur Fragment setiap tab
class ModuleTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // Jumlah total tab yang ada (Tahap 1.3: getItemCount)
    override fun getItemCount(): Int = 2

    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DictionaryFragment()
            1 -> VocabularyFragment()
            else -> throw IllegalStateException("Posisi tab tidak valid: $position")
        }
    }
}
