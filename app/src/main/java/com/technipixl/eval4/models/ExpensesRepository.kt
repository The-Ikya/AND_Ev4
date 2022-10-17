package com.technipixl.eval4.models

import android.app.Application
import androidx.lifecycle.asLiveData
import com.technipixl.eval4.ExpenseDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class ExpensesRepository(application: Application) {
	private val db = ExpenseDB.getDB(application.applicationContext)

	init {
		CoroutineScope(Dispatchers.IO).launch {
			if (getTypes().first().isEmpty()) {
				createType(ExpenseType(name = "Tax"))
				createType(ExpenseType(name = "Oil"))
				createType(ExpenseType(name = "Repairs"))
			}

			if (getExpenses().first().isEmpty()) {
				createExpense(
					Expense(
						name = "test",
						date = Date(),
						value = 1f,
						type = 0
					)
				)
				createExpense(
					Expense(
						name = "test2",
						date = Date(),
						value = 2f,
						type = 0
					)
				)
			}
		}
	}

	fun getExpenses() = db.expenseDao().getAll()

	fun createExpense(expense: Expense) {
		db.expenseDao().insert(expense)
	}

	fun getTypes() = db.typeDao().getAll()

	fun createType(type: ExpenseType) {
		db.typeDao().insert(type)
	}
}