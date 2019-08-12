package com.twoam.dimpro.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.twoam.dimpro.R
import com.twoam.dimpro.R.string.button
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.bumptech.glide.Glide.init


class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {


    //region Members
    //endregion

    //region Events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v!!.id) {
            R.id.rlOption1 -> {
                rlOption1.isPressed = true
                rlOption2.isPressed = false
            }

            R.id.rlOption2 -> {
                rlOption1.isPressed = false
                rlOption2.isPressed = true
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSubmit -> {

            if(rlOption1.isPressed)
                rlOption1.isPressed=true


                if(rlOption2.isPressed)
                    rlOption2.isPressed=true

                startActivity(Intent(this@LoginActivity, TitleActivity::class.java))
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,TitleActivity::class.java))
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }
    //endregion

    //region Helper Functions
    private fun init() {
        rlOption1.setOnTouchListener(this)
        rlOption2.setOnTouchListener(this)
        btnSubmit.setOnClickListener(this)
    }

    //endregion

}
