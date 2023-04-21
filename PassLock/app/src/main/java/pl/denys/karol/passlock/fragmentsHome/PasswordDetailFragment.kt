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
import androidx.navigation.fragment.navArgs
import pl.denys.karol.passlock.databinding.FragmentPasswordDetailBinding
import pl.denys.karol.passlock.viewModel.FirebaseViewModel


class PasswordDetailFragment : Fragment() {
    lateinit var binding: FragmentPasswordDetailBinding
    private val args by navArgs<PasswordDetailFragmentArgs>()
    private val viewModelFirebase: FirebaseViewModel by viewModels()

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
        setText()

        binding.buttonDelete.setOnClickListener {
            viewModelFirebase.deleteAccount(args.account)
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
            val clipData = ClipData.newPlainText("text", args.account.password)
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


    fun setText(){
        binding.titleText.setText(args.account.title)
        binding.webText.setText(args.account.title)
        binding.emailText.setText(args.account.login)
        binding.password.setText(args.account.password)
        binding.description.setText(args.account.description)
    }
}