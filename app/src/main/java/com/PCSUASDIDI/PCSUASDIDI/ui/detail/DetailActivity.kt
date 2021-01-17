package com.PCSUASDIDI.PCSUASDIDI.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.PCSUASDIDI.PCSUASDIDI.R
import com.PCSUASDIDI.PCSUASDIDI.adapter.SectionsPagerAdapter
import com.PCSUASDIDI.PCSUASDIDI.data.model.User
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_GITHUB_USER = "extra_github_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.apply {
            title = ""
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }

        setContentView(R.layout.activity_detail)

        showDetailGithubUser()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun showDetailGithubUser() {
        val extraGithubUser = intent.getParcelableExtra(EXTRA_GITHUB_USER) as User?

        val tvLogin: TextView = findViewById(R.id.detailLogin)
        val tvdetailurl: TextView = findViewById(R.id.detailUrl)
        val ivavatar: ImageView = findViewById(R.id.detailAvatar)

        Glide.with(this).load(extraGithubUser?.avatarUrl).into(ivavatar)
        tvLogin.text = extraGithubUser?.login
        tvdetailurl.text = extraGithubUser?.htmlUrl

        val username = extraGithubUser?.login.toString()

        showSectionPagerAdapter(username)

    }

    private fun showSectionPagerAdapter(username: String) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.username = username
        tab_page.adapter = sectionsPagerAdapter
        detailTab.setupWithViewPager(tab_page)
    }
}