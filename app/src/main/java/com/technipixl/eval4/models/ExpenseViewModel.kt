package com.technipixl.eval4.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData

class ExpenseViewModel(application: Application = Application()) : AndroidViewModel(application) {

	private val repository = ExpensesRepository(application)

	val expenses get() = repository.getExpenses().asLiveData()

	fun newExpense(expense: Expense) {
		repository.createExpense(expense)
	}

	fun getTypes() = repository.getTypes().asLiveData()?.value
}