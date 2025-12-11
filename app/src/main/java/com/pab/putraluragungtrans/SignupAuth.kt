package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.IOException
import com.google.gson.Gson
class SignupAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_auth)

        val actionRegister = findViewById<Button>(R.id.actionRegister)
        val actionBackRegister = findViewById<Button>(R.id.actionBackRegister)

//        actionRegister.setOnClickListener {
//            val inFirstName = findViewById<EditText>(R.id.inputFirstName).text.toString()
//            val inLastName = findViewById<EditText>(R.id.inputLastName).text.toString()
//            val inPhoneNumber = findViewById<EditText>(R.id.inputPhoneNumber).text.toString()
//            val inNik = findViewById<EditText>(R.id.inputNIK).text.toString()
//            val inAddress = findViewById<EditText>(R.id.inputAddress).text.toString()
//            val inEmail = findViewById<EditText>(R.id.inputEmail).text.toString()
//            val inPassword = findViewById<EditText>(R.id.inputPassword).text.toString()
//            val inConfirmPassword = findViewById<EditText>(R.id.inputConfirmPassword).text.toString()
//
//            val newUser = UserSignup(
//                firstName = inFirstName,
//                lastName = inLastName,
//                phoneNumber = inPhoneNumber,
//                nik = inNik,
//                address = inAddress,
//                email = inEmail,
//                password = inPassword,
//                confirmPassword = inConfirmPassword
//            )
//
//            var fileName = "user-$inEmail.json"
//            saveToCacheSignup(fileName, newUser)
//        }

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

//    val appCacheDir = applicationContext.cacheDir
//
//    data class UserSignup(val firstName: String, val lastName: String, val phoneNumber: String, val nik: String, val address: String, val email: String, val password: String, val confirmPassword: String)
//
//    private fun saveToCacheSignup(filename: String, data: UserSignup) {
//        val gson = Gson()
//        val jsonString = gson.toJson(data)
//        try {
//            val file = File(appCacheDir, filename)
//
//            file.writer().use {
//                it.write(jsonString)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
}