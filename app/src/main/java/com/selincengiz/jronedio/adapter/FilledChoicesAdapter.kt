package com.selincengiz.jronedio.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.R
import com.selincengiz.jronedio.databinding.MultichoicesFilledBinding
import com.selincengiz.jronedio.databinding.QuestionFilledBinding
import com.selincengiz.jronedio.model.MultiChoiceAnswer
import com.selincengiz.jronedio.model.Question

class FilledChoicesAdapter (private val questionList:List<MultiChoiceAnswer>) :
    RecyclerView.Adapter<FilledChoicesAdapter.FilledChoicesHolder>() {
    private lateinit var  option1: RadioGroup

    class FilledChoicesHolder(val binding: MultichoicesFilledBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilledChoicesHolder {
        val binding =
            MultichoicesFilledBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return FilledChoicesHolder(binding)

    }

    override fun onBindViewHolder(holder: FilledChoicesHolder, position: Int) {


      holder.binding.radioButton.text=questionList.get(position).answer

        holder.binding.radioButton.setOnClickListener {
            println("asdadads")
        }




    }

    override fun getItemCount(): Int {
        return questionList.size
    }


}