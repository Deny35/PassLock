 package pl.denys.karol.passlock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentForgotPasswordInfoFragmenatBinding
import pl.denys.karol.passlock.databinding.FragmentSignUpBinding


 class ForgotPasswordInfoFragmenat : Fragment() {

    private lateinit var binding: FragmentForgotPasswordInfoFragmenatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordInfoFragmenatBinding.inflate(inflater, container, false)
        return binding.root
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         binding.imageView.setImageResource(R.drawable.lock)
         binding.singInBtn.setOnClickListener {
             view.findNavController().navigate(
                 ForgotPasswordInfoFragmenatDirections.actionForgotPasswordInfoFragmenatToSignInFragment())
         }
     }}