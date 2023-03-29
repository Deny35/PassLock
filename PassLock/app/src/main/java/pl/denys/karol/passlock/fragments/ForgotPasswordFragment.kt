package pl.denys.karol.passlock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setImageResource(R.drawable.lock)
        binding.sendBtn.setOnClickListener {
            println("click")
            press(view)
        }
    }

    private fun press(view: View) {
        val email = binding.editEmailForgot.text.toString()

        if ( email.contains('@')) {
            println(email.contains('@'))
            viewModel.forgotPassword(email)
            view.findNavController().navigate(
                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToForgotPasswordInfoFragmenat())
        } else{
            if(!email.contains('@')){
                println("A")
                binding.editEmailForgot.error = "This is not email!"
            }
            if (email.isEmpty()){
                binding.editEmailForgot.error = "The field must not be empty!"
            }

        }
    }
}