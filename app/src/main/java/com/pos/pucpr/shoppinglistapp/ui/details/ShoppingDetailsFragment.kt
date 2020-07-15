package com.pos.pucpr.shoppinglistapp.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pos.pucpr.shoppinglistapp.R
import com.pos.pucpr.shoppinglistapp.databinding.ShoppingDetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingDetailsFragment : Fragment() {

  private val viewModel: ShoppingDetailsViewModel by viewModel()
  private lateinit var binding: ShoppingDetailsFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = ShoppingDetailsFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

}