package com.PCSUASDIDI.PCSUASDIDI.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.PCSUASDIDI.PCSUASDIDI.R
import com.PCSUASDIDI.PCSUASDIDI.adapter.UserAdapter
import com.PCSUASDIDI.PCSUASDIDI.data.viewmodel.FollowerFragmentViewModel
import kotlinx.android.synthetic.main.fragment_follower.*


class FollowerFragment : Fragment() {

    private lateinit var followerViewModel: FollowerFragmentViewModel
    private lateinit var adapter: UserAdapter

    companion object {
        private val ARG_USERNAME = "username"

        fun newInstance(username: String): FollowerFragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments= bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME) as String
        showRecyclerGithubUsers(username)

    }

    private fun showRecyclerGithubUsers(username: String) {
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        recyclerFollower.layoutManager = LinearLayoutManager(activity)
        recyclerFollower.adapter = adapter

        followerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowerFragmentViewModel::class.java)

        followerViewModel.setGithubUsers(requireActivity().application, username)

        followerViewModel.getGithubUsers().observe(viewLifecycleOwner, Observer { githubUserItems ->
            if(githubUserItems !== null) {
                adapter.setData(githubUserItems)
                showLoading(false)
            } else {
                adapter.setData(arrayListOf())
                showLoading(true)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        progressBarFollower.visibility = if(state) View.VISIBLE else View.GONE
    }
}