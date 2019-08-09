package com.twoam.dimpro.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.twoam.dimpro.Model.Title
import com.twoam.dimpro.R
import com.twoam.dimpro.Utilities.Adapter.TitleAdapter
import kotlinx.android.synthetic.main.activity_title.*

class TitleActivity : AppCompatActivity(), View.OnClickListener {


    private var titlesList = ArrayList<Title>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        init()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnText1 -> {
            }
           R.id.ivProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }
            R.id.btnZ -> {
            }
        }
    }

    private fun init() {
        btnText1.setOnClickListener(this)
        ivProfile.setOnClickListener(this)
        btnZ.setOnClickListener(this)

        getTitlesData()
    }

        private fun getTitlesData() {
            titlesList.add(Title(1, R.drawable.profile, "x1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "a1", "a2", "a3", "a4", "a5", "a6"))
            titlesList.add(Title(1, R.drawable.profile, "x1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "J1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "x1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "t1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "c1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "v1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "t1", "x2", "x3", "x4", "x5", "x6"))
            titlesList.add(Title(1, R.drawable.profile, "u1", "x2", "x3", "x4", "x5", "x6"))


            var adapter = TitleAdapter(this, titlesList)
            rvData.adapter = adapter
            rvData.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
}
