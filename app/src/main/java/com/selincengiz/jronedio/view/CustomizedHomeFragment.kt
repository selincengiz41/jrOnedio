package com.selincengiz.jronedio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.selincengiz.jronedio.R
import com.selincengiz.jronedio.adapter.CategorizedTestAdapter
import com.selincengiz.jronedio.databinding.FragmentCategoryTestBinding
import com.selincengiz.jronedio.databinding.FragmentCustomizedHomeBinding
import com.selincengiz.jronedio.model.Test
import com.selincengiz.jronedio.singleton.DataManager


class CustomizedHomeFragment : Fragment() {
private lateinit var subjects:Array<String>
    private  var categories:ArrayList<String> = ArrayList()
    private var _binding: FragmentCustomizedHomeBinding? = null
    private val binding get() = _binding!!
    private var category:String?=""
    private var categorizedTestList :ArrayList<Test> = ArrayList()
    private lateinit var categorizedTestAdapter : CategorizedTestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCustomizedHomeBinding.inflate(inflater, container, false)

        val view = binding.root

        //Category Test Adapter
        binding.recycle.layoutManager = LinearLayoutManager(view.context)
        categorizedTestAdapter = CategorizedTestAdapter(categorizedTestList)
        binding.recycle.adapter = categorizedTestAdapter

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
           subjects= CustomizedHomeFragmentArgs.fromBundle(it).subjects
        }
        for(i in subjects){
            if(i.equals("Astroloji"))
                categories.add("astroloji")
            if(i.equals("Kişilik"))
                categories.add("personality")
            if(i.equals("Psikoloji"))
                categories.add("psikoloji")
            if(i.equals("Yemek"))
                categories.add("yemek")
            if(i.equals("Genel Kültür"))
                categories.add("kultur")
            if(i.equals("İliski"))
                categories.add("relationship")

        }


        /////// Kategorize edilmiş testleri al
        for(item in DataManager.data) {
            for(i in categories){
                if(i==item.quizType){
                    println()
                    categorizedTestList.add(item)
                    categorizedTestAdapter.notifyDataSetChanged()
                }
            }


        }

    }
}