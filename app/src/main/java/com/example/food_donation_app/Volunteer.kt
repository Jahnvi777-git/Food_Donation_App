
package com.company.food_donation_app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Volunteer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer)

        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val editTextTime = findViewById<EditText>(R.id.editTextTime)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        // Set up Date Picker
        editTextDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    editTextDate.setText("$dayOfMonth/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // Set up Time Picker
        editTextTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    editTextTime.setText(String.format("%02d:%02d", hourOfDay, minute))
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }

        buttonSubmit.setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            val contact = findViewById<EditText>(R.id.editTextContact).text.toString()
            val date = editTextDate.text.toString()
            val time = editTextTime.text.toString()
            val volunteerType = findViewById<Spinner>(R.id.spinnerVolunteerType).selectedItem.toString()

            if (name.isEmpty() || contact.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // Handle form submission
                Toast.makeText(this, "Thank you for registering, $name!", Toast.LENGTH_SHORT).show()
                // Reset form
                findViewById<EditText>(R.id.editTextName).setText("")
                findViewById<EditText>(R.id.editTextContact).setText("")
                editTextDate.setText("")
                editTextTime.setText("")
                findViewById<Spinner>(R.id.spinnerVolunteerType).setSelection(0)
            }
        }
    }
}
