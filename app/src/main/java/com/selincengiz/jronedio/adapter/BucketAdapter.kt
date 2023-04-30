package com.selincengiz.jronedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.AnswerBinding
import com.selincengiz.jronedio.databinding.BucketBinding

class BucketAdapter(private val bucketList: ArrayList<Int>) :
    RecyclerView.Adapter<BucketAdapter.BucketHolder>() {

    class BucketHolder(val binding: BucketBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketHolder {
        val binding =
            BucketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BucketHolder(binding)

    }

    override fun onBindViewHolder(holder: BucketHolder, position: Int) {

        /////Seçenek Silme
        holder.binding.closeBucket.setOnClickListener {
            bucketList.removeAt(position)

            notifyItemRemoved(position)

            notifyItemRangeChanged(position, bucketList.size)
            notifyDataSetChanged()


        }
    }

    override fun getItemCount(): Int {
        return bucketList.size
    }


}