package cl.cat2814.a01roomexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    val name: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
