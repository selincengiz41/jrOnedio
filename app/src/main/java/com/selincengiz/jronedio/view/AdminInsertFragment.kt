package com.selincengiz.jronedio.view

import android.R
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.selincengiz.jronedio.adapter.QuestionAdapter
import com.selincengiz.jronedio.adapter.ResultBucketAdapter
import com.selincengiz.jronedio.databinding.FragmentAdminInsertBinding
import com.selincengiz.jronedio.databinding.FragmentAdminLoginBinding
import com.selincengiz.jronedio.model.Test


class AdminInsertFragment : Fragment(), QuestionAdapter.Listener {
    private var _binding: FragmentAdminInsertBinding? = null
    private val binding get() = _binding!!

    private lateinit var questionAdapter: QuestionAdapter
    private val questionSize: ArrayList<Int> = ArrayList()
    private lateinit var resultBucketAdapter: ResultBucketAdapter
    private val resultBucketSize: ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminInsertBinding.inflate(inflater, container, false)

        val view = binding.root
        //Question Adapter
        binding.questionRecycle.layoutManager = LinearLayoutManager(view.context)
        questionAdapter = QuestionAdapter(questionSize, this@AdminInsertFragment)
        binding.questionRecycle.adapter = questionAdapter

        //Result bucket adapter
        binding.bucketRecycle.layoutManager = LinearLayoutManager(view.context)
        resultBucketAdapter = ResultBucketAdapter(resultBucketSize)
        binding.bucketRecycle.adapter = resultBucketAdapter

        return view;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jsonLayout.isVisible=false


        ////// Oluştur Buton Animasyonu
        val button = binding.buttonCreate
        val colorFrom = resources.getColor(R.color.black)
        val colorTo = resources.getColor(R.color.darker_gray)
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

        ////// Manuel Buton Animasyonu
        val button2 = binding.manuelButton

        val colorAnimation3 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation3.duration = 2000 // milliseconds
        colorAnimation3.addUpdateListener { animator -> button2.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation4 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation4.duration = 2000 // milliseconds
        colorAnimation4.addUpdateListener { animator -> button2.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation3.start();
        colorAnimation3.doOnEnd {
            colorAnimation4.start()

        }
        colorAnimation4.doOnEnd {
            colorAnimation3.start()

        }

        ////// Json Buton Animasyonu
        val button3 = binding.jsonButton

        val colorAnimation5 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation5.duration = 2000 // milliseconds
        colorAnimation5.addUpdateListener { animator -> button3.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation6 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation6.duration = 2000 // milliseconds
        colorAnimation6.addUpdateListener { animator -> button3.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation5.start();
        colorAnimation5.doOnEnd {
            colorAnimation6.start()

        }
        colorAnimation6.doOnEnd {
            colorAnimation5.start()

        }

        //// Manuel butonu aktif

        binding.manuelButton.setOnClickListener {
            binding.manuelLayout.isVisible=true
            binding.jsonLayout.isVisible=false
            var params= binding.buttonCreate.layoutParams as ConstraintLayout.LayoutParams
            params.topToBottom=binding.manuelLayout.id

            binding.buttonCreate.requestLayout()
        }

        ////Json butonu aktif
        binding.jsonButton.setOnClickListener {
            binding.manuelLayout.isVisible=false
            binding.jsonLayout.isVisible=true
            var params= binding.buttonCreate.layoutParams as ConstraintLayout.LayoutParams
            params.topToBottom=binding.jsonLayout.id

            binding.buttonCreate.requestLayout()
        }
        ///////// Soru ekleme

        binding.addQuestion.setOnClickListener {
            questionSize.add(1)
            questionAdapter.notifyDataSetChanged()
            binding.root.requestLayout()
        }

        ///////// Result bucket ekleme

        binding.addBucket.setOnClickListener {
            resultBucketSize.add(1)
            resultBucketAdapter.notifyDataSetChanged()
            binding.root.requestLayout()
        }


            /////// Oluştur butonu

            binding.buttonCreate.setOnClickListener {
                val jsonString= binding.json.text.toString()
                val gson = Gson()
                val test = gson.fromJson(jsonString, Test::class.java)
                //Database json verisi gönder
                val database = Firebase.database
                val myRef = database.getReference(test.header.titleText)

                myRef.setValue(jsonString)
            }


    }

    override fun onItemClick(questionList: ArrayList<Int>) {

    }


}