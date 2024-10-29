package com.example.vfoodie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val fullNameInput = findViewById<EditText>(R.id.fullname_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirm_password_input)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginLinkTextView = findViewById<TextView>(R.id.tvLoginLink)

        registerButton.setOnClickListener {
            val fullName = fullNameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            // Validasi input
            if (fullName.isEmpty()) {
                fullNameInput.error = "Full name required"
            } else if (email.isEmpty()) {
                emailInput.error = "Email required"
            } else if (password.isEmpty()) {
                passwordInput.error = "Password required"
            } else if (password != confirmPassword) {
                confirmPasswordInput.error = "Password doesn't match"
            } else {
                // Simpan data register di SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.putString("email", email)
                editor.putString("password", password)  // Menyimpan password (sebagai contoh, jangan lakukan ini pada aplikasi nyata)
                editor.apply()

                Toast.makeText(this, "Registrasi succes!", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman login setelah registrasi berhasil
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Pindah ke halaman login jika sudah punya akun
        loginLinkTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
