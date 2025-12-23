package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AccountProfileActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtPhoneNumber: EditText
    private lateinit var edtNIK: EditText
    private lateinit var edtEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account_profile)

        db = AppDatabase.getDatabase(this)

        edtFirstName = findViewById(R.id.edtFirstName)
        edtLastName = findViewById(R.id.edtLastName)
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)
        edtNIK = findViewById(R.id.edtNIK)
        edtEmail = findViewById(R.id.edtEmail)

        val actionUpdate = findViewById<Button>(R.id.actionUpdate)
        val actionBack = findViewById<Button>(R.id.actionBack)

        val session = SessionManager(this@AccountProfileActivity)
        val emailUser = session.getEmail()

        if (emailUser != null) fetchDetailUser(emailUser)

        actionUpdate.setOnClickListener {
            val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val phoneNumber = edtPhoneNumber.text.toString().trim()
            val nik = edtNIK.text.toString().trim()
            val email = edtEmail.text.toString().trim()

            when {
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() -> {
                    Toast.makeText(this, "Nama dan Email wajib diisi", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    updateProfileUser(firstName, lastName, phoneNumber, nik, email)
                    Toast.makeText(this@AccountProfileActivity, "Profile berhasil di ubah", Toast.LENGTH_SHORT).show()

                    fetchDetailUser(email)
                }
            }

        }

        actionBack.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("TARGET_MENU_ID", R.id.nav_account)

            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchDetailUser(email: String) {
        lifecycleScope.launch {
            val user = db.userDao().getUserByEmail(email)
            user?.let {
                edtFirstName.setText(it.firstName)
                edtLastName.setText(it.lastName)
                edtPhoneNumber.setText(it.phoneNumber)
                edtNIK.setText(it.nik)
                edtEmail.setText(it.email)

                edtEmail.isEnabled = false
            }
        }
    }

    private fun updateProfileUser(firstName: String, lastName: String, phoneNumber: String, nik: String, email: String) {
        lifecycleScope.launch {
            val user = db.userDao().getUserByEmail(email)
            if (user == null) {
                Toast.makeText(this@AccountProfileActivity, "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val resultUpdate = db.userDao().updateUserProfile(firstName, lastName, phoneNumber, nik, email)
            if (resultUpdate == 0) {
                Toast.makeText(this@AccountProfileActivity, "Gagal mengubah profile", Toast.LENGTH_SHORT).show()
                return@launch
            }

            finish()
        }
    }
}