package com.example.hanif_3tia.Module

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.hanif_3tia.Module.Tutorial.TutorialModuleActivity
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.FragmentModuleBinding
import kotlin.jvm.java

class ModuleFragment : Fragment() {
        private var _binding: FragmentModuleBinding?=null
        private val binding get() = _binding!!

        private val messageList = listOf(
            ModuleModel("Alya", "Halo! Apa kabar?", "https://i.pravatar.cc/150?img=1"),
            ModuleModel("Budi", "Sudah makan?", "https://i.pravatar.cc/150?img=2"),
            ModuleModel("Citra", "Jangan lupa tugasnya ya!", "https://i.pravatar.cc/150?img=3"),
            ModuleModel("Dika", "Besok kita rapat jam 9", "https://i.pravatar.cc/150?img=4"),
            ModuleModel("Eka", "Nice job kemarin!", "https://i.pravatar.cc/150?img=5"),
            ModuleModel("Fajar", "Lagi ngapain?", "https://i.pravatar.cc/150?img=6"),
            ModuleModel("Gita", "Boleh minta tolong?", "https://i.pravatar.cc/150?img=7"),
            ModuleModel("Hana", "Lihat email ya", "https://i.pravatar.cc/150?img=8"),
            ModuleModel("Irfan", "Oke noted", "https://i.pravatar.cc/150?img=9"),
            ModuleModel("Joko", "Sampai jumpa besok", "https://i.pravatar.cc/150?img=10")
        )

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentModuleBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "Module"

            requireActivity().addMenuProvider(object : MenuProvider {

                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.module_toolbar_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_tutorial -> {
                            startActivity(
                                Intent(
                                    requireContext(),
                                    TutorialModuleActivity::class.java
                                )
                            )
                            true
                        }
                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)

            val adapter = ModuleAdapter(requireContext(), messageList)
            binding.listModuleItem.adapter = adapter
        }
    }