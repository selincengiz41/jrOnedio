package com.selincengiz.jronedio.adapter

import android.R
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.CategorizedTestBinding
import com.selincengiz.jronedio.databinding.QuestionBinding
import com.selincengiz.jronedio.model.Test
import com.selincengiz.jronedio.view.CategoryTestFragmentDirections

class CategorizedTestAdapter(private val testList: ArrayList<Test>) :
    RecyclerView.Adapter<CategorizedTestAdapter.CategorizedTestHolder>() {

    class CategorizedTestHolder(val binding: CategorizedTestBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorizedTestHolder {
        val binding =
            CategorizedTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategorizedTestHolder(binding)

    }

    override fun onBindViewHolder(holder: CategorizedTestHolder, position: Int) {

        holder.binding.button.text=testList.get(position).header.titleText
        //////  Buton Animasyonu
        val button3 = holder.binding.button
        val colorFrom = holder.binding.root.resources.getColor(R.color.black)
        val colorTo = holder.binding.root.resources.getColor(R.color.system_accent1_900)
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

        holder.binding.button.setOnClickListener {

            val action = CategoryTestFragmentDirections.actionCategoryTestFragmentToInsideTestFragment(testList.get(position))
            Navigation.findNavController(holder.binding.root).navigate(action)
        }



    }

    override fun getItemCount(): Int {
        return testList.size
    }


}