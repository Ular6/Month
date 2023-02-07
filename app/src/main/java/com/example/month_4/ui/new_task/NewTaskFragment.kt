package com.example.month_4.ui.new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.month_4.data.MainApplication
import com.example.month_4.databinding.FragmentNewTaskBinding

class NewTaskFragment : Fragment() {

    private var binding: FragmentNewTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding?.btnSave?.setOnClickListener {

            val entity = TaskEntity(
                title = binding?.etTitle?.text.toString(),
                description = binding?.etDescription?.text?.toString()
            )

            MainApplication.appDataBase?.taskDao?.insert(entity)
            findNavController().navigateUp()

        }
    }
}