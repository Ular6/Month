package com.example.month_4.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.month_4.R
import com.example.month_4.databinding.FragmentProfileBinding
import com.example.month_4.ui.utils.Preferences

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private var imagePicker = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            Preferences(requireContext()).saveProfilePicture(uri)
        }
            binding?.imageView?.setImageURI(uri)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initViews()

    }

    private fun initViews() {
        val pref = Preferences(requireContext())

        pref.getProfilePicture()?.let {
            Glide.with(requireActivity())
                .load(it)
                .circleCrop()
                .into(binding?.imageView!!)
        }
       pref.getProfileName()?.let {
            binding?.etText?.setText(it)
        }
    }

    private fun initListeners() {
        binding?.editPen?.setOnClickListener {
            imagePicker.launch("image/*")
        }
        binding?.btnSave?.setOnClickListener {
            val name = binding?.etText?.text.toString()
            Preferences(requireContext()).saveProfileName(name)
        }
        binding?.logOut?.setOnClickListener {
            findNavController().navigate(R.id.authFragment)
        }
    }
}
