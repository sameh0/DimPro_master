package com.twoam.dimpro.View

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.twoam.dimpro.Model.Title
import com.twoam.dimpro.R
import com.twoam.dimpro.Utilities.Adapter.TitleAdapter
import com.twoam.dimpro.Utilities.General.IBottomSheetCallback
import com.twoam.dimpro.Utilities.General.SaveBottomSheetDialog
import kotlinx.android.synthetic.main.activity_title_y.*

class TitleYActivity : AppCompatActivity(), View.OnClickListener, IBottomSheetCallback {

    //region Members
    private var titlesList = ArrayList<Title>()
    private var bottomSheet = SaveBottomSheetDialog()
    //endregion

    //region Events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_y)

        init()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                startActivity(Intent(this, TitleActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }
            R.id.tvTitle -> // for test bottom sheet purpose
            {
                bottomSheet.Action = 0
                bottomSheet.show(supportFragmentManager, "bottomSheet")
            }
            R.id.tvActK -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }

            R.id.btnZ -> {
            }
        }
    }

    override fun onBottomSheetSelectedItem(index: Int) {
        when (index) {
            0 ->  //btnBtnW result
            {
                Toast.makeText(this,"btnBtnW result",Toast.LENGTH_SHORT).show()
            }
            1 -> //btnBtnO result
            {
                Toast.makeText(this,"btnBtnO result",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //endregion

    //region Helper Functions
    private fun init() {
        tvTitle.setOnClickListener(this)
        ivBack.setOnClickListener(this)
        tvActK.setOnClickListener(this)
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
    //endregion
}
