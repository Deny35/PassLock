package pl.denys.karol.passlock.fragmentsHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM.RoomPasswordFragment
import pl.denys.karol.passlock.databinding.FragmentPasswordListBinding
import pl.denys.karol.passlock.util.tabLayoutSetup

@AndroidEntryPoint
class PasswordListFragment : Fragment() {
    private lateinit var binding: FragmentPasswordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = Adapterr(this@PasswordListFragment)
        tabLayoutSetup(requireContext(),  binding.tabLayout, binding.viewPager)
//        binding.addAccountFButton.setOnClickListener{
//            view.findNavController().navigate(
//                PasswordListFragmentDirections.actionPasswordListFragmentToAddPasswordFragment()
//            )
//        }

    }



    class Adapterr(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity) {

        private val fragments = listOf(FirebasePasswordFragment(), RoomPasswordFragment())
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}