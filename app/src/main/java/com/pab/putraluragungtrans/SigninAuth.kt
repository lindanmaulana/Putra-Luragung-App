package com.pab.putraluragungtrans

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SigninAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin_auth)

        val actionSignin = findViewById<Button>(R.id.actionSignin)
        val actionToRegister = findViewById<TextView>(R.id.actionToRegister)
        val busImage = findViewById<ImageView>(R.id.busImage)
        val formSignin = findViewById<LinearLayout>(R.id.formSignin)

        actionSignin.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
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

        startSlideToXAnimation(busImage)
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