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
import pl.denys.karol.passlock.databinding.FragmentForgotPasswordBinding
import pl.denys.karol.passlock.util.UiState

@AndroidEntryPoint
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
        binding.ForgotPasswordEmail.editText?.text.toString()
        binding.ForgotPasswordButton.setOnClickListener {
            press(view)
        }
    }

    private fun press(view: View) {
        val email =   binding.ForgotPasswordEmail.editText?.text.toString()

        if ( email.contains('@') && email.contains('.')) {
            viewModel.forgotPassword(email)

            viewModel.forgotPassword.observe(viewLifecycleOwner) { state ->
                if (state is UiState.Success) {
                    view.findNavController().navigate(
                        ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToSignInFragment())
                }
                if (state is UiState.Loading){
                    binding.progress.visibility = View.VISIBLE
                    binding.ForgotPasswordButton.setText("")
                }
                if (state is UiState.Failure){
                    Toast.makeText(
                        context,
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progress.visibility = View.INVISIBLE
                    binding.ForgotPasswordButton.setText("Sign In")

                }
            }

        } else{
            if(!email.contains('@') && email.contains('.')){
                binding.ForgotPasswordEmail.editText?.error = "This is not email!"
            }
            if (email.isEmpty()){
                binding.ForgotPasswordEmail.editText?.error = "The field must not be empty!"
            }

        }
    }
}