package com.pos.pucpr.shoppinglistapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pos.pucpr.shoppinglistapp.databinding.ShoppingListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment() {

  private val viewModel: ShoppingListViewModel by viewModel()
  private lateinit var binding: ShoppingListFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = ShoppingListFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    subscribeObservers()
    subscribeListeners()
  }

  private fun subscribeListeners() {
    TODO("Not yet implemented")
  }

  private fun subscribeObservers() {
    TODO("Not yet implemented")
  }

}