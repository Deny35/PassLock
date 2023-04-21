package pl.denys.karol.passlock.fragmentsHome.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.denys.karol.passlock.databinding.PasswordListItemBinding
import pl.denys.karol.passlock.fragmentsHome.PasswordListFragmentDirections
import pl.denys.karol.passlock.model.Account

class FirebasePasswordAdapter( //val onItemClicked: (Int, Account) -> Unit

) : RecyclerView.Adapter<FirebasePasswordAdapter.MyViewHolder>() {

    private var list: MutableList<Account> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = PasswordListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        println(item.id)
        holder.bind(item)
    }

    inner class MyViewHolder(val binding: PasswordListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Account){
                binding.itemTitle.text = item.title
                binding.itemId.text = item.id

                binding.itemLayout.setOnClickListener {
                    binding.itemLayout.findNavController().navigate(
                        PasswordListFragmentDirections.actionPasswordListFragmentToPasswordDetailFragment(item)
                    )

                }
                }
            }


    fun updateList(list: MutableList<Account>){
        this.list = list
        println(list.size)

    }


    override fun getItemCount(): Int {
        return list.size
    }

}