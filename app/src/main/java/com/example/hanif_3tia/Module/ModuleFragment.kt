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

class ModuleFragment : Fragment() {
    private var _binding: FragmentModuleBinding? = null
    private val binding get() = _binding!!

    // Modul 7 Tahap 4: Data model kosakata Bahasa Inggris & Jepang
    private val vocabList = listOf(
        ModuleModel("Hello (こんにちは)", "Sapaan umum sehari-hari (Konnichiwa)", "https://avatar.iran.liara.run/public/1"),
        ModuleModel("Thank You (ありがとう)", "Ungkapan terima kasih (Arigatou)", "https://avatar.iran.liara.run/public/2"),
        ModuleModel("Good Morning (おはよう)", "Selamat pagi (Ohayou)", "https://avatar.iran.liara.run/public/3"),
        ModuleModel("Friend (ともだち)", "Teman atau sahabat (Tomodachi)", "https://avatar.iran.liara.run/public/4"),
        ModuleModel("Delicious (おいしい)", "Makanan lezat / enak (Oishii)", "https://avatar.iran.liara.run/public/5"),
        ModuleModel("Study (べんきょう)", "Belajar atau menuntut ilmu (Benkyou)", "https://avatar.iran.liara.run/public/6"),
        ModuleModel("Teacher (せんせい)", "Guru atau mentor (Sensei)", "https://avatar.iran.liara.run/public/7"),
        ModuleModel("Water (みず)", "Air minum (Mizu)", "https://avatar.iran.liara.run/public/8"),
        ModuleModel("School (がっこう)", "Sekolah atau kampus (Gakkou)", "https://avatar.iran.liara.run/public/9"),
        ModuleModel("Goodbye (さようなら)", "Selamat tinggal (Sayounara)", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Modul 5 & 7: Pasang Toolbar pada Fragment
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Kamus & Kosakata"

        // Modul 9 Tahap 5.4: Menu Info Tutorial dengan MenuProvider
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

        // Modul 7 Tahap 4.7: Pasang Custom Adapter ke ListView
        val adapter = ModuleAdapter(requireContext(), vocabList)
        binding.listModuleItem.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
