package com.example.hanif_3tia.Module

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ModuleTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DictionaryFragment()
            1 -> VocabularyFragment()
            else -> throw IllegalStateException("Posisi tab tidak valid: $position")
        }
    }
}
