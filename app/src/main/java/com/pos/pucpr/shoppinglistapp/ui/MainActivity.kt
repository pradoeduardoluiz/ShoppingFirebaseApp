package com.pos.pucpr.shoppinglistapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pos.pucpr.shoppinglistapp.R
import com.pos.pucpr.shoppinglistapp.auth.Auth
import com.pos.pucpr.shoppinglistapp.auth.AuthStateListener
import com.pos.pucpr.shoppinglistapp.databinding.ActivityMainBinding
import com.pos.pucpr.shoppinglistapp.router.Router
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  val router: Router by inject { parametersOf(this@MainActivity) }

  val auth: Auth<Int, Intent> by inject { parametersOf(this@MainActivity) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  private val authListener: AuthStateListener =
    object : AuthStateListener {
      override fun onAuthChanged(isLoggedIn: Boolean) {
        if (!isLoggedIn) {
          router.showLogin()
        }
      }
    }

  override fun onStart() {
    super.onStart()
    auth.addAuthChangeListener(authListener)
  }

  override fun onStop() {
    super.onStop()
    auth.removeAuthChangeListener(authListener)
  }

  fun startSignIn() {
    auth.startSignIn(RC_GOOGLE_SIGN_IN)
  }

  override fun onNavigateUp(): Boolean {
    super.onBackPressed()
    return true
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == RC_GOOGLE_SIGN_IN) {
      try {
        auth.handleSignInResult(data,
          {
            router.showShoppingList()
          },
          {
            showErrorSignIn()
          })
      } catch (e: Exception) {
        showErrorSignIn()
      }
    }
  }

  private fun showErrorSignIn() {
    Toast.makeText(this, R.string.error_google_sign_in, Toast.LENGTH_SHORT).show()
  }

  companion object {
    const val RC_GOOGLE_SIGN_IN = 1
  }
}