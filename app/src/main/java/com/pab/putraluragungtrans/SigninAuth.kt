package com.pab.putraluragungtrans

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class SigninAuth : BaseActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin_auth)

        db = AppDatabase.getDatabase(this)

        val actionSignin = findViewById<Button>(R.id.actionSignin)
        val actionToRegister = findViewById<TextView>(R.id.actionToRegister)
        val formSignin = findViewById<LinearLayout>(R.id.formSignin)

        val inputEmail = findViewById<TextInputEditText>(R.id.inputEmail)
        val inputPassword = findViewById<TextInputEditText>(R.id.inputPassword)

        actionSignin.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            when {
                email.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(this@SigninAuth, "Email dan Password wajib diisi", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    lifecycleScope.launch {
                        val user = db.userDao().getUserByEmail(email)

                        when {
                            user == null -> {
                                Toast.makeText(this@SigninAuth, "Invalid credentials", Toast.LENGTH_SHORT).show()
                            }

                            user.password != password -> {
                                Toast.makeText(this@SigninAuth, "Invalid credentials", Toast.LENGTH_SHORT).show()
                            }

                            else -> {
                                Toast.makeText(this@SigninAuth, "Login Berhasil", Toast.LENGTH_SHORT).show()

                                val session = SessionManager(this@SigninAuth)
                                session.createLoginSession(user.email)

                                navigateTo(Dashboard::class.java, R.id.nav_home)
                            }
                        }
                    }
                }
            }
        }

        actionToRegister.setOnClickListener {
            val intent = Intent(this, SignupAuth::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signinAuth)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startSlideToYAnimation(formSignin)
    }

    private fun startSlideToXAnimation(view: ImageView) {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()

        val startX = screenWidth
        val endX = -view.width.toFloat()

        val animator = ObjectAnimator.ofFloat(
            view,
            "translationX",
            startX,
            endX
        )

        animator.duration = 2500
        animator.interpolator = LinearInterpolator()

        animator.start()
    }

    private fun startSlideToYAnimation(view: LinearLayout) {
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        val startY = screenHeight
        val endY = -view.height.toFloat()

        val animator = ObjectAnimator.ofFloat(
            view,
            "translationY",
            startY,
            endY
        )

        animator.duration = 1500
        animator.interpolator = LinearInterpolator()

        animator.start()
    }
}