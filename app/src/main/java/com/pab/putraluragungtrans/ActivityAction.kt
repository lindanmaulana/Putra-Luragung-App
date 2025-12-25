package com.pab.putraluragungtrans
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


open class BaseActivity: AppCompatActivity() {
    fun navigateTo(destination: Class<*>, targetMenuId: Int? = null) {
        val intent = Intent(this, destination)

        val options = ActivityOptions.makeCustomAnimation(
            this,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )

        if (destination.name.contains("Dashboard")) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            targetMenuId?.let { intent.putExtra("TARGET_MENU_ID", it) }
        }

        startActivity(intent, options.toBundle())
    }


    fun replaceFragmentDashboard(containerId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right)
            .replace(containerId, fragment)
            .commit()
    }
}

fun AppCompatActivity.navigateTo(destination: Class<*>, targetMenuId: Int? = null) {
    val intent = Intent(this, destination)

    val options = ActivityOptions.makeCustomAnimation(
        this,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )

    if (destination.name.contains("Dashboard")) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        targetMenuId?.let { intent.putExtra("TARGET_MENU_ID", it) }
    }

    startActivity(intent, options.toBundle())
    finish()
}