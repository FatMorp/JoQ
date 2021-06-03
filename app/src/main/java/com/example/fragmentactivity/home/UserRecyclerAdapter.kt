package com.example.fragmentactivity.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentactivity.R
import com.example.fragmentactivity.core.domain.User
import com.example.fragmentactivity.databinding.ItemUserRecyclerViewBinding

class UserRecyclerAdapter(private val users: ArrayList<User>) :
        RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    fun addData(list: List<User>){
        users.addAll(list)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserRecyclerViewBinding.bind(itemView)
        fun bind(item: User) {
            with(binding) {
                itemTxtUser.text = item.name
                itemTxtCity.text = item.city

                root.setOnClickListener{
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                    action.user = item

                    root.findNavController().navigate(action)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_recycler_view,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}