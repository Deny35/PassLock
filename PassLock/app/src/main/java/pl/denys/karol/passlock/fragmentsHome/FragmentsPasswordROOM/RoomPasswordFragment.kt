package pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.CardFragments.CardAdapter
import pl.denys.karol.passlock.CardFragments.CardComperator
import pl.denys.karol.passlock.CardFragments.CardFragmentDirections
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.FragmentRoomPasswordBinding
import pl.denys.karol.passlock.fragmentsHome.PasswordListFragmentDirections
import pl.denys.karol.passlock.fragmentsHome.adapters.FirebasePasswordAdapter
import pl.denys.karol.passlock.viewModel.CardViewModel
import pl.denys.karol.passlock.viewModel.FirebaseViewModel
import pl.denys.karol.passlock.viewModel.PasswordViewModel

@AndroidEntryPoint
class RoomPasswordFragment : Fragment() {
    private lateinit var binding: FragmentRoomPasswordBinding

    private val viewModel: PasswordViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RoomPasswordAdapter(RoomPasswordComperator(), viewModel)
        binding.listRecyclerView.adapter = adapter
        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllRoomPass().observe(viewLifecycleOwner, adapter::submitList)
        binding.addAccountFButton.setOnClickListener {
            findNavController().navigate( PasswordListFragmentDirections.actionPasswordListFragmentToAddRoomPasswordFragment())
        }
    }

}
