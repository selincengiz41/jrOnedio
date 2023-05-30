package com.selincengiz.jronedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.QuestionBinding
import com.selincengiz.jronedio.databinding.ResultBucketBinding

class ResultBucketAdapter (private val resultBucketList: ArrayList<Int>) :
    RecyclerView.Adapter<ResultBucketAdapter.ResultBucketHolder>() {

    class ResultBucketHolder(val binding: ResultBucketBinding) : RecyclerView.ViewHolder(binding.root) {


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultBucketHolder {
        val binding =
            ResultBucketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultBucketHolder(binding)

    }
    override fun onBindViewHolder(holder: ResultBucketHolder, position: Int) {


        //////Result Bucket silme
        holder.binding.closeResultBucket.setOnClickListener {
            resultBucketList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, resultBucketList.size)
            notifyDataSetChanged()


        }

    }

    override fun getItemCount(): Int {
        return resultBucketList.size
    }


}