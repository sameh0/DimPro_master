package com.twoam.dimpro.View

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.twoam.dimpro.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    //region Members

    //endregion

    //region Events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile1)

        init()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rlBack -> {
                startActivity(Intent(this, TitleActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }
            R.id.ivShare -> {
              share()
            }
            R.id.rlAboutUs -> {
                openUrl("http://www.trolley-app.com")
            }
            R.id.rlTermsAndConditions -> {
                openUrl("http://www.trolley-app.com/terms-conditions/")
            }
            R.id.rlPrivacyPolicy -> {
                openUrl("http://www.trolley-app.com/privacy-policy/")
            }
            R.id.rlDeleteAccount -> {
                deleteAccount()
            }

        }
    }
    //endregion

    //region Helper Functions
    private fun init() {
        rlBack.setOnClickListener(this)
        ivShare.setOnClickListener(this)
        rlAboutUs.setOnClickListener(this)
        rlTermsAndConditions.setOnClickListener(this)
        rlPrivacyPolicy.setOnClickListener(this)
        rlDeleteAccount.setOnClickListener(this)

    }

    fun openUrl(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
    fun share()
    {
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "Here is the share content body"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

    fun deleteAccount()
    {}
    //endregion

}
