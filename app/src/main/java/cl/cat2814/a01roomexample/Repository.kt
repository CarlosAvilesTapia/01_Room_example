package cl.cat2814.a01roomexample

import androidx.lifecycle.LiveData

class Repository(private val taskDao: TaskDao) {
    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    fun getTasks(): LiveData<List<Task>> {
        return taskDao.getTasks()
    }
}
