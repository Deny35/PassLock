package pl.denys.karol.passlock.CardFragments

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
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import pl.denys.karol.passlock.databinding.FragmentCardDetailBinding
import pl.denys.karol.passlock.databinding.FragmentRoomPasswordDetailBinding
import pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM.RoomPasswordDetailFragmentDirections
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.CardViewModel
import pl.denys.karol.passlock.viewModel.CryptViewModel
import pl.denys.karol.passlock.viewModel.PasswordViewModel


class CardDetailFragment : Fragment() {
    lateinit var binding: FragmentCardDetailBinding
    val itemId: Int by lazy { requireArguments().getInt("id") }
    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized) {
            return binding.root
        } else {
            binding = FragmentCardDetailBinding.inflate(layoutInflater)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUpdateCard(itemId).observe(viewLifecycleOwner, this::setText)


        binding.buttonDelete.setOnClickListener{
            viewModel.getUpdateCard(itemId).observe(viewLifecycleOwner, Observer { item ->
                println(item)
                viewModel.delCard(item)
                view.findNavController().navigate(
                   CardDetailFragmentDirections.actionCardDetailFragmentToCardFragment()
                )
            } )

        }


        binding.imageView3.setOnClickListener {
            copyTextToClipboard()
        }

        binding.buttonSave.setOnClickListener{
            updateTo(view)
        }
    }



    private fun copyTextToClipboard() {
        val manager: ClipboardManager =
            activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val clipData = ClipData.newPlainText("text", binding.webText.text.toString(),)
            manager.setPrimaryClip(clipData)

            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()

    }

    fun setText(item: CardModel){
        binding.titleText.setText(item.title)
        binding.webText.setText(item.number)
        binding.emailText.setText(item.month)
        binding.password.setText(item.ye)
        binding.cvv.setText(item.cvv)
        binding.description.setText(item.description)
    }



    fun updateTo(view: View){

            viewModel.getUpdateCard(itemId).observe(viewLifecycleOwner, Observer { item ->
                viewModel.updateCard(
                    CardModel(
                        id = item.id,
                        user_id = item.user_id,
                        title = binding.titleText.text.toString(),
                        number= binding.webText.text.toString(),
                        month = binding.emailText.text.toString() ,
                        ye = binding.password.text.toString(),
                        cvv = binding.cvv.text.toString(),
                        description = binding.description.text.toString())
                    )
            } )

        view.findNavController().navigate(
            RoomPasswordDetailFragmentDirections.actionRoomPasswordDetailFragmentToPasswordListFragment()
        )

    }
}