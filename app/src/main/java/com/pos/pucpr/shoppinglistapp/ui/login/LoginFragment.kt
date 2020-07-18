package com.pos.pucpr.shoppinglistapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pos.pucpr.shoppinglistapp.databinding.LoginFragmentBinding
import com.pos.pucpr.shoppinglistapp.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

  private val viewModel: LoginViewModel by viewModel()
  private lateinit var binding: LoginFragmentBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = LoginFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    subscribeObservers()
    subscriberListeners()
  }

  private fun subscribeObservers() {

  }

  private fun subscriberListeners() {
    binding.buttonSignIn.setOnClickListener {
      if (activity is MainActivity) {
        (activity as MainActivity).startSignIn()
      }
    }
  }

}