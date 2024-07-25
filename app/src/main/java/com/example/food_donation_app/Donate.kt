package com.company.food_donation_app

import FoodItem
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Donate : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        // Update this line with the correct database URL
        database = FirebaseDatabase.getInstance("https://sample-f9e6a-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("foodItems")

        val cookedRawSpinner = findViewById<Spinner>(R.id.spinner_cooked_raw)
        val vegNonVegSpinner = findViewById<Spinner>(R.id.spinner_veg_nonveg)
        val locationField = findViewById<EditText>(R.id.edittext_location)
        val freshnessDurationField = findViewById<EditText>(R.id.edittext_freshness_duration)
        val numberPeopleField = findViewById<EditText>(R.id.edittext_number_people)
        val additionalDetailsField = findViewById<EditText>(R.id.edittext_additional_details)
        val submitButton = findViewById<Button>(R.id.button_submit)

        submitButton.setOnClickListener {
            Log.d("DonateActivity", "Submit button clicked")

            val cookedOrRaw = cookedRawSpinner.selectedItem.toString()
            val vegOrNonVeg = vegNonVegSpinner.selectedItem.toString()
            val location = locationField.text.toString().trim()
            val freshnessDuration = freshnessDurationField.text.toString().trim()
            val numberPeople = numberPeopleField.text.toString().trim()
            val additionalDetails = additionalDetailsField.text.toString().trim()

            if (location.isEmpty() || freshnessDuration.isEmpty() || numberPeople.isEmpty()) {
                Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Generate a readable donation ID
            val donationId = generateDonationId()
            val foodItem = FoodItem(donationId, cookedOrRaw, vegOrNonVeg, location, freshnessDuration, numberPeople, additionalDetails)

            Log.d("DonateActivity", "Attempting to write to database")

            database.child(donationId).setValue(foodItem)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("DonateActivity", "Donation successful")
                        Toast.makeText(this, "Food donated successfully!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Log.e("DonateActivity", "Donation failed: ${task.exception?.message}")
                        Toast.makeText(this, "Donation failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { exception ->
                    Log.e("DonateActivity", "Donation failed: ${exception.message}")
                    Toast.makeText(this, "Donation failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun generateDonationId(): String {
        val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
        return "DON-$timestamp"
    }
}

//package com.company.food_donation_app
//
//import FoodItem
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//class Donate : AppCompatActivity() {
//    private lateinit var database: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_donate)
//
//        // Update this line with the correct database URL
//        database = FirebaseDatabase.getInstance("https://sample-f9e6a-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("foodItems")
//
//        val cookedRawSpinner = findViewById<Spinner>(R.id.spinner_cooked_raw)
//        val vegNonVegSpinner = findViewById<Spinner>(R.id.spinner_veg_nonveg)
//        val locationField = findViewById<EditText>(R.id.edittext_location)
//        val freshnessDurationField = findViewById<EditText>(R.id.edittext_freshness_duration)
//        val additionalDetailsField = findViewById<EditText>(R.id.edittext_additional_details)
//        val submitButton = findViewById<Button>(R.id.button_submit)
//
//        submitButton.setOnClickListener {
//            Log.d("DonateActivity", "Submit button clicked")
//
//            val cookedOrRaw = cookedRawSpinner.selectedItem.toString()
//            val vegOrNonVeg = vegNonVegSpinner.selectedItem.toString()
//            val location = locationField.text.toString().trim()
//            val freshnessDuration = freshnessDurationField.text.toString().trim()
//            val additionalDetails = additionalDetailsField.text.toString().trim()
//
//            if (location.isEmpty() || freshnessDuration.isEmpty()) {
//                Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            // Generate a readable donation ID
//            val donationId = generateDonationId()
//            val foodItem = FoodItem(donationId, cookedOrRaw, vegOrNonVeg, location, freshnessDuration, additionalDetails)
//
//            Log.d("DonateActivity", "Attempting to write to database")
//
//            database.child(donationId).setValue(foodItem)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Log.d("DonateActivity", "Donation successful")
//                        Toast.makeText(this, "Food donated successfully!", Toast.LENGTH_SHORT).show()
//                        finish()
//                    } else {
//                        Log.e("DonateActivity", "Donation failed: ${task.exception?.message}")
//                        Toast.makeText(this, "Donation failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                    }
//                }.addOnFailureListener { exception ->
//                    Log.e("DonateActivity", "Donation failed: ${exception.message}")
//                    Toast.makeText(this, "Donation failed: ${exception.message}", Toast.LENGTH_SHORT).show()
//                }
//        }
//    }
//
//    private fun generateDonationId(): String {
//        val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
//        return "DON-$timestamp"
//    }
//}
//
//
