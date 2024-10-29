package com.example.vfoodie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.Button
import android.util.Log

class CartActivity : AppCompatActivity() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private var cartItems = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRecyclerView = findViewById(R.id.cart_recycler_view)
        totalPriceTextView = findViewById(R.id.total_price)
        checkoutButton = findViewById(R.id.checkout_button)

        // Ambil data keranjang dari Intent
        val cartData = intent.getParcelableArrayListExtra<CartItem>("cart_items")
        if (cartData != null) {
            cartItems.addAll(cartData)
        } else {
            Log.e("CartActivity", "Cart data is null")
            // Menampilkan pesan atau melakukan tindakan lain jika data keranjang kosong
        }

        // Setup RecyclerView
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CartAdapter(cartItems) { item, newQuantity ->
            updateCart(item, newQuantity)
        }
        cartRecyclerView.adapter = adapter

        // Hitung total harga awal
        calculateTotalPrice()

        // Tangani klik tombol checkout
        checkoutButton.setOnClickListener {
            // Membuat dan menampilkan AlertDialog
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Pesanan Diproses")
            builder.setMessage("Pesanan Anda sedang diproses, harap tunggu.")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss() // Menutup dialog ketika tombol OK ditekan
            }
            builder.show()
        }

    }

    private fun updateCart(item: CartItem, newQuantity: Int) {
        // Update kuantitas item
        item.quantity = newQuantity
        calculateTotalPrice()
        cartRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun calculateTotalPrice() {
        var totalPrice = 0
        for (item in cartItems) {
            totalPrice += item.price * item.quantity
        }
        totalPriceTextView.text = "Total: Rp $totalPrice"
    }
}
