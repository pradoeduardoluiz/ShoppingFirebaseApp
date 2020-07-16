package com.pos.pucpr.shoppinglistapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pos.pucpr.shoppinglistapp.databinding.ShoppingListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShoppingListFragment : Fragment() {

  private val viewModel: ShoppingListViewModel by viewModel { parametersOf(findNavController()) }
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
    binding.fabAddItem.setOnClickListener {
      viewModel.navigateToDetails()
    }
  }

  private fun subscribeObservers() {
  }

}