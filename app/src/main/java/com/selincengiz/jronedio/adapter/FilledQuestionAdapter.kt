package com.selincengiz.jronedio.adapter


import android.R
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.jronedio.databinding.QuestionFilledBinding
import com.selincengiz.jronedio.model.Question


class FilledQuestionAdapter(private val questionList:List<Question>, private val listener: Listener) :
    RecyclerView.Adapter<FilledQuestionAdapter.FilledQuestionHolder>() {
    interface Listener {
        fun onItemClick(question: String, choices :String)

    }


    class FilledQuestionHolder(val binding: QuestionFilledBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilledQuestionHolder {
        val binding =
            QuestionFilledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilledQuestionHolder(binding)

    }

    override fun onBindViewHolder(holder: FilledQuestionHolder, position: Int) {

        holder.binding.textView.text=questionList.get(position).question
        var radioButtonList: ArrayList<RadioButton> = ArrayList<RadioButton>()

        val colorStateList = ColorStateList(
            arrayOf(intArrayOf(R.attr.state_enabled)),
            intArrayOf(holder.binding.root.resources.getColor(R.color.black))
        )
        for(item in questionList.get(position).multiChoiceAnswers){

            val option1: RadioButton = RadioButton(holder.binding.root.context)
            option1.id = View.generateViewId()
            option1.text=item.answer
            option1.buttonTintList=colorStateList
            radioButtonList.add(option1)
            holder.binding.insideTestGroup.addView(option1)
        }
        holder.binding.insideTestGroup.setOnCheckedChangeListener { radioGroup, i ->
           listener.onItemClick(questionList.get(position).question ,holder.binding.root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())
            println(questionList.get(position).question )
           println(holder.binding.root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())

        }




    }

    override fun getItemCount(): Int {
        return questionList.size
    }


}