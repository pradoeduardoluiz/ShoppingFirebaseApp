package com.pos.pucpr.shoppinglistapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pos.pucpr.shoppinglistapp.R
import com.pos.pucpr.shoppinglistapp.common.ext.observe
import com.pos.pucpr.shoppinglistapp.common.ext.showDialog
import com.pos.pucpr.shoppinglistapp.databinding.ShoppingListFragmentBinding
import com.pos.pucpr.shoppinglistapp.ui.list.controllers.ShoppingListController
import com.pos.pucpr.shoppinglistapp.ui.view_data.ShoppingViewData
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShoppingListFragment : Fragment(), ShoppingListController.OnClickListener {

  private val viewModel: ShoppingListViewModel by viewModel { parametersOf(findNavController()) }
  private lateinit var binding: ShoppingListFragmentBinding
  private val controller: ShoppingListController by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    controller.setListener(listener = this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = ShoppingListFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onResume() {
    super.onResume()
    viewModel.getAllShopping()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
    subscribeObservers()
    subscribeListeners()
  }

  private fun subscribeObservers() {
    with(viewModel.viewState) {
      observe(getAllShoppingState) { state ->
//        when (state) {
//          is State.Loading -> TODO()
//          is State.Success -> TODO()
//          is State.Failed -> TODO()
//        }
      }
      observe(shopping) { list ->
        controller.setData(list)
      }
    }
  }

  private fun subscribeListeners() {
    binding.fabAddItem.setOnClickListener {
      viewModel.navigateToDetails(id = null)
    }
  }

  private fun setupRecyclerView() {
    binding.recyclerView.adapter = controller.adapter
    binding.recyclerView.setHasFixedSize(true)
  }

  override fun onClickListener(shoppingItem: ShoppingViewData) {
    viewModel.navigateToDetails(shoppingItem.id)
  }

  override fun onDeleteListener(shoppingItem: ShoppingViewData) {
    showDeleteDialog {
      viewModel.deleteShopping(shoppingItem)
    }
  }

  private fun showDeleteDialog(onConfirmed: () -> Unit) {
    requireContext().showDialog(
      title = R.string.title_delete_shopping,
      message = R.string.message_delete_shopping,
      positiveButton = R.string.action_confirm,
      negativeButton = R.string.action_cancel,
      onPositiveButtonClick = {
        onConfirmed.invoke()
      }
    )
  }

}