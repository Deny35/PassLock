package pl.denys.karol.passlock.CardFragments

import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.denys.karol.passlock.databinding.CardListItemBinding
import pl.denys.karol.passlock.fragmentsHome.PasswordListFragmentDirections
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.viewModel.CardViewModel

class CardViewHolder(private val binding: CardListItemBinding, private val viewModel: CardViewModel) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: CardModel) {
        binding.itemTitle.text = item.title
        binding.itemId.text = (removeLastNchars(item.number, 5))

        binding.cardItemLayout.setOnClickListener {
            binding.cardItemLayout.findNavController().navigate(
               CardFragmentDirections.actionCardFragmentToCardDetailFragment(item.id)
            )

        }
    }



    fun removeLastNchars(str: String, n: Int): String {
        return str.substring(0, str.length - n)
    }
}