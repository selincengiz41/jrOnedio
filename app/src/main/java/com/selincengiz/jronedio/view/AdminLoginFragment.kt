package com.selincengiz.jronedio.view


import android.R
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.selincengiz.jronedio.databinding.FragmentAdminLoginBinding


class AdminLoginFragment : Fragment() {
    private var _binding: FragmentAdminLoginBinding? = null
    private val binding get() = _binding!!

    private val username:String ="hobbit"
    private val password:String ="123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminLoginBinding.inflate(inflater, container, false)

        val view = binding.root

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ////// Buton Animasyonu
        val button = binding.buttonLogin
        val colorFrom = resources.getColor(R.color.black)
        val colorTo = resources.getColor(R.color.darker_gray)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 2000 // milliseconds
        colorAnimation.addUpdateListener { animator -> button.setBackgroundColor(animator.animatedValue as Int) }

        val colorAnimation2 = ValueAnimator.ofObject(ArgbEvaluator(),colorTo , colorFrom)
        colorAnimation2.duration = 2000 // milliseconds
        colorAnimation2.addUpdateListener { animator -> button.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation.start();
        colorAnimation.doOnEnd {
            colorAnimation2.start()

        }
        colorAnimation2.doOnEnd {
            colorAnimation.start()

        }

        ///////Giriş Yapma
        binding.buttonLogin.setOnClickListener {

            if(username== binding.editTextUsername.text.toString() && password== binding.editTextPassword.text.toString()){
                val action = AdminLoginFragmentDirections.actionAdminLoginFragmentToAdminInsertFragment()
                Navigation.findNavController(it).navigate(action)
            }
            else{
                Toast.makeText(view.context,"Giriş bilgileri hatalı!",Toast.LENGTH_SHORT).show()

            }


        }


    }

}