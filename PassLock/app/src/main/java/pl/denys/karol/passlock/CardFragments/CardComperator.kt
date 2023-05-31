package pl.denys.karol.passlock.CardFragments

import androidx.recyclerview.widget.DiffUtil
import pl.denys.karol.passlock.model.CardModel

class CardComperator: DiffUtil.ItemCallback<CardModel>() {
    override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return oldItem.id == newItem.id
    }
}