package com.study.mymovies.ui.fragment.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.mymovies.R
import com.study.mymovies.databinding.ItemUserBinding
import com.study.mymovies.ui.fragment.registry.data.model.UserModel

class UserAdapter(private val list: List<UserModel>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val bind = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(bind)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class UserViewHolder(private val bind: ItemUserBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(item: UserModel) {
            bind.apply {
                root.context.apply {
                    name.text = item.fullName
                    phone.text = item.phone
                    email.text = item.email
                    address.text = item.address
                }
            }
        }
    }
}