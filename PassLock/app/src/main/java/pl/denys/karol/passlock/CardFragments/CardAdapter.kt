package pl.denys.karol.passlock.CardFragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pl.denys.karol.passlock.databinding.CardListItemBinding
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.viewModel.CardViewModel

class CardAdapter (Comperator: CardComperator, private val viewModel: CardViewModel) : ListAdapter<CardModel, CardViewHolder>(Comperator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            CardListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), viewModel
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    public fun getItemAt(position: Int): CardModel{
        return getItem(position)
    }
}