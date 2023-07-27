package cl.cat2814.a01roomexample

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)
}