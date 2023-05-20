package com.selincengiz.jronedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.AnswerBinding
import com.selincengiz.jronedio.databinding.QuestionBinding
import com.selincengiz.jronedio.databinding.QuestionFilledBinding
import com.selincengiz.jronedio.databinding.ResultBucketBinding
import com.selincengiz.jronedio.model.MultiChoiceAnswer
import com.selincengiz.jronedio.model.Question
import com.selincengiz.jronedio.model.Test
import okhttp3.internal.notify

class FilledQuestionAdapter(private val questionList:List<Question>) :
    RecyclerView.Adapter<FilledQuestionAdapter.FilledQuestionHolder>() {



    class FilledQuestionHolder(val binding: QuestionFilledBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilledQuestionHolder {
        val binding =
            QuestionFilledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilledQuestionHolder(binding)

    }

    override fun onBindViewHolder(holder: FilledQuestionHolder, position: Int) {
         var filledChoicesAdapter: FilledChoicesAdapter
        var multichoiceSize: ArrayList<MultiChoiceAnswer> = ArrayList()
        holder.binding.textView.text=questionList.get(position).question

        //////Filledchoice adapteri bağlama
        holder.binding.insideTestRecycler.layoutManager = LinearLayoutManager(holder.binding.root.context)
        filledChoicesAdapter = FilledChoicesAdapter(multichoiceSize)
        holder.binding.insideTestRecycler.adapter = filledChoicesAdapter

        /////Filledchoice ekleme

        multichoiceSize.addAll(questionList.get(position).multiChoiceAnswers)
        filledChoicesAdapter.notifyDataSetChanged()




    }

    override fun getItemCount(): Int {
        return questionList.size
    }


}