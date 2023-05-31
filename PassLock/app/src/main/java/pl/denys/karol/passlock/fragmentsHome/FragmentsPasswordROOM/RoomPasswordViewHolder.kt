package pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.denys.karol.passlock.Constants
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.PasswordListItemBinding
import pl.denys.karol.passlock.fragmentsHome.PasswordListFragmentDirections
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.viewModel.CardViewModel
import pl.denys.karol.passlock.viewModel.PasswordViewModel
import java.util.*

class RoomPasswordViewHolder(private val binding: PasswordListItemBinding, private val viewModel: PasswordViewModel) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AccountROOM) {
        val now = Calendar.getInstance()
        
        binding.itemTitle.text = item.title
        binding.itemId.text = item.login

        if (now.get(Calendar.HOUR_OF_DAY).toInt() >= item.timeH && now.get(Calendar.MINUTE).toInt() >= item.timeM){
            binding.itemIcon.setImageResource(R.drawable.ic_baseline_password_invalid)
            pl.denys.karol.passlock.Notification.notificationBuilder(
                itemView.context,
                Constants.TITLE,
                Constants.DESCRIPTION
            )
        }else{
            binding.itemIcon.setImageResource(R.drawable.ic_baseline_password_24)

        }
        binding.itemLayout.setOnClickListener {
            binding.itemLayout.findNavController().navigate(
                PasswordListFragmentDirections.actionPasswordListFragmentToRoomPasswordDetailFragment(item.id)
            )

        }
    }


}