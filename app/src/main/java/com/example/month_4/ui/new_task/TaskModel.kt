package com.example.month_4.ui.new_task

import androidx.room.*

data class TaskModel(
    val id: Int,
    val title: String,
    val description: String?
)

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "desc")
    val description: String? = null,
)

@Dao
interface TaskDao {

    @Delete
    fun delete(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id =:taskId")
    fun deleteBy(taskId: Long)

    @Insert
    fun insert(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskEntity>

}
