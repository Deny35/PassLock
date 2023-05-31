package pl.denys.karol.passlock.fragmentsHome

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.Constants.Companion.DESCRIPTION
import pl.denys.karol.passlock.Constants.Companion.TITLE
import pl.denys.karol.passlock.Notification
import pl.denys.karol.passlock.databinding.FragmentPasswordDetailBinding
import pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM.RoomPasswordDetailFragmentDirections
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.CryptViewModel
import pl.denys.karol.passlock.viewModel.FirebaseViewModel

@AndroidEntryPoint
class PasswordDetailFragment : Fragment() {
    lateinit var binding: FragmentPasswordDetailBinding
    private val args by navArgs<PasswordDetailFragmentArgs>()
    private val viewModelFirebase: FirebaseViewModel by viewModels()
    private val viewModelCrypt: CryptViewModel by viewModels()
    private var pass:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized) {
            return binding.root
        } else {
            binding = FragmentPasswordDetailBinding.inflate(layoutInflater)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("det")
        println(args.account.id)

        viewModelCrypt.decrypt(args.account.password)

        viewModelCrypt.decrypt.observe(viewLifecycleOwner){state ->
            if(state is UiState.Success){
                pass = state.data
                setText(pass)

            }
            if (state is UiState.Loading){
                binding.progressBar.visibility = View.VISIBLE
                binding.buttonSave.setText("    ")
            }
            if (state is UiState.Failure){
                println("cos tam to")
                Toast.makeText(
                    context,
                    state.error,
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.visibility = View.INVISIBLE
                binding.buttonSave.setText("SAVE")

            }
        }

        binding.buttonDelete.setOnClickListener {
            viewModelFirebase.deleteAccount(args.account)

            viewModelFirebase.deleteAccount.observe(viewLifecycleOwner){state ->
                if (state is UiState.Success){
                    view.findNavController().navigate(
                        PasswordDetailFragmentDirections.actionPasswordDetailFragmentToPasswordListFragment()
                    )
                }
                if (state is UiState.Loading){
                    binding.progressBar2.visibility = View.VISIBLE
                    binding.buttonDelete.setText("    ")
                }
                if (state is UiState.Failure){
                    Toast.makeText(
                        context,
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBar2.visibility = View.INVISIBLE
                    binding.buttonDelete.setText("DELETE")

                }
            }
        }


        binding.buttonSave.setOnClickListener{
            viewModelFirebase.updateAccount(updateTo())

            viewModelFirebase.updateAccount.observe(viewLifecycleOwner){state ->
                if (state is UiState.Success){
                    view.findNavController().navigate(
                        PasswordDetailFragmentDirections.actionPasswordDetailFragmentToPasswordListFragment()
                    )
                }
                if (state is UiState.Loading){
                    binding.progressBar.visibility = View.VISIBLE
                    binding.buttonSave.setText("    ")
                }
                if (state is UiState.Failure){
                    Toast.makeText(
                        context,
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.buttonSave.setText("UPDATE")

                }
            }
        }
        binding.imageView2.setOnClickListener {
            copyTextToClipboard("pass")

        }

        binding.imageView3.setOnClickListener {
            copyTextToClipboard("login")
        }
    }

    private fun copyTextToClipboard(w: String) {
        val manager: ClipboardManager =
            activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager



        if (w == "pass") {
            val clipData = ClipData.newPlainText("text", pass)
            manager.setPrimaryClip(clipData)

            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
        }
        if (w == "login")
        {
            val clipData = ClipData.newPlainText("text", args.account.login)
            manager.setPrimaryClip(clipData)

            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
        }
    }


    fun setText(pass: String){
        binding.titleText.setText(args.account.title)
        binding.webText.setText(args.account.title)
        binding.emailText.setText(args.account.login)
        binding.password.setText(pass)
        binding.description.setText(args.account.description)
    }

    fun updateTo(): Account{
        if (pass != binding.password.text.toString()){

                var pas:String =""
                viewModelCrypt.encrypt(binding.password.text.toString())

                viewModelCrypt.encrypt.observe(viewLifecycleOwner){ state ->
                    if (state is UiState.Success) {
                        pas = state.data
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
                        id = args.account.id,
                        user_id = args.account.user_id,
                        title = binding.titleText.text.toString(),
                        login = binding.emailText.text.toString(),
                        password = pas,
                        website = binding.webText.text.toString(),
                        description = binding.description.text.toString(),
                        timeH =  binding.timePicker.hour,
                        timeM = binding.timePicker.minute,
                        type = "Account"
                    )
        }else{
                return Account(
                        id = args.account.id,
                        user_id = args.account.user_id,
                        title = binding.titleText.text.toString(),
                        login = binding.emailText.text.toString(),
                        password = args.account.password,
                        website = binding.webText.text.toString(),
                        description = binding.description.text.toString(),
                        timeH =  args.account.timeH,
                        timeM = args.account.timeM,
                        type = "Account"
                    )
        }
    }
}