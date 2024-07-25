package com.company.food_donation_app

import FoodItem
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Search_food : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var foodItemList: MutableList<FoodItem>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodItemAdapter
    private lateinit var filterOption: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_food)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance("https://sample-f9e6a-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("foodItems")

        // Initialize UI elements
        val filterSpinner = findViewById<Spinner>(R.id.filter_spinner)
        val searchInput = findViewById<EditText>(R.id.search_input)
        recyclerView = findViewById(R.id.food_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        foodItemList = mutableListOf()

        // Initialize the adapter with the food item list and a click listener
        adapter = FoodItemAdapter(foodItemList) { foodItem ->
            // Handle item click, for example:
            Toast.makeText(this, "Clicked on: ${foodItem.id}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        filterOption = "Location"  // Default filter option

        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterOption = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                searchFoodItems(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun searchFoodItems(query: String) {
        val queryRef: Query = when (filterOption) {
            "Location" -> database.orderByChild("location").startAt(query).endAt("$query\uf8ff")
            "Cooked/Raw" -> database.orderByChild("cookedOrRaw").startAt(query).endAt("$query\uf8ff")
            else -> database.orderByChild("location").startAt(query).endAt("$query\uf8ff")
        }

        Log.d("SearchFood", "Querying with filter: $filterOption, query: $query")

        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodItemList.clear()
                for (dataSnapshot in snapshot.children) {
                    val foodItem = dataSnapshot.getValue(FoodItem::class.java)
                    foodItem?.let {
                        if ((filterOption == "Cooked/Raw" && it.cookedOrRaw.contains(query, true)) ||
                            (filterOption == "Location" && it.location.contains(query, true))) {
                            if (it.status != "Spoiled" && it.status != "Donated") {
                                foodItemList.add(it)
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged()
                Log.d("SearchFood", "Found ${foodItemList.size} items for query: $query with filter: $filterOption")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SearchFood", "DatabaseError: ${error.message}")
            }
        })
    }
}




//package com.company.food_donation_app
//
//import FoodItem
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.database.*
//
//class Search_food : AppCompatActivity() {
//
//    private lateinit var database: DatabaseReference
//    private lateinit var foodItemList: MutableList<FoodItem>
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: FoodItemAdapter
//    private lateinit var filterOption: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search_food)
//
//        // Initialize Firebase database reference
//        database = FirebaseDatabase.getInstance("https://sample-f9e6a-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("foodItems")
//
//        // Initialize UI elements
//        val filterSpinner = findViewById<Spinner>(R.id.filter_spinner)
//        val searchInput = findViewById<EditText>(R.id.search_input)
//        recyclerView = findViewById(R.id.food_list_recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        foodItemList = mutableListOf()
//
//        // Initialize the adapter with the food item list and a click listener
//        adapter = FoodItemAdapter(foodItemList) { foodItem ->
//            // Handle item click, for example:
//            Toast.makeText(this, "Clicked on: ${foodItem.id}", Toast.LENGTH_SHORT).show()
//        }
//        recyclerView.adapter = adapter
//
//        filterOption = "Location"  // Default filter option
//
//        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                filterOption = parent?.getItemAtPosition(position).toString()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing
//            }
//        }
//
//        searchInput.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                val query = s.toString().trim()
//                searchFoodItems(query)
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        })
//    }
//
//    private fun searchFoodItems(query: String) {
//        val queryRef: Query = when (filterOption) {
//            "Location" -> database.orderByChild("location").startAt(query).endAt("$query\uf8ff")
//            "Cooked/Raw" -> database.orderByChild("cookedOrRaw").startAt(query).endAt("$query\uf8ff")
//            else -> database.orderByChild("location").startAt(query).endAt("$query\uf8ff")
//        }
//
//        Log.d("SearchFood", "Querying with filter: $filterOption, query: $query")
//
//        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                foodItemList.clear()
//                for (dataSnapshot in snapshot.children) {
//                    val foodItem = dataSnapshot.getValue(FoodItem::class.java)
//                    foodItem?.let {
//                        if ((filterOption == "Cooked/Raw" && it.cookedOrRaw.contains(query, true)) ||
//                            (filterOption == "Location" && it.location.contains(query, true))) {
//                            if (it.status != "Spoiled" && it.status != "Donated") {
//                                foodItemList.add(it)
//                            }
//                        }
//                    }
//                }
//                adapter.notifyDataSetChanged()
//                Log.d("SearchFood", "Found ${foodItemList.size} items for query: $query with filter: $filterOption")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("SearchFood", "DatabaseError: ${error.message}")
//            }
//        })
//    }
//}

