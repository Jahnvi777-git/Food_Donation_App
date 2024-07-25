package com.example.food_donation_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.food_donation_app.Donate
import com.company.food_donation_app.InquireDonations
import com.company.food_donation_app.Search_food
import com.company.food_donation_app.Update
import com.company.food_donation_app.Volunteer
import com.company.food_donation_app.databinding.FragmentHomeBinding


//import com.example.food_donation_app.dataBinding.FragmentHomeBinding
//import com.example.food_donation_app.Volunteer
//import com.example.food_donation_app.Update
//import com.example.food_donation_app.Donate
//import com.example.food_donation_app.Search_food
//import com.example.food_donation_app.VolunteerActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cardSearchFood.setOnClickListener {
            startActivity(Intent(activity, Search_food::class.java))
        }

        binding.cardDonate.setOnClickListener {
            startActivity(Intent(activity, Donate::class.java))
        }

        binding.cardUpdate.setOnClickListener {
            startActivity(Intent(activity, Update::class.java))
        }

        binding.cardVolunteer.setOnClickListener {
            startActivity(Intent(activity, Volunteer::class.java))
        }
        binding.cardInquireDonations.setOnClickListener {
            startActivity(Intent(activity, InquireDonations::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
