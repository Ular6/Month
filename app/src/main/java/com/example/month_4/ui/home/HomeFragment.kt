package com.example.month_4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.month_4.R
import com.example.month_4.databinding.FragmentHomeBinding
import com.example.month_4.data.MainApplication
import com.example.month_4.ui.new_task.TaskAdapter
import com.example.month_4.ui.new_task.TaskModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val taskAdapter = TaskAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        getActualTasks()

    }

    private fun initViews() {
        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
        taskAdapter.onDelete = {

            AlertDialog.Builder(requireContext())
                .setPositiveButton("Да") { _, _ ->
                    MainApplication.appDataBase?.taskDao?.deleteBy(
                        it.toLong()
                    )
                    getActualTasks()
                }
                .setNegativeButton("Нет"){ _, _ ->

                }
                .setTitle("Удаление!")
                .setMessage("Вы точно хотите удалить?")
                .create()
                .show()
        }
    }

    private fun getActualTasks() {
        lifecycleScope.launch {
            val tasks = MainApplication.appDataBase?.taskDao?.getAll()
            val taskModels = tasks?.map {
                TaskModel(
                    id = it.id.toInt(),
                    title = it.title,
                    description = it.description,
                )
            }
            taskModels?.let {
                taskAdapter.renew(it)
            }
        }
    }

    private fun initListeners() {
        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }
    }
}

