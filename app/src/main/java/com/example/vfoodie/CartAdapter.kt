package com.example.vfoodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChanged: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemQuantity: TextView = itemView.findViewById(R.id.item_quantity)
        val increaseQuantity: Button = itemView.findViewById(R.id.increase_quantity)
        val decreaseQuantity: Button = itemView.findViewById(R.id.decrease_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.itemName.text = currentItem.name
        holder.itemPrice.text = "Rp ${currentItem.price}"
        holder.itemQuantity.text = currentItem.quantity.toString()

        // Menggunakan Glide untuk memuat gambar dengan sudut membulat
        val radius = 30 // Atur radius sesuai keinginan
        Glide.with(holder.itemView.context)
            .load(currentItem.imageResId)  // Menggunakan imageResId dari CartItem
            .apply(RequestOptions().transform(RoundedCorners(radius)))  // Membuat gambar dengan sudut membulat
            .into(holder.itemImage)

        // Atur listener untuk menambah dan mengurangi kuantitas
        holder.increaseQuantity.setOnClickListener {
            onQuantityChanged(currentItem, currentItem.quantity + 1)
        }

        holder.decreaseQuantity.setOnClickListener {
            if (currentItem.quantity > 1) {
                onQuantityChanged(currentItem, currentItem.quantity - 1)
            } else {
                // Menghapus item dari keranjang jika kuantitas berkurang menjadi 0
                cartItems.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartItems.size)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}