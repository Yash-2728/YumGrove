package com.example.yumgrove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yumgrove.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_search)
        binding.search2.requestFocus()
    }
}