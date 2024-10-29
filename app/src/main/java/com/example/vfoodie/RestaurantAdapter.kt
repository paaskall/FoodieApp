package com.example.vfoodie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class RestaurantAdapter(private val restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    // ViewHolder untuk mengikat data ke tampilan item restoran
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName: TextView = itemView.findViewById(R.id.restaurant_name)
        val restaurantAddress: TextView = itemView.findViewById(R.id.restaurant_address)
        val restaurantRating: TextView = itemView.findViewById(R.id.restaurant_rating)
        val restaurantImage: ImageView = itemView.findViewById(R.id.restaurant_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.restaurantName.text = restaurant.name
        holder.restaurantAddress.text = restaurant.address
        holder.restaurantRating.text = "Rating: ${restaurant.rating}"
        holder.restaurantImage.setImageResource(restaurant.imageResId)

        val radius = 30 // Ukuran radius untuk sudut yang membulat

        // Menggunakan Glide dengan RoundedCorners untuk gambar sudut membulat
        Glide.with(holder.itemView.context)
            .load(restaurant.imageResId)
            .apply(RequestOptions().transform(RoundedCorners(radius)))  // Membuat gambar dengan sudut membulat
            .into(holder.restaurantImage)

        // Tambahkan listener untuk klik
        holder.itemView.setOnClickListener {
            // Navigasi ke MenuActivity saat restoran diklik
            val context = holder.itemView.context
            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra("restaurant_name", restaurant.name) // Kirimkan nama restoran
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }
}
