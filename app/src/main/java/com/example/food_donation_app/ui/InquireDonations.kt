package com.company.food_donation_app

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.food_donation_app.databinding.ActivityInquireDonationsBinding

class InquireDonations : AppCompatActivity() {

    private lateinit var binding: ActivityInquireDonationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInquireDonationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the spinner for food preferences
        val foodTypeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.food_type_array,
            android.R.layout.simple_spinner_item
        )
        foodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFoodType.adapter = foodTypeAdapter

        // Set up the spinner for veg/non-veg preferences
        val vegNonVegAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.veg_non_veg_array,
            android.R.layout.simple_spinner_item
        )
        vegNonVegAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerVegNonVeg.adapter = vegNonVegAdapter

        binding.buttonSubmit.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phone = binding.editTextPhone.text.toString()
            val foodType = binding.spinnerFoodType.selectedItem.toString()
            val vegNonVeg = binding.spinnerVegNonVeg.selectedItem.toString()
            val peopleCount = binding.editTextPeopleCount.text.toString()
            val address = binding.editTextAddress.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty() && peopleCount.isNotEmpty() && address.isNotEmpty()) {
                // Here you can handle the data, for example, send it to a server or save it locally
                Toast.makeText(this, "Inquiry submitted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

