package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
class SignupAuth : AppCompatActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_auth)

        db = AppDatabase.getDatabase(this)

        val actionRegister = findViewById<Button>(R.id.actionRegister)
        val actionBackRegister = findViewById<Button>(R.id.actionBackRegister)

        val inputFirstName = findViewById<TextInputEditText>(R.id.inputFirstName)
        val inputLastName = findViewById<TextInputEditText>(R.id.inputLastName)
        val inputPhoneNumber = findViewById<TextInputEditText>(R.id.inputPhoneNumber)
        val inputNIK = findViewById<TextInputEditText>(R.id.inputNIK)
        val inputAddress = findViewById<TextInputEditText>(R.id.inputAddress)
        val inputEmail = findViewById<TextInputEditText>(R.id.inputEmail)
        val inputPassword = findViewById<TextInputEditText>(R.id.inputPassword)
        val inputConfirmPassword = findViewById<TextInputEditText>(R.id.inputConfirmPassword)

        actionRegister.setOnClickListener {
            val fName = inputFirstName.text.toString().trim()
            val lName = inputLastName.text.toString().trim()
            val phone = inputPhoneNumber.text.toString().trim()
            val nik = inputNIK.text.toString().trim()
            val address = inputAddress.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val pass = inputPassword.text.toString().trim()
            val confirmPass = inputConfirmPassword.text.toString().trim()

            when {
                fName.isEmpty() || lName.isEmpty() || email.isEmpty() -> {
                    Toast.makeText(this, "Nama dan Email wajib diisi", Toast.LENGTH_SHORT).show()
                }

                nik.length != 16 -> {
                    Toast.makeText(this, "NIK harus 16 digit", Toast.LENGTH_SHORT).show()
                }

                pass != confirmPass -> {
                    Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val userBaru = User(
                        firstName = fName,
                        lastName = lName,
                        phoneNumber = phone,
                        nik = nik,
                        address = address,
                        email = email,
                        password = pass
                    )

                    lifecycleScope.launch {
                        db.userDao().insertUser(userBaru)
                        Toast.makeText(this@SignupAuth, "Registrasi Berhasil!", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@SignupAuth, SuccessSignup::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        actionBackRegister.setOnClickListener {
            val intent = Intent(this, SigninAuth::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signupAuth)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}