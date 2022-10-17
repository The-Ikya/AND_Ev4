package com.technipixl.eval4.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class ExpenseType(
	@PrimaryKey(autoGenerate = true)
	val typeId: Long = 0,
	val name: String
)

@Dao
interface ExpenseTypeDao {

	@Query("SELECT * FROM expensetype")
	fun getAll() : Flow<List<ExpenseType>>

	@Query("SELECT * FROM expensetype WHERE typeId in (:id)")
	fun findById(id: Long) : Flow<List<ExpenseType>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(type: ExpenseType)

	@Insert
	fun insertAll(vararg type: ExpenseType)

	@Delete
	fun remove(type: ExpenseType)
}
