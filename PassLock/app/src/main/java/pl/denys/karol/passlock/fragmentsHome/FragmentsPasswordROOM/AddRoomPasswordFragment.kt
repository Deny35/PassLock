package pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.CardFragments.AddCardFragmentDirections
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentAddCardBinding
import pl.denys.karol.passlock.databinding.FragmentAddRoomPasswordBinding
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.CardViewModel
import pl.denys.karol.passlock.viewModel.CryptViewModel
import pl.denys.karol.passlock.viewModel.PasswordViewModel

@AndroidEntryPoint
class AddRoomPasswordFragment : Fragment() {
    private lateinit var binding: FragmentAddRoomPasswordBinding
    private val viewModel: PasswordViewModel by viewModels()
    private val viewModelAuth: AuthViewModel by viewModels()
    private val viewModelCrypt: CryptViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoomPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addLoginDetails.setOnClickListener {
            add(view)

        }
    }

    fun add(view: View,){
        if(isOk()){
            viewModel.addRoomPass(getAccount())
            findNavController().navigate(AddRoomPasswordFragmentDirections.actionAddRoomPasswordFragmentToPasswordListFragment())
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

    fun getAccount(): AccountROOM {

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

        return AccountROOM(
            id = 0,
            user_id = "",
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

//    var id: Int,
//    var user_id: String = "",
//    val title: String = "",
//    val login: String = "",
//    val password: String = "",
//    val website: String = "",
//    val description: String = "",
//    val timeH: Int = 0,
//    val timeM: Int = 0,
//    val type: String = ""
}