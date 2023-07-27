package cl.cat2814.a01roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.cat2814.a01roomexample.databinding.FragmentInsertTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertTaskFragment : Fragment() {

    lateinit var binding: FragmentInsertTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInsertTaskBinding.inflate(layoutInflater, container, false)

        initListener()

        loadTasks()

        return binding.root
    }

    private fun initListener() {
        binding.btSaveTask.setOnClickListener {
            val task = binding.etInsertTask.text.toString()
            saveTask(task)
        }
    }

    private fun saveTask(taskText: String) {
        val taskDao = TaskDatabase.getDatabase(requireContext()).getTaskDao()
        val task = Task(taskText)
        GlobalScope.launch { taskDao.insertTask(task) }
    }

    private fun loadTasks() {
        val taskDao = TaskDatabase.getDatabase(requireContext()).getTaskDao()
        GlobalScope.launch {
            val tasks = taskDao.getTasks()
            val tasksAsText = tasks.joinToString("\n") { it.name }
            binding.tvTaskList.text = tasksAsText
        }
    }
}
