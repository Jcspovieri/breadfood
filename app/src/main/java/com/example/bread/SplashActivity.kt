package com.example.bread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        changeToLogin()
    }



    fun changeToLogin() {

        val intent = Intent(this, MainActivity::class.java)

        Handler().postDelayed({
            intent.change()
        }, 3000)
    }

    fun Intent.change() {
        startActivity(this)
        finish()
    }
}
