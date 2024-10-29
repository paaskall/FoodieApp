package com.example.vfoodie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginButton = findViewById<Button>(R.id.login_button)
        val rememberMeCheckBox = findViewById<CheckBox>(R.id.cbRememberMe)
        val forgotPasswordTextView = findViewById<TextView>(R.id.tvForgotPassword)
        val registerLinkTextView = findViewById<TextView>(R.id.tvRegisterLink)

        // Event handler untuk tombol login
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Ambil data yang tersimpan di SharedPreferences
            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val registeredEmail = sharedPreferences.getString("email", null)
            val registeredPassword = sharedPreferences.getString("password", null)

            // Validasi input
            if (email.isEmpty()) {
                emailInput.error = "Email required"
            } else if (password.isEmpty()) {
                passwordInput.error = "Password required"
            } else if (email == registeredEmail && password == registeredPassword) {
                // Jika login berhasil
                Toast.makeText(this, "Login succes", Toast.LENGTH_SHORT).show()

                // Simpan preferensi "Remember Me" jika dipilih
                if (rememberMeCheckBox.isChecked) {
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("rememberMe", true)
                    editor.apply()
                }

                // Pindah ke halaman berikutnya (misalnya Dashboard)
                val intent = Intent(this, RestaurantListActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika login gagal
                Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Event handler untuk 'Lupa Password'
        forgotPasswordTextView.setOnClickListener {
            Toast.makeText(this, "The forgot password feature is not yet available", Toast.LENGTH_SHORT).show()
        }

        // Pindah ke halaman register jika belum punya akun
        registerLinkTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
