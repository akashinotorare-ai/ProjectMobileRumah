package com.example.hanif_3tia.Module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hanif_3tia.databinding.FragmentVocabularyBinding

class VocabularyFragment : Fragment() {

    private var _binding: FragmentVocabularyBinding? = null
    private val binding get() = _binding!!

    private val vocabularyList = listOf(
        VocabularyModel(
            title = "Food & Drinks",
            subtitle = "Makanan & Minuman",
            imageUrl = "https://picsum.photos/seed/food99/400/300",
            count = "25 Kosakata"
        ),
        VocabularyModel(
            title = "Animals & Pets",
            subtitle = "Hewan & Peliharaan",
            imageUrl = "https://picsum.photos/seed/animal99/400/300",
            count = "18 Kosakata"
        ),
        VocabularyModel(
            title = "Daily Activity",
            subtitle = "Aktivitas Harian",
            imageUrl = "https://picsum.photos/seed/activity99/400/300",
            count = "30 Kosakata"
        ),
        VocabularyModel(
            title = "Campus & Study",
            subtitle = "Kuliah & Belajar",
            imageUrl = "https://picsum.photos/seed/study99/400/300",
            count = "22 Kosakata"
        ),
        VocabularyModel(
            title = "Travel & Places",
            subtitle = "Wisata & Tempat",
            imageUrl = "https://picsum.photos/seed/travel99/400/300",
            count = "20 Kosakata"
        ),
        VocabularyModel(
            title = "Feelings & Mood",
            subtitle = "Emosi & Perasaan",
            imageUrl = "https://picsum.photos/seed/mood99/400/300",
            count = "15 Kosakata"
        ),
        VocabularyModel(
            title = "Weather & Nature",
            subtitle = "Cuaca & Alam",
            imageUrl = "https://picsum.photos/seed/nature99/400/300",
            count = "14 Kosakata"
        ),
        VocabularyModel(
            title = "Family & People",
            subtitle = "Keluarga & Orang",
            imageUrl = "https://picsum.photos/seed/family99/400/300",
            count = "16 Kosakata"
        ),
        VocabularyModel(
            title = "Shopping & Items",
            subtitle = "Belanja & Benda",
            imageUrl = "https://picsum.photos/seed/shop99/400/300",
            count = "28 Kosakata"
        ),
        VocabularyModel(
            title = "Technology & Work",
            subtitle = "Teknologi & Kerja",
            imageUrl = "https://picsum.photos/seed/tech99/400/300",
            count = "24 Kosakata"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVocabularyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vocabularyAdapter = VocabularyAdapter(vocabularyList) { item ->
            Toast.makeText(
                requireContext(),
                "Membuka Materi: ${item.title} (${item.subtitle})",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvVocabulary.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = vocabularyAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
