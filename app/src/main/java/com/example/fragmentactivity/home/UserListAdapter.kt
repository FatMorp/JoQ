package com.example.fragmentactivity.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentactivity.R
import com.example.fragmentactivity.core.domain.User
import com.example.fragmentactivity.databinding.ItemUserRecyclerViewBinding

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(USER_DIFF_UTIL) {

    var onItemClicked: ((User) -> Unit)? = null

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

    override fun onBindViewHolder(holder: UserListAdapter.UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val USER_DIFF_UTIL = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}
