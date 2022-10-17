package com.technipixl.eval4.models

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseWithType(
	@Embedded val expense: Expense,
	@Relation(
		parentColumn = "id",
		entityColumn = "typeId"
	)
	val type: ExpenseType
)

