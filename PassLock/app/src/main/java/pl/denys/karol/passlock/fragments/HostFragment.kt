package pl.denys.karol.passlock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentHostBinding
import pl.denys.karol.passlock.databinding.FragmentSingOutBinding

class HostFragment : Fragment() {

    private lateinit var binding: FragmentHostBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHostBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addFloatingButton.setOnClickListener {}
        var dialog = AddFragment()
        dialog.show((activity as AppCompatActivity).supportFragmentManager, "show")
    }
}


