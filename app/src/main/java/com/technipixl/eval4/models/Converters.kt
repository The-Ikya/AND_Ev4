package com.technipixl.eval4.models

import androidx.room.TypeConverter
import java.util.*

class Converters {
	@TypeConverter
	fun fromTimestamp(value: Long) = Date(value)

	@TypeConverter
	fun toTimestamp(date: Date) = date.time
}