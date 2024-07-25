package com.company.food_donation_app

import FoodItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodItemAdapter(
    private val foodItems: List<FoodItem>,
    private val onItemClick: (FoodItem) -> Unit
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item_layout, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val foodItem = foodItems[position]
        holder.bind(foodItem, onItemClick)
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.textview_id)
        private val cookedOrRawTextView: TextView = itemView.findViewById(R.id.textview_cooked_or_raw)
        private val vegOrNonvegTextView: TextView = itemView.findViewById(R.id.textview_veg_or_nonveg)
        private val locationTextView: TextView = itemView.findViewById(R.id.textview_location)
        private val numberPeopleTextView: TextView = itemView.findViewById(R.id.textview_number_people)

        fun bind(foodItem: FoodItem, onItemClick: (FoodItem) -> Unit) {
            idTextView.text = foodItem.id
            cookedOrRawTextView.text = foodItem.cookedOrRaw
            vegOrNonvegTextView.text = foodItem.vegOrNonVeg
            locationTextView.text = foodItem.location
            numberPeopleTextView.text = foodItem.numberPeople

            itemView.setOnClickListener { onItemClick(foodItem) }
        }
    }
}


//package com.company.food_donation_app
//
//import FoodItem
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class FoodItemAdapter(
//    private val foodItems: List<FoodItem>,
//    private val onItemClick: (FoodItem) -> Unit
//) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item_layout, parent, false)
//        return FoodItemViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
//        val foodItem = foodItems[position]
//        holder.bind(foodItem, onItemClick)
//    }
//
//    override fun getItemCount(): Int {
//        return foodItems.size
//    }
//
//    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val idTextView: TextView = itemView.findViewById(R.id.textview_id)
//        private val cookedOrRawTextView: TextView = itemView.findViewById(R.id.textview_cooked_or_raw)
//        private val vegOrNonvegTextView: TextView = itemView.findViewById(R.id.textview_veg_or_nonveg)
//        private val locationTextView: TextView = itemView.findViewById(R.id.textview_location)
//        private val freshnessDurationTextView: TextView = itemView.findViewById(R.id.textview_freshness_duration)
//        private val numberPeopleTextView: TextView = itemView.findViewById(R.id.textview_number_people)
//
//        fun bind(foodItem: FoodItem, onItemClick: (FoodItem) -> Unit) {
//            idTextView.text = foodItem.id
//            cookedOrRawTextView.text = foodItem.cookedOrRaw
//            vegOrNonvegTextView.text = foodItem.vegOrNonVeg
//            locationTextView.text = foodItem.location
//            freshnessDurationTextView.text = foodItem.freshnessDuration
//            numberPeopleTextView.text = foodItem.numberPeople
//
//            itemView.setOnClickListener { onItemClick(foodItem) }
//        }
//    }
//}

//class FoodItemAdapter(
//    private val foodItems: List<FoodItem>,
//    private val onItemClick: (FoodItem) -> Unit
//) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item_layout, parent, false)
//        return FoodItemViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
//        val foodItem = foodItems[position]
//        holder.bind(foodItem, onItemClick)
//    }
//
//    override fun getItemCount(): Int {
//        return foodItems.size
//    }
//
//    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val idTextView: TextView = itemView.findViewById(R.id.textview_id)
//        private val cookedOrRawTextView: TextView = itemView.findViewById(R.id.textview_cooked_or_raw)
//        private val vegOrNonvegTextView: TextView = itemView.findViewById(R.id.textview_veg_or_nonveg)
//        private val locationTextView: TextView = itemView.findViewById(R.id.textview_location)
//        private val freshnessDurationTextView: TextView = itemView.findViewById(R.id.textview_freshness_duration)
//        private val numberPeopleTextView: TextView = itemView.findViewById(R.id.textview_number_people)
//
//        fun bind(foodItem: FoodItem, onItemClick: (FoodItem) -> Unit) {
//            idTextView.text = foodItem.id
//            cookedOrRawTextView.text = foodItem.cookedOrRaw
//            vegOrNonvegTextView.text = foodItem.vegOrNonVeg
//            locationTextView.text = foodItem.location
//            freshnessDurationTextView.text = foodItem.freshnessDuration
//            numberPeopleTextView.text = foodItem.numberPeople
//
//            itemView.setOnClickListener { onItemClick(foodItem) }
//        }
//    }
//}

