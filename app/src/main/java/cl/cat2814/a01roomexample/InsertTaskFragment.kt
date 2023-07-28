package cl.cat2814.a01roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import cl.cat2814.a01roomexample.databinding.FragmentInsertTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertTaskFragment : Fragment() {

    lateinit var binding: FragmentInsertTaskBinding
    private val viewModelTask: ViewModelTask by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        val task = Task(taskText)
        viewModelTask.insertTask(task)
    }

    private fun loadTasks() {
        viewModelTask.getTaskList().observe(viewLifecycleOwner) {
            val tasksAsText = it.joinToString("\n") { it.name }
            binding.tvTaskList.text = tasksAsText
        }
    }
}
