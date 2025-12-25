package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class AccountChangePasswordActivity : BaseActivity() {
    private lateinit var db: AppDatabase
    private lateinit var inputPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account_change_password)

        db = AppDatabase.getDatabase(this@AccountChangePasswordActivity)
        val session = SessionManager(this@AccountChangePasswordActivity)
        val email = session.getEmail()

        inputPassword = findViewById<EditText>(R.id.inputPassword)

        val actionChangePassword = findViewById<MaterialButton>(R.id.actionChangePassword)
        val actionBack = findViewById<MaterialButton>(R.id.actionBack)

        actionBack.setOnClickListener {
            navigateTo(Dashboard::class.java, R.id.nav_account)
        }

        actionChangePassword.setOnClickListener {
            val password = inputPassword.text.toString().trim()

            if (password.isEmpty()) {
                Toast.makeText(this@AccountChangePasswordActivity, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (email == null) {
                Toast.makeText(this@AccountChangePasswordActivity, "Sesi anda telah berakhir, harap login kembali", Toast.LENGTH_SHORT).show()
                session.logout()
                navigateTo(SigninAuth::class.java, isFinal = true)
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val dataUser = db.userDao().getUserByEmail(email)

                if (dataUser == null) {
                    Toast.makeText(this@AccountChangePasswordActivity, "Akun tidak valid, harap login kembali", Toast.LENGTH_SHORT).show()
                    session.logout()
                    navigateTo(SigninAuth::class.java, isFinal = true)
                    return@launch
                }

                if (password != dataUser.password) {
                    Toast.makeText(this@AccountChangePasswordActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                navigateTo(AccountCreateNewPasswordActivity::class.java)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}