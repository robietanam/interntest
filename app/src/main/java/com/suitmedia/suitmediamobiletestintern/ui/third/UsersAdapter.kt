package com.suitmedia.suitmediamobiletestintern.ui.third

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.suitmediamobiletestintern.data.retrofit.UserResponseDataItem
import com.suitmedia.suitmediamobiletestintern.databinding.ItemRvThirdScreenBinding


class UsersAdapter: PagingDataAdapter<UserResponseDataItem, UsersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var binding: ItemRvThirdScreenBinding
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserResponseDataItem>() {
            override fun areItemsTheSame(oldItem: UserResponseDataItem, newItem: UserResponseDataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserResponseDataItem, newItem: UserResponseDataItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: UserResponseDataItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(private val binding: ItemRvThirdScreenBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: UserResponseDataItem){
            binding.tvEmail.text = users.email
            binding.tvFullname.text = "${users.firstName} ${users.lastName}"

            Glide.with(binding.root)
                .load(users.avatar)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(users)
            }

            }

        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.MyViewHolder {
        binding = ItemRvThirdScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users = getItem(position)

        if (users != null) {
            holder.bind(users)
        }

    }



}