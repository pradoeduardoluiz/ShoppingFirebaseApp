package com.pos.pucpr.shoppinglistapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pos.pucpr.shoppinglistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  override fun onNavigateUp(): Boolean {
    super.onBackPressed()
    return true
  }
}