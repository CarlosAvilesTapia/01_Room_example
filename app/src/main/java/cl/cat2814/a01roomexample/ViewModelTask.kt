package cl.cat2814.a01roomexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelTask(application: Application): AndroidViewModel(application) {

    private val repository: Repository

    init {
        repository = Repository(TaskDatabase.getDatabase(application).getTaskDao())
    }

    fun getTaskList(): LiveData<List<Task>> {
        return repository.getTasks()
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }
}
