package com.pab.putraluragungtrans

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

class AccountCreateNewPasswordActivity : BaseActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account_create_new_password)
        db = AppDatabase.getDatabase(this@AccountCreateNewPasswordActivity)

        val session = SessionManager(this@AccountCreateNewPasswordActivity)
        val emailUser = session.getEmail()

        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val inputConfirmPassword = findViewById<EditText>(R.id.inputConfirmPassword)

        val actionBack = findViewById<MaterialButton>(R.id.actionBack)
        val actionConfirmPassword = findViewById<MaterialButton>(R.id.actionConfirmPassword)

        actionConfirmPassword.setOnClickListener {
            val password = inputPassword.text.toString().trim()
            val confirmPassword = inputConfirmPassword.text.toString().trim()

            if (emailUser == null) {
                Toast.makeText(this@AccountCreateNewPasswordActivity, "Sesi anda telah berakhir, harap login kembali", Toast.LENGTH_SHORT).show()
                session.logout()
                navigateTo(SigninAuth::class.java, isFinal = true)
                return@setOnClickListener
            }

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this@AccountCreateNewPasswordActivity, "Password atau Konfirm Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this@AccountCreateNewPasswordActivity, "Password dan Confirm Password invalid", Toast.LENGTH_SHORT).show()
                inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this@AccountCreateNewPasswordActivity, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show()
                inputPassword.requestFocus()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val dataUser = db.userDao().getUserByEmail(emailUser)

                if (dataUser == null) {
                    Toast.makeText(this@AccountCreateNewPasswordActivity, "Akun tidak valid, harap login kembali", Toast.LENGTH_SHORT).show()
                    session.logout()
                    navigateTo(SigninAuth::class.java, isFinal = true)
                    return@launch
                }

                val result = db.userDao().updatePassword(password, dataUser.email)

                if (result == 0) {
                    Toast.makeText(this@AccountCreateNewPasswordActivity, "Gagal mengubah password, please try again later", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                Toast.makeText(this@AccountCreateNewPasswordActivity, "Password berhasil diubah", Toast.LENGTH_SHORT).show()
                navigateTo(Dashboard::class.java, R.id.nav_account)
            }
        }

        actionBack.setOnClickListener {
            navigateTo(Dashboard::class.java, R.id.nav_account)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}