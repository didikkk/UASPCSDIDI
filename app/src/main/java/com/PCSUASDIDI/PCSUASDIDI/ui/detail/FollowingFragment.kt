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
import com.PCSUASDIDI.PCSUASDIDI.data.viewmodel.FollowingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_following.*


class FollowingFragment : Fragment() {
    private lateinit var followingViewModel: FollowingFragmentViewModel
    private lateinit var adapter: UserAdapter

    companion object {
        private val ARG_USERNAME = "username"

        fun newInstance(username: String): FollowingFragment {
            val fragment = FollowingFragment()
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
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME) as String
        showRecyclerGithubUsers(username)

    }

    private fun showRecyclerGithubUsers(username: String) {
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        recyclerFollowing.layoutManager = LinearLayoutManager(activity)
        recyclerFollowing.adapter = adapter

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingFragmentViewModel::class.java)

        followingViewModel.setGithubUsers(requireActivity().application, username)

        followingViewModel.getGithubUsers().observe(viewLifecycleOwner, Observer { githubUserItems ->
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
        progressBarFollowing.visibility = if(state) View.VISIBLE else View.GONE
    }

}