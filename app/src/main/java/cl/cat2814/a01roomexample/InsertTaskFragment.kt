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
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInsertTaskBinding.inflate(layoutInflater, container, false)

        initRepository()

        initListener()

        loadTasks()

        return binding.root
    }

    private fun initRepository() {
        repository = Repository(TaskDatabase.getDatabase(requireContext()).getTaskDao())
    }

    private fun initListener() {
        binding.btSaveTask.setOnClickListener {
            val task = binding.etInsertTask.text.toString()
            saveTask(task)
        }
    }

    private fun saveTask(taskText: String) {
        val task = Task(taskText)
        GlobalScope.launch { repository.insertTask(task) }
    }

    private fun loadTasks() {
        repository.getTasks().observe(requireActivity()) {
            val tasksAsText = it.joinToString("\n") { it.name }
            binding.tvTaskList.text = tasksAsText
        }
    }
}
