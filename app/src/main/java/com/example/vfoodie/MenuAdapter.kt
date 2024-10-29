package com.example.vfoodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MenuAdapter(
    private val menuList: List<MenuItem>,
    private val onAddToCartClicked: (MenuItem) -> Unit  // Callback untuk add to cart
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    // ViewHolder class untuk item menu
    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuImage: ImageView = view.findViewById(R.id.menu_image)
        val menuName: TextView = view.findViewById(R.id.menu_name)
        val menuPrice: TextView = view.findViewById(R.id.menu_price)
        val menuDescription: TextView = view.findViewById(R.id.menu_description)
        val menuRating: RatingBar = view.findViewById(R.id.menu_rating)
        val addToCartButton: Button = view.findViewById(R.id.add_to_cart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentItem = menuList[position]

        // Set data item menu
        holder.menuName.text = currentItem.name
        holder.menuPrice.text = "Rp ${currentItem.price}"
        holder.menuDescription.text = currentItem.description
        holder.menuRating.rating = currentItem.rating

        // Menggunakan Glide dengan RoundedCorners untuk gambar sudut membulat
        val radius = 60 // Deklarasi radius untuk sudut membulat
        Glide.with(holder.itemView.context)
            .load(currentItem.imageResId)  // Menggunakan currentItem.imageResId
            .apply(RequestOptions().transform(RoundedCorners(radius)))  // Membuat gambar dengan sudut membulat
            .into(holder.menuImage)

        // Listener untuk tombol Add to Cart
        holder.addToCartButton.setOnClickListener {
            onAddToCartClicked(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}