package pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentPasswordDetailBinding
import pl.denys.karol.passlock.databinding.FragmentRoomPasswordDetailBinding
import pl.denys.karol.passlock.fragmentsHome.PasswordDetailFragmentArgs
import pl.denys.karol.passlock.fragmentsHome.PasswordDetailFragmentDirections
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.CryptViewModel
import pl.denys.karol.passlock.viewModel.FirebaseViewModel
import pl.denys.karol.passlock.viewModel.PasswordViewModel

@AndroidEntryPoint
class RoomPasswordDetailFragment : Fragment() {

    lateinit var binding: FragmentRoomPasswordDetailBinding
    val itemId: Int by lazy { requireArguments().getInt("id") }
    private val viewModel: PasswordViewModel by viewModels()
    private val viewModelCrypt: CryptViewModel by viewModels()
    private var pass:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized) {
            return binding.root
        } else {
            binding = FragmentRoomPasswordDetailBinding.inflate(layoutInflater)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUpdateAccount(itemId).observe(viewLifecycleOwner, this::setText)
        viewModel.getUpdateAccount(itemId).observe(viewLifecycleOwner, this::passDec)

        binding.buttonDelete.setOnClickListener{
        viewModel.getUpdateAccount(itemId).observe(viewLifecycleOwner, Observer { item ->
            viewModel.delAccount(item)
            view.findNavController().navigate(
                RoomPasswordDetailFragmentDirections.actionRoomPasswordDetailFragmentToPasswordListFragment()
            )
        } )

        }

        binding.imageView2.setOnClickListener {
            copyTextToClipboard("pass")
        }

        binding.imageView3.setOnClickListener {
            copyTextToClipboard("login")
        }

        binding.buttonSave.setOnClickListener{
            updateTo(view)
        }
    }



    private fun copyTextToClipboard(w: String) {
        val manager: ClipboardManager =
            activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager



        if (w == "pass") {
            val clipData = ClipData.newPlainText("text", pass)
            manager.setPrimaryClip(clipData)

            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
        }
        if (w == "login")
        {
//            val clipData = ClipData.newPlainText("text", args.account.login)
//            manager.setPrimaryClip(clipData)

            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
        }
    }

    fun setText(item: AccountROOM){
        binding.titleText.setText(item.title)
        binding.webText.setText(item.title)
        binding.emailText.setText(item.login)
        binding.description.setText(item.description)
    }

    fun passDec(item: AccountROOM){
        viewModelCrypt.decrypt(item.password)

        viewModelCrypt.decrypt.observe(viewLifecycleOwner){state ->
            if(state is UiState.Success){
                pass = state.data
                binding.password.setText(pass)


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
            binding.buttonSave.setText("SAVE")

        }
        }
    }

    fun updateTo(view: View){
        if (pass != binding.password.text.toString()){
            viewModel.getUpdateAccount(itemId).observe(viewLifecycleOwner, Observer { item ->


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
                viewModel.updateAccount(
                    AccountROOM(
                        id = item.id,
                        user_id = item.user_id,
                        title = binding.titleText.text.toString(),
                        login = binding.emailText.text.toString(),
                        password = pas,
                        website = binding.webText.text.toString(),
                        description = binding.description.text.toString(),
                        timeH =  binding.timePicker.hour,
                        timeM = binding.timePicker.minute,
                        type = "Account"
                    )
                )
            } )

        }else{
            viewModel.getUpdateAccount(itemId).observe(viewLifecycleOwner, Observer { item ->
                viewModel.updateAccount(
                    AccountROOM(
                        id = item.id,
                        user_id = item.user_id,
                        title = binding.titleText.text.toString(),
                        login = binding.emailText.text.toString(),
                        password = pass,
                        website = binding.webText.text.toString(),
                        description = binding.description.text.toString(),
                        timeH =  item.timeH,
                        timeM = item.timeM,
                        type = "Account"
                    )
                )
            } )

        }
        view.findNavController().navigate(
            RoomPasswordDetailFragmentDirections.actionRoomPasswordDetailFragmentToPasswordListFragment()
        )

    }
}