package com.technipixl.eval4.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity
data class Expense(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	var name: String,
	var date: Date,
	var value: Float,
	val type: Long
)

@Dao
interface ExpenseDao {

	@Query("SELECT * FROM expense")
	fun getAll() : Flow<List<Expense>>

	@Query("SELECT * FROM expense WHERE id in (:id)")
	fun findById(id: Long) : Flow<List<Expense>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(expense: Expense)

	@Insert
	fun insertAll(vararg expense: Expense)

	@Delete
	fun remove(expense: Expense)
}
