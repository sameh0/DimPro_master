package com.twoam.dimpro.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.twoam.dimpro.Model.Title
import com.twoam.dimpro.R
import com.twoam.dimpro.Utilities.Adapter.TitleAdapter
import com.twoam.dimpro.Utilities.General.A4BottomSheetDialog
import com.twoam.dimpro.Utilities.General.DisplayBottomSheetDialog
import com.twoam.dimpro.Utilities.General.IBottomSheetCallback
import com.twoam.dimpro.Utilities.General.SaveBottomSheetDialog
import kotlinx.android.synthetic.main.activity_title.*

class TitleActivity : AppCompatActivity(), View.OnClickListener, IBottomSheetCallback {


    private var titlesList = ArrayList<Title>()
    private var displayBottomSheet = DisplayBottomSheetDialog()
    private var saveBottomSheet = SaveBottomSheetDialog()
    private var a4BottomSheet = A4BottomSheetDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        init()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSave -> {
                saveBottomSheet.Action = 0
                saveBottomSheet.show(supportFragmentManager, "displayBottomSheet")
            }
            R.id.btnDisplay -> {
                displayBottomSheet.Action = 1
                displayBottomSheet.show(supportFragmentManager, "displayBottomSheet")
            }
            R.id.btnA4 -> {
                a4BottomSheet.Action = 2
                a4BottomSheet.show(supportFragmentManager, "displayBottomSheet")
            }

            R.id.ivProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }
            R.id.btnZ -> {
                startActivity(Intent(this, TitleYActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }
        }
    }

    override fun onBottomSheetSelectedItem(index: Int) {
        when (index) {
            0 ->  //DisplayBottomSheetDialog  -> btnBtnO result
            {
                Toast.makeText(this, "DisplayBottomSheetDialog  btnBtnO -> result", Toast.LENGTH_SHORT).show()
            }
            1 -> // DisplayBottomSheetDialog -> btnZ2 result
            {
                Toast.makeText(this, "DisplayBottomSheetDialog -> btnZ2 result", Toast.LENGTH_SHORT).show()
            }

            2 -> // SaveBottomSheetDialog -> btnBtnW result
            {
                Toast.makeText(this, "SaveBottomSheetDialog -> btnBtnW result", Toast.LENGTH_SHORT).show()
            }

            3 -> // SaveBottomSheetDialog -> btnBtnO result
            {
                Toast.makeText(this, "SaveBottomSheetDialog -> btnBtnO result", Toast.LENGTH_SHORT).show()
            }

            4 -> // A4BottomSheetDialog -> X1 result
            {
                Toast.makeText(this, "A4BottomSheetDialog -> X1 result", Toast.LENGTH_SHORT).show()
            }

            5 -> // A4BottomSheetDialog -> X2 result
            {
                Toast.makeText(this, "A4BottomSheetDialog -> X2 result", Toast.LENGTH_SHORT).show()
            }
            6 -> // A4BottomSheetDialog -> btnBtnO result
            {
                Toast.makeText(this, "A4BottomSheetDialog -> X3 result", Toast.LENGTH_SHORT).show()
            }
            7 -> // A4BottomSheetDialog -> btnBtnO result
            {
                Toast.makeText(this, "A4BottomSheetDialog -> 1 result", Toast.LENGTH_SHORT).show()
            }
            8 -> // A4BottomSheetDialog -> btnBtnO result
            {
                Toast.makeText(this, "A4BottomSheetDialog -> 4 result", Toast.LENGTH_SHORT).show()
            }
            9 -> // A4BottomSheetDialog -> btnBtnO result
            {
                Toast.makeText(this, "A4BottomSheetDialog -> 12 result", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        btnSave.setOnClickListener(this)
        btnDisplay.setOnClickListener(this)
        btnA4.setOnClickListener(this)
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
