package com.example.hanif_3tia.Module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hanif_3tia.databinding.FragmentDictionaryBinding

class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null
    private val binding get() = _binding!!

    private val dictionaryList = listOf(
        DictionaryModel(
            word = "Arigatou",
            phonetic = "ありがとう (a-ri-ga-tou)",
            meaning = "Terima kasih (Thank you)",
            example = "Doumo arigatou gozaimasu - Terima kasih banyak.",
            partOfSpeech = "Ungkapan"
        ),
        DictionaryModel(
            word = "Konnichiwa",
            phonetic = "こんにちは (kon-ni-chi-wa)",
            meaning = "Halo / Selamat siang (Hello / Good afternoon)",
            example = "Minasan, konnichiwa! - Halo semuanya!",
            partOfSpeech = "Salam"
        ),
        DictionaryModel(
            word = "Sayounara",
            phonetic = "さようなら (sa-you-na-ra)",
            meaning = "Selamat tinggal (Goodbye)",
            example = "Mata ashita, sayounara - Sampai jumpa besok, selamat tinggal.",
            partOfSpeech = "Salam"
        ),
        DictionaryModel(
            word = "Ganbatte",
            phonetic = "がんばって (gan-bat-te)",
            meaning = "Semangat / Berjuanglah (Do your best)",
            example = "Kyou mo ichinichi ganbatte kudasai! - Hari ini juga tetap semangat!",
            partOfSpeech = "Seruan"
        ),
        DictionaryModel(
            word = "Oyasumi",
            phonetic = "おやすみ (o-ya-su-mi)",
            meaning = "Selamat beristirahat / Selamat malam (Good night)",
            example = "Oyasuminasai, yoi yume wo - Selamat tidur, mimpi indah.",
            partOfSpeech = "Salam"
        ),
        DictionaryModel(
            word = "Gomen nasai",
            phonetic = "ごめんなさい (go-men-na-sa-i)",
            meaning = "Mohon maaf (I am sorry)",
            example = "Okurete gomen nasai - Maaf saya terlambat.",
            partOfSpeech = "Ungkapan"
        ),
        DictionaryModel(
            word = "Onegai",
            phonetic = "おねがい (o-ne-ga-i)",
            meaning = "Tolong / Mohon bantuannya (Please)",
            example = "Kore wo onegai shimasu - Tolong bantu saya dengan ini.",
            partOfSpeech = "Kata Benda"
        ),
        DictionaryModel(
            word = "Subarashii",
            phonetic = "すばらしい (su-ba-ra-shi-i)",
            meaning = "Luar biasa / Hebat (Wonderful / Amazing)",
            example = "Kimi no seiseki wa subarashii desu! - Nilaimu sangat luar biasa!",
            partOfSpeech = "Kata Sifat"
        ),
        DictionaryModel(
            word = "Oishii",
            phonetic = "おいしい (o-i-shi-i)",
            meaning = "Enak / Lezat (Delicious)",
            example = "Kono ramen wa totemo oishii desu - Ramen ini sangat lezat.",
            partOfSpeech = "Kata Sifat"
        ),
        DictionaryModel(
            word = "Tomodachi",
            phonetic = "ともだち (to-mo-da-chi)",
            meaning = "Teman / Sahabat (Friend)",
            example = "Bokutachi wa tomodachi desu - Kita adalah teman.",
            partOfSpeech = "Kata Benda"
        ),
        DictionaryModel(
            word = "Sensei",
            phonetic = "せんせい (sen-se-i)",
            meaning = "Guru / Dosen / Pengajar (Teacher / Professor)",
            example = "Sensei, shitsumon ga arimasu - Pak/Bu Guru, saya punya pertanyaan.",
            partOfSpeech = "Kata Benda"
        ),
        DictionaryModel(
            word = "Daijoubu",
            phonetic = "だいじょうぶ (dai-jou-bu)",
            meaning = "Tidak apa-apa / Aman (It's okay / Fine)",
            example = "Daijoubu desu ka? - Apakah kamu baik-baik saja?",
            partOfSpeech = "Kata Sifat"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dictionaryAdapter = DictionaryAdapter(dictionaryList) { item ->
            Toast.makeText(
                requireContext(),
                "Kamus: ${item.word} = ${item.meaning}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvDictionary.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dictionaryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
