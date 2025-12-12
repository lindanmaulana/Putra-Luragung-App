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
class SignupAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_auth)

        val actionRegister = findViewById<Button>(R.id.actionRegister)
        val actionBackRegister = findViewById<Button>(R.id.actionBackRegister)

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