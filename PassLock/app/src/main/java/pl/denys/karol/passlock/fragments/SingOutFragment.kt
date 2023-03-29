package pl.denys.karol.passlock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import pl.denys.karol.passlock.databinding.FragmentSingOutBinding

class SingOutFragment : Fragment() {

    private lateinit var binding: FragmentSingOutBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingOutBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signOutBtn.setOnClickListener {
            viewModel.signOut()
            view.findNavController().navigate(
            SingOutFragmentDirections.actionSingOutFragmentToSignInFragment())
        }
    }
}