package com.example.yumgrove

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import java.util.logging.Handler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = android.os.Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
            val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            },3000
        )
    }
}