package com.example.hanif_3tia.Target

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hanif_3tia.databinding.FragmentTargetBinding

class TargetFragment : Fragment() {
    private var _binding: FragmentTargetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTargetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val dailyGoalMinutes = sharedPref.getInt("daily_goal_minutes", 15)

        binding.tvDailyGoalVal.text = "$dailyGoalMinutes Menit / Hari"
        binding.progressGoal.max = dailyGoalMinutes
        binding.progressGoal.progress = (dailyGoalMinutes * 0.7).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

