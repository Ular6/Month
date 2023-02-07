package com.example.month_4.ui.onBoarding

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.month_4.databinding.FragmentOnBoardPageBinding

class OnBoardPageFragment : Fragment() {
    companion object {
        const val IS_LAST_ARG = "is_last"
    }

    private var binding: FragmentOnBoardPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardPageBinding.inflate(LayoutInflater.from(context), container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
    }

    private fun initViews() {
        binding?.springDotsIndicator?.attachTo(
            (parentFragment as OnBoardListener).getViewPager()
        )

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(BoardModel.ARG_KEY, BoardModel::class.java)
        } else {
            arguments?.getSerializable(BoardModel.ARG_KEY) as BoardModel
        }
        val isLast = arguments?.getBoolean(IS_LAST_ARG)

        if (data != null) {
            binding?.animView?.setAnimation(
                data.imageResId
            )
            binding?.tvTitle?.text = data.title
            binding?.tvDesc?.text = data.description

            if (isLast == true) {
                binding?.apply {
                    btnSkip.visibility = View.GONE
                    btnNext.visibility = View.GONE
                    btnStart.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initListeners() {
        binding?.btnSkip?.setOnClickListener {
            (parentFragment as OnBoardListener).onSkipClicked()
        }
        binding?.btnNext?.setOnClickListener {
            (parentFragment as OnBoardListener).onNextClicked()
        }
        binding?.btnStart?.setOnClickListener {
            (parentFragment as OnBoardListener).onStartClicked()
        }
    }

    interface OnBoardListener {
        fun onSkipClicked()
        fun onNextClicked()
        fun onStartClicked()
        fun getViewPager(): ViewPager2

    }
}
