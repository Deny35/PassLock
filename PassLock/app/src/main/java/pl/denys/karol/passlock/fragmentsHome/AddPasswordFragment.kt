package pl.denys.karol.passlock.fragmentsHome

import android.content.Intent
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
import pl.denys.karol.passlock.MainActivity2
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentAddPasswordBinding
import pl.denys.karol.passlock.databinding.FragmentSignInBinding
import pl.denys.karol.passlock.fragmentsAuth.SignInFragmentDirections
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.FirebaseViewModel

@AndroidEntryPoint
class AddPasswordFragment : Fragment() {
    private lateinit var binding: FragmentAddPasswordBinding
    private val viewModelFirebase: FirebaseViewModel by viewModels()
    private val viewModelAuth: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addLoginDetails.setOnClickListener {
            add(view)
        }
    }

    fun add(view: View,){

        if(isOk())

            viewModelFirebase.addAccount(getAccount())


        viewModelFirebase.addAccount.observe(viewLifecycleOwner){ state ->

            if (state is UiState.Success) {
                view.findNavController().navigate(
                    AddPasswordFragmentDirections.actionAddPasswordFragmentToPasswordListFragment()
                )
                binding.progressBar3.visibility = View.INVISIBLE
            }
            if (state is UiState.Loading){
                binding.progressBar3.visibility = View.VISIBLE
                binding.addLoginDetails.setText("")
            }
            if (state is UiState.Failure){
                Toast.makeText(
                    context,
                    state.error,
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar3.visibility = View.INVISIBLE
                binding.addLoginDetails.setText("Add Login Details")

            }
        }
    }

    fun isOk():Boolean{
        if(binding.loginTitleAdd.text.isEmpty()){
            return false
        }
        if(binding.loginEmailAdd.text.isEmpty()){
            return false

        }
        if(binding.loginPasswordAdd.text.isEmpty()){
            return false

        }
        if(binding.loginWebsiteAdd.text.isEmpty()){
            return false

        }
        if(binding.loginNoteAdd.text.isEmpty()){
            return false
        }
        return true
    }
    fun getAccount(): Account{
        return Account(
            id ="",
            title = binding.loginTitleAdd.text.toString(),
            login = binding.loginEmailAdd.text.toString(),
            password = binding.loginPasswordAdd.text.toString(),
            website = binding.loginWebsiteAdd.text.toString(),
            description = binding.loginNoteAdd.text.toString(),
            type = "Account"
        ).apply{viewModelAuth.getSession { this.user_id = it?.id ?: "" } }
    }
}