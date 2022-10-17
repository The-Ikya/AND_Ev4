package com.technipixl.eval4.views.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.eval4.models.Expense
import com.technipixl.eval4.models.ExpenseWithType
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

@BindingAdapter("listData")
fun bindExpenseRecyclerView(recyclerView: RecyclerView, data: List<Expense>?) {
	val adapter = recyclerView.adapter as ExpenseListAdapter
	adapter.submitList(data)
}

@BindingAdapter("dateString")
fun dateFormatting(view: TextView, date: Date) {
	view.text = DateFormat.getDateInstance().format(date)
}

@BindingAdapter("price")
fun priceFormatting(view: TextView, value: Float) {
	view.text = NumberFormat.getCurrencyInstance().format(value)
}

