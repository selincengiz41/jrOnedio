package com.selincengiz.jronedio.view

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.selincengiz.jronedio.R
import com.selincengiz.jronedio.adapter.CategorizedTestAdapter
import com.selincengiz.jronedio.adapter.FilledQuestionAdapter
import com.selincengiz.jronedio.adapter.QuestionAdapter
import com.selincengiz.jronedio.databinding.FragmentAdminInsertBinding
import com.selincengiz.jronedio.databinding.FragmentCategoryTestBinding
import com.selincengiz.jronedio.databinding.FragmentInsideTestBinding
import com.selincengiz.jronedio.model.Question
import com.selincengiz.jronedio.model.Test


class InsideTestFragment : Fragment(), FilledQuestionAdapter.Listener {
    private var _binding: FragmentInsideTestBinding? = null
    private val binding get() = _binding!!
    private lateinit var test: Test
    private lateinit var filledQuestionAdapter: FilledQuestionAdapter
    private val questionSize: ArrayList<Question> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentInsideTestBinding.inflate(inflater, container, false)

        val view = binding.root
//Filled Question Adapter
        binding.InsideTestRecycler.layoutManager = LinearLayoutManager(view.context)
        filledQuestionAdapter = FilledQuestionAdapter(questionSize , this@InsideTestFragment)
        binding.InsideTestRecycler.adapter = filledQuestionAdapter




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            test= InsideTestFragmentArgs.fromBundle(it).test
            questionSize.addAll(test.questions)
            filledQuestionAdapter.notifyDataSetChanged()
            binding.root.requestLayout()
        }
        ////// Finish Buton Animasyonu
        val button = binding.finishButton
        val colorFrom = resources.getColor(android.R.color.black)
        val colorTo = resources.getColor(android.R.color.darker_gray)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 2000 // milliseconds
        colorAnimation.addUpdateListener { animator -> button.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation2 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation2.duration = 2000 // milliseconds
        colorAnimation2.addUpdateListener { animator -> button.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation.start();
        colorAnimation.doOnEnd {
            colorAnimation2.start()

        }
        colorAnimation2.doOnEnd {
            colorAnimation.start()

        }





            //////////
            binding.question.text=test.header.titleText


    }

    override fun onItemClick(question: String, choices: String) {


    }


}