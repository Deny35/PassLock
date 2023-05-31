package pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM

import androidx.recyclerview.widget.DiffUtil
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel

class RoomPasswordComperator : DiffUtil.ItemCallback<AccountROOM>() {
    override fun areItemsTheSame(oldItem: AccountROOM, newItem: AccountROOM): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: AccountROOM, newItem: AccountROOM): Boolean {
        return oldItem.id == newItem.id
    }
}