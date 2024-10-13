package com.coding.meet.todo_app.converters

import androidx.room.TypeConverter
import java.util.Date

// TypeConverter class to handle conversion between Long timestamps and Date objects
class TypeConverter {

    // Converts a timestamp (Long) to a Date object
    @TypeConverter
    fun fromTimestampToDate(value: Long): Date {
        return Date(value) // Create and return a Date object using the given timestamp
    }

    // Converts a Date object to a timestamp (Long)
    @TypeConverter
    fun dateToTimestamp(value: Date): Long {
        return value.time // Return the timestamp (in milliseconds) of the Date object
    }
}
