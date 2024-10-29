package com.example.vfoodie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RestaurantListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        // Data restoran
        val restaurantList = listOf(
            Restaurant("Mie Gacoan", "Jl. Merdeka No. 1", 4.5f, R.drawable.download1),
            Restaurant("Burger Bangor", "Jl. Mawar No. 5", 4.2f, R.drawable.download),
            Restaurant("Es Teler Pattiro", "Jl. Anggrek No. 7", 4.8f, R.drawable.download2),
            Restaurant("Nasi Kuning Nita", "Jl. Melati No. 17", 4.8f, R.drawable.nita)
            // Tambahkan restoran lain di sini
        )

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.restaurant_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RestaurantAdapter(restaurantList)
    }
}
