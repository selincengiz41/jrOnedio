package com.selincengiz.jronedio.view

import android.R
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.navigation.Navigation
import com.selincengiz.jronedio.databinding.FragmentAdminLoginBinding
import com.selincengiz.jronedio.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val view = binding.root

        return view;    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        //////Buton Animasyonu
        val colorFrom = resources.getColor(R.color.black)
        val colorTo = resources.getColor(R.color.darker_gray)
        val button3 = binding.astrolojibutton

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

        val button4 = binding.genelkulturbutton

        val colorAnimation7 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation7.duration = 2000 // milliseconds
        colorAnimation7.addUpdateListener { animator -> button4.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation8 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation8.duration = 2000 // milliseconds
        colorAnimation8.addUpdateListener { animator -> button4.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation7.start();
        colorAnimation7.doOnEnd {
            colorAnimation8.start()

        }
        colorAnimation8.doOnEnd {
            colorAnimation7.start()

        }


        val button5 = binding.iliskibutton

        val colorAnimation9 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation9.duration = 2000 // milliseconds
        colorAnimation9.addUpdateListener { animator -> button5.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation10 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation10.duration = 2000 // milliseconds
        colorAnimation10.addUpdateListener { animator -> button5.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation9.start();
        colorAnimation9.doOnEnd {
            colorAnimation10.start()

        }
        colorAnimation10.doOnEnd {
            colorAnimation9.start()

        }
        val button6 = binding.kisilikbutton

        val colorAnimation1 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation1.duration = 2000 // milliseconds
        colorAnimation1.addUpdateListener { animator -> button6.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation2 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation2.duration = 2000 // milliseconds
        colorAnimation2.addUpdateListener { animator -> button6.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation1.start();
        colorAnimation1.doOnEnd {
            colorAnimation2.start()

        }
        colorAnimation2.doOnEnd {
            colorAnimation1.start()

        }

        val button7 = binding.psikolojibutton

        val colorAnimation3 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation3.duration = 2000 // milliseconds
        colorAnimation3.addUpdateListener { animator -> button7.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation4 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation4.duration = 2000 // milliseconds
        colorAnimation4.addUpdateListener { animator -> button7.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation3.start();
        colorAnimation3.doOnEnd {
            colorAnimation4.start()

        }
        colorAnimation4.doOnEnd {
            colorAnimation3.start()

        }

        val button8 = binding.yemekbutton

        val colorAnimation11 = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation11.duration = 2000 // milliseconds
        colorAnimation11.addUpdateListener { animator -> button8.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation12 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation12.duration = 2000 // milliseconds
        colorAnimation12.addUpdateListener { animator -> button8.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation11.start();
        colorAnimation11.doOnEnd {
            colorAnimation12.start()

        }
        colorAnimation12.doOnEnd {
            colorAnimation11.start()

        }
        binding.button2.visibility=View.GONE
        button4.visibility=View.GONE
        button5.visibility=View.GONE
        button6.visibility=View.GONE
        button7.visibility=View.GONE
        button8.visibility=View.GONE
        button3.visibility=View.GONE
        binding.imageView.setOnClickListener {
            val animatorX = ObjectAnimator.ofFloat(it, "translationX", 0f, 500f)

            animatorX.interpolator = LinearInterpolator()

            val animatorY = ObjectAnimator.ofFloat(it, "translationY", 0f, 0f)

            animatorY.interpolator = LinearInterpolator()

            val animatorRotation = ObjectAnimator.ofFloat(it, "rotation", 0f, 0f)


            val animatorSet = AnimatorSet()
            animatorSet.playTogether(animatorX, animatorY, animatorRotation)
            animatorSet.duration = 300
            animatorSet.start()
            animatorSet.doOnEnd {
                binding.textView4.visibility=View.GONE
                binding.imageView.visibility=View.GONE
                binding.imageView2.visibility=View.GONE
                button4.visibility=View.VISIBLE
                button5.visibility=View.VISIBLE
                button6.visibility=View.VISIBLE
                button7.visibility=View.VISIBLE
                button8.visibility=View.VISIBLE
                button3.visibility=View.VISIBLE
                binding.button2.visibility=View.VISIBLE
            }


        }

        binding.button2.setOnClickListener {
            var secilen : ArrayList<String> = ArrayList()
            if(button3.isChecked)
                secilen.add(button3.text.toString())
            if(button4.isChecked)
                secilen.add(button4.text.toString())
            if(button5.isChecked)
                secilen.add(button5.text.toString())
            if(button6.isChecked)
                secilen.add(button6.text.toString())
            if(button7.isChecked)
                secilen.add(button7.text.toString())
            if(button8.isChecked)
                secilen.add(button8.text.toString())

            val action =HomeFragmentDirections.actionHomeFragmentToCustomizedHomeFragment(secilen.toArray(arrayOfNulls<String>(secilen.size)))
            Navigation.findNavController(view).navigate(action)
        }
    }



}