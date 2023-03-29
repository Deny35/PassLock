package pl.denys.karol.passlock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setImageResource(R.drawable.lock)
        binding.signUpBtn.setOnClickListener {
            println("click")
            press(view)
        }
    }

    private fun press(view: View) {
        val email = binding.editEmailSignUp.text.toString()
        val password: String = binding.editPassSignUp.text.toString()
        val passwordRepeat = binding.editPassSignUpRepeat.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty() && password==passwordRepeat && password.length >= 7){
            println("11")
            viewModel.register(email, password)
            println("aa")
            view.findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
            println("bb")

        } else{
            if (email.isEmpty()){
                binding.editEmailSignUp.error = "The field must not be empty!"
            }
            if ( password.isEmpty()){
                binding.editPassSignUp.error = "The field must not be empty!"
            }
            if ( passwordRepeat.isEmpty()) {
                binding.editPassSignUpRepeat.error = "The field must not be empty!"
            }
            if (password!=passwordRepeat){
                Toast.makeText(
                    context,
                    "Passwords must be the same!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.editPassSignUpRepeat.error = "Passwords must be the same!"
            }
            if(password.length <= 6){
                Toast.makeText(
                    context,
                    "The password must have min 6 char!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.editPassSignUp.error = "The password must have min 6 char!"
            }


        }
    }
}