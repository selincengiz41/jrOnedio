package com.selincengiz.jronedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.AnswerBinding
import com.selincengiz.jronedio.databinding.QuestionBinding

class AnswerAdapter (private val answerList: ArrayList<Int>) :
    RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {
    private lateinit var bucketAdapter: BucketAdapter
    private val bucketSize: ArrayList<Int> = ArrayList()
    class AnswerHolder(val binding: AnswerBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val binding =
            AnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerHolder(binding)

    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {


        //////Bucket adapteri bağlama
        holder.binding.bucketRecycle.layoutManager = LinearLayoutManager(holder.binding.root.context)
        bucketAdapter = BucketAdapter(bucketSize)
        holder.binding.bucketRecycle.adapter = bucketAdapter



        /////Seçenek Silme
        holder.binding.closeAnswer.setOnClickListener {

            answerList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, answerList.size)
            notifyDataSetChanged()


        }

        /////Bucket ekleme
        holder.binding.addBucket.setOnClickListener {
            bucketSize.add(1)
            bucketAdapter.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return answerList.size
    }


}