package pl.denys.karol.passlock.fragmentsHome

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
import pl.denys.karol.passlock.databinding.FragmentAddPasswordBinding
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.CryptViewModel
import pl.denys.karol.passlock.viewModel.FirebaseViewModel

@AndroidEntryPoint
class AddPasswordFragment : Fragment(), PasswordGeneratorDialog.OnCopyListener {
    private lateinit var binding: FragmentAddPasswordBinding
    private val viewModelFirebase: FirebaseViewModel by viewModels()
    private val viewModelAuth: AuthViewModel by viewModels()
    private val viewModelCrypt: CryptViewModel by viewModels()


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

        binding.genPass.setOnClickListener {
            val dialog = PasswordGeneratorDialog()
            dialog.show(childFragmentManager, "s")
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
                binding.progressBar.visibility = View.INVISIBLE
            }
            if (state is UiState.Loading){
                binding.progressBar.visibility = View.VISIBLE
                binding.addLoginDetails.setText("")
            }
            if (state is UiState.Failure){
                Toast.makeText(
                    context,
                    state.error,
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.visibility = View.INVISIBLE
                binding.addLoginDetails.setText("Add Login Details")

            }
        }
    }

    fun isOk():Boolean{
        if(binding.titleText.text.isEmpty()){
            return false
        }
        if(binding.emailText.text.isEmpty()){
            return false

        }
        if(binding.password.text.isEmpty()){
            return false

        }
        if(binding.webText.text.isEmpty()){
            return false

        }
        if(binding.description.text.isEmpty()){
            return false
        }
        return true
    }
    fun getAccount(): Account{
        var pass:String =""
        viewModelCrypt.encrypt(binding.password.text.toString())

        viewModelCrypt.encrypt.observe(viewLifecycleOwner){ state ->
            if (state is UiState.Success) {
                pass = state.data
            }
            if (state is UiState.Failure){
                Toast.makeText(
                    context,
                    state.error,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
            return Account(
                id = "",
                title = binding.titleText.text.toString(),
                login = binding.emailText.text.toString(),
                password = pass,
                website = binding.webText.text.toString(),
                description = binding.description.text.toString(),
                timeH =  binding.timePicker.hour,
                timeM = binding.timePicker.minute,
                type = "Account"
            ).apply { viewModelAuth.getSession { this.user_id = it?.id ?: "" } }
        }

    override fun sendPassword(password: String) {
        binding.password.setText(password)
    }

}