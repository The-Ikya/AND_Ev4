package com.technipixl.eval4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.technipixl.eval4.models.*

@Database(entities = [Expense::class, ExpenseType::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ExpenseDB : RoomDatabase() {

	companion object {
		@Volatile
		private var sharedInstance : ExpenseDB? = null

		fun getDB(context: Context) : ExpenseDB {
			if (sharedInstance != null) return sharedInstance!!
			synchronized(this) {
				sharedInstance = Room
					.databaseBuilder(context, ExpenseDB::class.java, "Expenses.db")
					.fallbackToDestructiveMigration()
					.build()
				return sharedInstance!!
			}
		}
	}

	abstract fun expenseDao() : ExpenseDao
	abstract fun typeDao() : ExpenseTypeDao
}