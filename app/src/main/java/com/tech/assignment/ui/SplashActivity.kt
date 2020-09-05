package com.tech.assignment.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tech.assignment.R
import com.tech.assignment.product.ui.ProductActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activityScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, ProductActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}
