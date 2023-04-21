package pl.denys.karol.passlock.fragmentsHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentFirebasePasswordBinding
import pl.denys.karol.passlock.fragmentsHome.adapters.FirebasePasswordAdapter
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.util.UiState
import pl.denys.karol.passlock.viewModel.FirebaseViewModel



@AndroidEntryPoint
class FirebasePasswordFragment : Fragment() {

    private val viewModelFirebase: FirebaseViewModel by viewModels()
    private val viewModelAuth: AuthViewModel by viewModels()
    lateinit var binding: FragmentFirebasePasswordBinding
    val adapter by lazy {
        FirebasePasswordAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized) {
            return binding.root
        } else {
            binding = FragmentFirebasePasswordBinding.inflate(layoutInflater)
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelAuth.getSession {
            viewModelFirebase.getNotes(it?.id ?: "")
        }
        oberver()
    }

    private fun oberver() {
        viewModelFirebase.account.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UiState.Failure -> {
                    Toast.makeText(
                        context,
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBar.visibility = View.INVISIBLE

                }
                is UiState.Success -> {
                    println("w")
                    binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.listRecyclerView.adapter = adapter
                    adapter.updateList(state.data.toMutableList())
                    binding.progressBar.visibility = View.INVISIBLE

                }
            }
        }
    }


}
