package com.example.month_4.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.month_4.ui.new_task.TaskDao
import com.example.month_4.ui.new_task.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 2,
)
abstract class AppDataBase: RoomDatabase()  {
    abstract val taskDao: TaskDao

}
