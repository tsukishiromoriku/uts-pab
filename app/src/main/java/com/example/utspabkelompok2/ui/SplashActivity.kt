package com.example.utspabkelompok2.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.utspabkelompok2.R
import com.example.utspabkelompok2.util.SessionManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure status bar and navigation bar are consistent Dongker
        window.statusBarColor = ContextCompat.getColor(this, R.color.header_dark)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.header_dark)

        setContentView(R.layout.activity_splash)

        val cardLogo = findViewById<View>(R.id.cardSplashLogo)
        val tvTitle = findViewById<View>(R.id.tvSplashTitle)
        val tvSubtitle = findViewById<View>(R.id.tvSplashSubtitle)
        val layoutBottom = findViewById<View>(R.id.layoutBottomSplash)

        // Initial state
        cardLogo.alpha = 0f
        cardLogo.scaleX = 0.5f
        cardLogo.scaleY = 0.5f
        cardLogo.translationY = -60f

        tvTitle.alpha = 0f
        tvTitle.translationY = 40f

        tvSubtitle.alpha = 0f
        tvSubtitle.translationY = 30f

        layoutBottom.alpha = 0f

        // 1. Animate Logo with luxury overshoot bounce
        cardLogo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .translationY(0f)
            .setDuration(900)
            .setInterpolator(OvershootInterpolator(1.4f))
            .start()

        // 2. Animate App Title
        tvTitle.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(350)
            .setDuration(700)
            .setInterpolator(DecelerateInterpolator())
            .start()

        // 3. Animate Tagline
        tvSubtitle.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(550)
            .setDuration(700)
            .setInterpolator(DecelerateInterpolator())
            .start()

        // 4. Animate bottom indicator
        layoutBottom.animate()
            .alpha(1f)
            .setStartDelay(750)
            .setDuration(500)
            .start()

        // 5. Navigate to Login or Dashboard after splash animation
        Handler(Looper.getMainLooper()).postDelayed({
            val session = SessionManager(this)
            val destination = if (session.isLoggedIn() && session.getUserId() != SessionManager.NO_USER_196) {
                DashboardActivity::class.java
            } else {
                LoginActivity::class.java
            }

            val intent = Intent(this, destination)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_scale_in, R.anim.fade_scale_out)
            finish()
        }, 2200)
    }
}

