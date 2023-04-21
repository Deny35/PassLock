package pl.denys.karol.passlock.fragmentsAuth

import android.content.Intent
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
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.MainActivity2
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentSignInBinding
import pl.denys.karol.passlock.util.UiState

@AndroidEntryPoint
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
        binding.signInButton.setOnClickListener {
            press()
        }
        binding.txtSignUp.setOnClickListener {
            view.findNavController().navigate(
                SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            )
        }
        binding.forgotPass.setOnClickListener {
            view.findNavController().navigate(
                SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment()
            )
        }
    }
        private fun press() {
            val email = binding.textInputLayoutEmailSignIn.editText?.text.toString()
            val password = binding.textInputLayoutSignInPassword.editText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signIn(email,password)


                viewModel.signIn.observe(viewLifecycleOwner) { state ->

                    if (state is UiState.Success) {
                        startActivity(Intent(requireActivity(), MainActivity2::class.java))
                        requireActivity().finish()
                    }
                    if (state is UiState.Loading){
                        binding.progress.visibility = View.VISIBLE
                        binding.signInButton.setText("")
                    }
                    if (state is UiState.Failure){
                        Toast.makeText(
                            context,
                            state.error,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progress.visibility = View.INVISIBLE
                        binding.signInButton.setText("Sign In")

                    }
                }
            } else {
                if (email.isEmpty()) {
                    binding.textInputLayoutEmailSignIn.editText?.error = "The field must not be empty!"
                }
                if (password.isEmpty()) {
                    binding.textInputLayoutSignInPassword.editText?.error = "The field must not be empty!"
                }

            }
        }

    override fun onStart() {
        super.onStart()
        viewModel.getSession { user ->
            if (user != null){
                startActivity(Intent(requireActivity(), MainActivity2::class.java))
                requireActivity().finish()
            }
        }
    }

}