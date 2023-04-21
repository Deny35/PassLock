package pl.denys.karol.passlock.fragmentsHome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.MainActivity

import pl.denys.karol.passlock.databinding.FragmentSignOutBinding


@AndroidEntryPoint
class SignOutFragment : Fragment() {

    private lateinit var binding: FragmentSignOutBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignOutBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.signOutBtn.setOnClickListener {
//            viewModel.signOut {
//                startActivity(Intent(requireActivity(), MainActivity::class.java))
//                requireActivity().finish()
//            }
//        }
    }
}