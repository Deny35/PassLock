package pl.denys.karol.passlock.CardFragments

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
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentAddCardBinding
import pl.denys.karol.passlock.databinding.FragmentAddPasswordBinding
import pl.denys.karol.passlock.fragmentsHome.AddPasswordFragmentDirections
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.CardViewModel
import pl.denys.karol.passlock.viewModel.CryptViewModel
import pl.denys.karol.passlock.viewModel.FirebaseViewModel

@AndroidEntryPoint
class AddCardFragment : Fragment() {
    private lateinit var binding: FragmentAddCardBinding
    private val viewModel: CardViewModel by viewModels()
    private val viewModelAuth: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSve.setOnClickListener {
            add(view)

        }
    }

    fun add(view: View,){
        if(isOk()){
            viewModel.addCard(getAccount())
            findNavController().navigate(AddCardFragmentDirections.actionAddCardFragmentToCardFragment())
        }

    }

    fun isOk():Boolean{
        if(binding.titleText.text.isEmpty()){
            return false
        }
        if(binding.webText.text.toString().length < 16){
            return false
        }
        if(binding.emailText.text.toString().length < 2){
            return false
        }
        if(binding.password.text.toString().length < 2){
            return false
        }
        if(binding.cvv.text.toString().length < 3){
            return false
        }

        return true
    }
    fun getAccount(): CardModel {
        return CardModel(
            id = 0,
            user_id = "",
            title = binding.titleText.text.toString(),
            number= binding.webText.text.toString(),
            month = binding.emailText.text.toString() ,
            ye = binding.password.text.toString(),
            cvv = binding.cvv.text.toString(),
            description = binding.description.text.toString()
        ).apply { viewModelAuth.getSession { this.user_id = it?.id ?: "" } }
    }

//    val id: Int,
//    val title: String,
//    val number : String,
//    val month: String,
//    val ye: String,
//    val description: String
}