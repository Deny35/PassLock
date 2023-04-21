package pl.denys.karol.passlock.fragmentsAuth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentSignUpBinding
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.util.UiState

@AndroidEntryPoint
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
        binding.buttonSignup.setOnClickListener {
            press(view)
        }
    }

    private fun press(view: View) {
        val email =  binding.textInputLayoutEmail.editText?.text.toString()
        val password: String =binding.textInputLayoutPassword.editText?.text.toString()
        val passwordRepeat = binding.textInputLayoutPassword2.editText?.text.toString()

        binding.textInputLayoutPassword.editText?.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty() && password==passwordRepeat && password.length >= 7) {
            viewModel.register(email, password, User("", email))

            viewModel.register.observe(viewLifecycleOwner) { state ->
                println(state)
                if (state is UiState.Success) {
                    view.findNavController()
                        .navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                }
            }

        } else{
            if (email.isEmpty()){
                binding.textInputLayoutEmail.editText?.error = "The field must not be empty!"
            }
            if ( password.isEmpty()){
                binding.textInputLayoutPassword.editText?.error = "The field must not be empty!"
            }
            if ( passwordRepeat.isEmpty()) {
                binding.textInputLayoutPassword2.editText?.error = "The field must not be empty!"
            }
            if (password!=passwordRepeat){
                Toast.makeText(
                    context,
                    "Passwords must be the same!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.textInputLayoutPassword2.editText?.error = "Passwords must be the same!"
            }
            if(password.length <= 6){
                Toast.makeText(
                    context,
                    "The password must have min 6 char!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.textInputLayoutPassword.editText?.error = "The password must have min 6 char!"
            }


        }
    }
}