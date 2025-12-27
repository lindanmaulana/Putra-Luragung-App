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
import kotlin.math.sign

class SignupAuth : BaseActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_auth)
        db = AppDatabase.getDatabase(this)

        val actionRegister = findViewById<Button>(R.id.actionRegister)
        val actionBack = findViewById<Button>(R.id.actionBack)

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

            if (fName.isEmpty() || lName.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Nama dan Email wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nik.length != 16) {
                Toast.makeText(this, "NIK harus 16 digit", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != confirmPass) {
                Toast.makeText(
                    this,
                    "Password dan confirm password tidak valid",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val userBaru = User(
                firstName = fName,
                lastName = lName,
                phoneNumber = phone,
                nik = nik,
                address = address,
                email = email,
                password = pass
            )

            signUp(userBaru)
        }

        actionBack.setOnClickListener {
            navigateTo(SigninAuth::class.java)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signupAuth)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun signUp(data: User) {
        lifecycleScope.launch {
            try {
                db.userDao().insertUser(data)
                Toast.makeText(this@SignupAuth, "Registrasi Berhasil!", Toast.LENGTH_SHORT)
                    .show()

                navigateTo(SuccessSignup::class.java, isFinal = true)
            } catch (e: android.database.sqlite.SQLiteConstraintException) {
                Toast.makeText(
                    this@SignupAuth,
                    "Email atau NIK sudah terdaftar!",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(this@SignupAuth, "Terjadi kesalahan sistem", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}