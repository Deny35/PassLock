package pl.denys.karol.passlock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setImageResource(R.drawable.lock)
        binding.signInBtn.setOnClickListener {
            press(view)
        }
        binding.signUpBtn.setOnClickListener {
            view.findNavController().navigate(
                SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            )
        }
        binding.forgetPassBtn.setOnClickListener {
            view.findNavController().navigate(
                SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment()
            )
        }
    }
        private fun press(view: View) {
            val email = binding.editEmailSignIn.text.toString()
            val password = binding.editPassSignIn.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signIn(email,password)
                if(viewModel.getCurrentUser() != null) {
                    view.findNavController().navigate(
                        SignInFragmentDirections.actionSignInFragmentToSingOutFragment()
                    )
                }
            } else {
                if (email.isEmpty()) {
                    binding.editEmailSignIn.error = "The field must not be empty!"
                }
                if (password.isEmpty()) {
                    binding.editPassSignIn.error = "The field must not be empty!"
                }

            }
        }

}