package pl.denys.karol.passlock.fragmentsHome.FragmentsPasswordROOM

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pl.denys.karol.passlock.CardFragments.CardComperator
import pl.denys.karol.passlock.CardFragments.CardViewHolder
import pl.denys.karol.passlock.databinding.CardListItemBinding
import pl.denys.karol.passlock.databinding.PasswordListItemBinding
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.viewModel.CardViewModel
import pl.denys.karol.passlock.viewModel.PasswordViewModel

class RoomPasswordAdapter  (Comperator: RoomPasswordComperator, private val viewModel: PasswordViewModel) : ListAdapter<AccountROOM, RoomPasswordViewHolder>(Comperator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomPasswordViewHolder {
        return RoomPasswordViewHolder(
            PasswordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), viewModel
        )
    }

    override fun onBindViewHolder(holder: RoomPasswordViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    public fun getItemAt(position: Int): AccountROOM {
        return getItem(position)
    }

}