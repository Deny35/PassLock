package pl.denys.karol.passlock.fragmentsHome.adapters

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codingstuff.loginsignupmvvm.viewmodel.AuthViewModel
import pl.denys.karol.passlock.Constants
import pl.denys.karol.passlock.Constants.Companion.DESCRIPTION
import pl.denys.karol.passlock.Constants.Companion.TITLE
import pl.denys.karol.passlock.Notification.notificationBuilder
import pl.denys.karol.passlock.R
import pl.denys.karol.passlock.databinding.PasswordListItemBinding
import pl.denys.karol.passlock.fragmentsHome.PasswordListFragmentDirections
import pl.denys.karol.passlock.model.Account
import java.util.*

class FirebasePasswordAdapter() : RecyclerView.Adapter<FirebasePasswordAdapter.MyViewHolder>() {
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
                val now = Calendar.getInstance()

                binding.itemTitle.text = item.title
                binding.itemId.text = item.login
                if (now.get(Calendar.HOUR_OF_DAY).toInt() >= item.timeH && now.get(Calendar.MINUTE).toInt() >= item.timeM){
                    binding.itemIcon.setImageResource(R.drawable.ic_baseline_password_invalid)
                    pl.denys.karol.passlock.Notification.notificationBuilder(
                        itemView.context,
                        TITLE,
                        DESCRIPTION )
                }else{
                    binding.itemIcon.setImageResource(R.drawable.ic_baseline_password_24)

                }

                binding.itemLayout.setOnClickListener {
                    println(item.id)
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