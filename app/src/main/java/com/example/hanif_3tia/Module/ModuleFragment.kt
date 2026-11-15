package com.example.hanif_3tia.Module

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.hanif_3tia.Module.Tutorial.TutorialModuleActivity
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.FragmentModuleBinding
import com.google.android.material.tabs.TabLayoutMediator

// Modul 10: Mengintegrasikan TabLayout + ViewPager2 + TabLayoutMediator pada Tab 3 Kamus & Kosakata
class ModuleFragment : Fragment() {

    private var _binding: FragmentModuleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Modul 5: Pasang Toolbar pada Fragment
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Kamus & Kosakata"

        // Modul 5 Option Menu: Info Tutorial dengan MenuProvider
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.module_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_tutorial -> {
                        startActivity(Intent(requireContext(), TutorialModuleActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Modul 10 Tahap 1.4: Inisialisasi dan pasang adapter ke ViewPager2
        val tabsAdapter = ModuleTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // Modul 10 Tahap 1.4 & 2.1: Hubungkan TabLayout dan ViewPager2 dengan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Kamus"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_nav_dict)
                    // Modul 10 Tahap 2.1: Badge titik tanpa angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Kosakata"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_star)
                    // Modul 10 Tahap 2.1: Badge angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 10
                }
            }
        }.attach()
    }

    // Modul 10 Tahap 4.1: Mencegah kebocoran memori
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
