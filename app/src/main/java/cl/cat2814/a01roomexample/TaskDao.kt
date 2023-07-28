package cl.cat2814.a01roomexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY id ASC")

    fun getTasks(): LiveData<List<Task>>
}
