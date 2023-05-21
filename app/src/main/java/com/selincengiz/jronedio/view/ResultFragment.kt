package com.selincengiz.jronedio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.selincengiz.jronedio.R
import com.selincengiz.jronedio.databinding.FragmentAdminLoginBinding
import com.selincengiz.jronedio.databinding.FragmentResultBinding
import com.selincengiz.jronedio.model.Test
import com.selincengiz.jronedio.singleton.DataManager


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var test: Test
    private  var buckets:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        val view = binding.root

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            test= ResultFragmentArgs.fromBundle(it).test
        }


        ///// test içerisinden cevapların bucketlarını almak
      for (i in test.questions)  {

         for(j in i.multiChoiceAnswers){
             if(  DataManager.filledTest.get(i.question).equals(j.answer)){
               buckets.addAll(j.buckets)
             }

         }
      }
        for (i in buckets){
            println(i)
        }

        ////// bucketlara göre test sonucunu bulmak
        val countMap = HashMap<String, Int>()
        for (element in buckets) {
            if (countMap.containsKey(element)) {
                countMap[element] = countMap[element]!! + 1
            } else {
                countMap[element] = 1
            }
        }
        var maxElement = ""
        var maxCount = 0

        for ((element, count) in countMap) {
            if (count > maxCount) {
                maxElement = element
                maxCount = count
            }
        }

        println("sonuç ${maxElement}")

        for(i in test.resultBuckets){
            if(i.id.equals(maxElement)){
                binding.result.text=i.title
                binding.description.text=i.share
            }
        }


    }


}