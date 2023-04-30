package com.selincengiz.jronedio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.selincengiz.jronedio.R
import com.selincengiz.jronedio.adapter.CategorizedTestAdapter
import com.selincengiz.jronedio.adapter.QuestionAdapter
import com.selincengiz.jronedio.databinding.FragmentAdminLoginBinding
import com.selincengiz.jronedio.databinding.FragmentCategoryTestBinding
import com.selincengiz.jronedio.model.Test
import com.selincengiz.jronedio.singleton.DataManager


class CategoryTestFragment : Fragment() {
    private var _binding: FragmentCategoryTestBinding? = null
    private val binding get() = _binding!!
    private var category:String?=""
    private var categorizedTestList :ArrayList<Test> = ArrayList()
    private lateinit var categorizedTestAdapter :CategorizedTestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryTestBinding.inflate(inflater, container, false)

        val view = binding.root

        //Category Test Adapter
        binding.categorizedRecycler.layoutManager = LinearLayoutManager(view.context)
        categorizedTestAdapter = CategorizedTestAdapter(categorizedTestList)
        binding.categorizedRecycler.adapter = categorizedTestAdapter

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            category = it.getString("type")
        }


        /////// Kategorize edilmiş testleri al
       for(item in DataManager.data) {
           if(category==item.quizType){
               categorizedTestList.add(item)
               categorizedTestAdapter.notifyDataSetChanged()
           }

       }




    }


}