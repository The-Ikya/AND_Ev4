package com.technipixl.eval4.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.eval4.databinding.ExpenseCellBinding
import com.technipixl.eval4.models.Expense

class ExpenseListAdapter : ListAdapter<Expense, ExpenseListAdapter.ExpenseListHolder>(DiffCallBack) {

	companion object DiffCallBack : ItemCallback<Expense>() {
		override fun areItemsTheSame(oldItem: Expense, newItem: Expense) =
			oldItem.id == newItem.id

		override fun areContentsTheSame(oldItem: Expense, newItem: Expense) =
			oldItem.name == newItem.name &&
					oldItem.date == newItem.date &&
					oldItem.value == newItem.value
	}

	class ExpenseListHolder(private val binding: ExpenseCellBinding)
		: RecyclerView.ViewHolder(binding.root) {
		fun bind(expense: Expense) {
			binding.expense = expense
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		ExpenseListHolder(
			ExpenseCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)

	override fun onBindViewHolder(holder: ExpenseListHolder, position: Int) {
		holder.bind(getItem(position))
	}
}