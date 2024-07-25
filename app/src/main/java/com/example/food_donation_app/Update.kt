package com.company.food_donation_app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class Update : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var donationIdEditText: EditText
    private lateinit var statusSpinner: Spinner
    private lateinit var reasonEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance("https://sample-f9e6a-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("foodItems")

        // Initialize UI elements
        donationIdEditText = findViewById(R.id.edittext_donation_id)
        statusSpinner = findViewById(R.id.spinner_donation_status)
        reasonEditText = findViewById(R.id.edittext_reason)
        submitButton = findViewById(R.id.button_submit)

        submitButton.setOnClickListener {
            val donationId = donationIdEditText.text.toString().trim()
            val newStatus = statusSpinner.selectedItem.toString()
            val reason = reasonEditText.text.toString().trim()

            if (donationId.isEmpty() || reason.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                updateDonationStatus(donationId, newStatus, reason)
            }
        }
    }

    private fun updateDonationStatus(donationId: String, newStatus: String, reason: String) {
        val donationRef = database.child(donationId)

        donationRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val updates = mapOf(
                        "status" to newStatus,
                        "updateReason" to reason
                    )
                    donationRef.updateChildren(updates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this@Update, "Donation updated successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@Update, "Failed to update donation", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this@Update, "Donation ID not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("UpdateActivity", "Error: ${error.message}")
                Toast.makeText(this@Update, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


