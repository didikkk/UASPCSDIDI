package com.PCSUASDIDI.PCSUASDIDI.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.PCSUASDIDI.PCSUASDIDI.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.setting)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val txtLanguage: View = findViewById(R.id.bahasa)
        txtLanguage.setOnClickListener {
            val setLanguage = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(setLanguage)
        }
    }
}