package com.example.vfoodie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuList: List<MenuItem>
    private val cartItems: MutableList<CartItem> = mutableListOf() // Keranjang untuk menampung item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Dapatkan nama restoran dari Intent
        val restaurantName = intent.getStringExtra("restaurant_name")

        // Buat data menu berdasarkan restoran yang dipilih
        menuList = getMenuForRestaurant(restaurantName)

        recyclerView = findViewById(R.id.recycler_view_menu)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set adapter untuk RecyclerView
        menuAdapter = MenuAdapter(menuList) { menuItem ->
            addItemToCart(menuItem)  // Tambah item ke cart ketika tombol ditekan
        }
        recyclerView.adapter = menuAdapter

        // Set listener untuk tombol keranjang
        val cartButton: ImageButton = findViewById(R.id.view_cart_button)
        cartButton.setOnClickListener {
            openCartActivity()  // Panggil fungsi untuk membuka keranjang
        }
    }

    // Fungsi untuk mendapatkan menu dari restoran
    private fun getMenuForRestaurant(restaurantName: String?): List<MenuItem> {
        return when (restaurantName) {
            "Mie Gacoan" -> listOf(
                MenuItem("Mie Gacoan Level 1", "Mie pedas dengan level 1", 15000, 4.6f, R.drawable.menu1),
                MenuItem("Mie Gacoan Level 5", "Mie pedas dengan level 5", 17000, 4.7f, R.drawable.menu2),
                MenuItem("Siomay Gacoan", "Siomay Flezat dengan saus kacang", 12000, 4.5f, R.drawable.menu4),
                MenuItem("Es Teh Gacoan", "Minuman teh segar", 5000, 4.4f, R.drawable.menu5),
                MenuItem("Mie Goreng Gacoan", "Mie goreng spesial", 16000, 4.6f, R.drawable.menu3)
            )
            "Burger Bangor" -> listOf(
                MenuItem("Burger Original Bangor", "Burger klasik dengan daging sapi", 30000, 4.7f, R.drawable.menu6),
                MenuItem("Cheese Burger Bangor", "Burger keju dengan daging juicy", 35000, 4.8f, R.drawable.menu7),
                MenuItem("Burger Ayam Bangor", "Burger ayam renyah", 28000, 4.6f, R.drawable.menu8),
                MenuItem("Double Patty Bangor", "Burger dengan dua lapis patty", 45000, 4.9f, R.drawable.menu9),
                MenuItem("French Fries Bangor", "Kentang goreng renyah", 15000, 4.5f, R.drawable.menu10)
            )
            "Es Teler Pattiro" -> listOf(
                MenuItem("Es Teler Original", "Kelapa muda, alpukat, nangka, sirup", 18000, 4.7f, R.drawable.menu11)
            )
            "Nasi Kuning Nita" -> listOf(
                MenuItem("Nasi Kuning Ayam", "Nasi kuning ayam krispi, bakar & goreng", 14000, 4.7f, R.drawable.menu12),
                MenuItem("Nasi Kuning Telur", "Nasi kuning telur balado", 10000, 4.7f, R.drawable.menu13)
            )
            else -> emptyList()
        }
    }

    // Fungsi untuk menambahkan item ke cart
    private fun addItemToCart(menuItem: MenuItem) {
        // Cek apakah item sudah ada di keranjang
        val existingItem = cartItems.find { it.name == menuItem.name }
        if (existingItem != null) {
            // Jika sudah ada, tambahkan quantity
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            cartItems[cartItems.indexOf(existingItem)] = updatedItem
        } else {
            // Jika belum ada, tambahkan item baru dengan imageResId
            cartItems.add(CartItem(menuItem.name, menuItem.price, 1, menuItem.imageResId))  // Quantity awal 1, tambahkan imageResId
        }

        // Menampilkan pesan item berhasil ditambahkan
        Toast.makeText(this, "${menuItem.name} (x${cartItems.find { it.name == menuItem.name }?.quantity ?: 1}) added to cart", Toast.LENGTH_SHORT).show()
    }

    // Fungsi untuk membuka CartActivity
    private fun openCartActivity() {
        val intent = Intent(this, CartActivity::class.java)
        intent.putParcelableArrayListExtra("cart_items", ArrayList(cartItems))  // Mengirim data keranjang
        startActivity(intent)
    }
}
