package com.PCSUASDIDI.PCSUASDIDI.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.PCSUASDIDI.PCSUASDIDI.R
import com.PCSUASDIDI.PCSUASDIDI.data.model.User
import com.PCSUASDIDI.PCSUASDIDI.ui.detail.DetailActivity

class UserAdapter : RecyclerView.Adapter<UserAdapter.GithubUserViewHolder>() {
    private val listGithubUser = ArrayList<User>()

    fun setData(items: ArrayList<User>) {
        listGithubUser.clear()
        listGithubUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_main,
            parent,
            false
        )
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        val githubUser = listGithubUser[position]

        Glide.with(holder.itemView.context)
            .load(githubUser.avatarUrl)
            .into(holder.imgPhoto)

        holder.tvName.text = githubUser.login
        holder.tvDesc.text = githubUser.htmlUrl

        val mContext = holder.itemView.context
        holder.itemView.setOnClickListener {
            val moveDetailActivity = Intent(mContext, DetailActivity::class.java)
            moveDetailActivity.putExtra(DetailActivity.EXTRA_GITHUB_USER, listGithubUser[position])
            mContext.startActivity(moveDetailActivity)
        }
    }

    override fun getItemCount(): Int {
        return listGithubUser.size
    }

    inner class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: de.hdodenhof.circleimageview.CircleImageView = itemView.findViewById(R.id.itemAvatar)
        var tvName: TextView = itemView.findViewById(R.id.itemLogin)
        var tvDesc: TextView = itemView.findViewById(R.id.itemDescription)
    }
}