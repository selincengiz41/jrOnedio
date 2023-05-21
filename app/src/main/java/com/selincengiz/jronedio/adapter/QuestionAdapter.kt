package com.selincengiz.jronedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.QuestionBinding

class QuestionAdapter (private val questionList: ArrayList<Int>, private val listener: Listener) :
    RecyclerView.Adapter<QuestionAdapter.QuestionHolder>() {
    interface Listener {
        fun onItemClick(questionList: ArrayList<Int>)
    }
    private lateinit var answerAdapter: AnswerAdapter

    class QuestionHolder(val binding: QuestionBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val binding =
            QuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionHolder(binding)

    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
         val answerSize: ArrayList<Int> = ArrayList()

       ///////Seçenek bağlama
        holder.binding.answersRecycle.layoutManager = LinearLayoutManager(holder.binding.root.context)
        answerAdapter = AnswerAdapter(answerSize)
        holder.binding.answersRecycle.adapter = answerAdapter

        //////Soru silme
        holder.binding.closeQuestion.setOnClickListener {
            questionList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, questionList.size)
            notifyDataSetChanged()
            listener.onItemClick(questionList)

        }

        /////Seçenek ekleme
        holder.binding.addAnswer.setOnClickListener {
            answerSize.add(1)
            answerAdapter.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }


}