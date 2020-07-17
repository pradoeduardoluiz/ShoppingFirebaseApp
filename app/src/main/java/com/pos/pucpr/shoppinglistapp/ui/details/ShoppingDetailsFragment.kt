package com.pos.pucpr.shoppinglistapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pos.pucpr.shoppinglistapp.common.State
import com.pos.pucpr.shoppinglistapp.common.ext.afterTextChanged
import com.pos.pucpr.shoppinglistapp.common.ext.observe
import com.pos.pucpr.shoppinglistapp.databinding.ShoppingDetailsFragmentBinding
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShoppingDetailsFragment : Fragment() {

  private val viewModel: ShoppingDetailsViewModel by viewModel { parametersOf(findNavController()) }
  private lateinit var binding: ShoppingDetailsFragmentBinding
  private val args: ShoppingDetailsFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    args.id?.let { viewModel.getShopping(id = it) }
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = ShoppingDetailsFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    subscribeObservers()
    subscribeListeners()
  }

  private fun subscribeObservers() {
    with(viewModel.viewState) {
      observe(isSaveButtonEnabled) { isEnabled ->
        binding.buttonSave.isEnabled = isEnabled
      }
      observe(getShoppingState) { state ->
        when (state) {
          is State.Loading -> binding.progressBar.isVisible = true
          is State.Failed -> binding.progressBar.isGone = true
          is State.Success -> {
            binding.progressBar.isGone = true
            updateView(viewModel.getShopping())
          }
        }
      }
      observe(saveShoppingState) { state ->
        when (state) {
          is State.Loading -> binding.progressBar.isVisible = true
          is State.Failed -> binding.progressBar.isGone = true
          is State.Success -> binding.progressBar.isGone = true
        }
      }
    }
  }

  private fun subscribeListeners() {
    binding.editName.afterTextChanged { name ->
      viewModel.afterNameChanged(name = name)
    }
    binding.editAmount.afterTextChanged { amount ->
      viewModel.afterAmountChanged(amount = amount.toInt())
    }
    binding.editBrand.afterTextChanged { brand ->
      viewModel.afterBrandChanged(brand = brand)
    }
    binding.editShelfLife.afterTextChanged { shelfLife ->
      viewModel.afterShelfLifeChanged(shelfLife = shelfLife)
    }

    binding.buttonSave.setOnClickListener {
      viewModel.saveShopping()
    }

  }

  private fun updateView(shopping: ShoppingViewData) {
    binding.textInputLayoutId.isVisible = true
    binding.editId.setText(shopping.id)
    binding.editName.setText(shopping.name)
    binding.editAmount.setText(shopping.amount.toString())
    binding.editBrand.setText(shopping.brand)
    binding.editShelfLife.setText(shopping.shelfLife)
  }

}
