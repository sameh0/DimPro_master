package com.twoam.dimpro.View

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.twoam.dimpro.Model.Title
import com.twoam.dimpro.R
import com.twoam.dimpro.R.id.*
import com.twoam.dimpro.Utilities.Adapter.TitleAdapter
import com.twoam.dimpro.Utilities.General.A4BottomSheetDialog
import com.twoam.dimpro.Utilities.General.DisplayBottomSheetDialog
import com.twoam.dimpro.Utilities.General.IBottomSheetCallback
import com.twoam.dimpro.Utilities.General.SaveBottomSheetDialog
import kotlinx.android.synthetic.main.activity_title.*
import pub.devrel.easypermissions.EasyPermissions

class TitleActivity : AppCompatActivity(), View.OnClickListener, IBottomSheetCallback {


    //region Members

    private var titlesList = ArrayList<Title>()
    private var displayBottomSheet = DisplayBottomSheetDialog()
    private var saveBottomSheet = SaveBottomSheetDialog()
    private var a4BottomSheet = A4BottomSheetDialog()
    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_IMAGE_GALLERY = 2

    //endregion

    //region Events
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

            R.id.btnLogin -> {
                startActivity(Intent(this, LoginActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
                finish()
            }
            R.id.btnGallery -> {
                val galleryPermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

                if (EasyPermissions.hasPermissions(this, galleryPermissions[0])) {
                    openGallery()
                } else {
                    EasyPermissions.requestPermissions(this, "Access for storage",
                            101, *galleryPermissions)
                }
            }
            R.id.btnCamera -> {
                openCamera()
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            val bitmap = data!!.extras.get("data") as Bitmap
            if (bitmap != null) {
                Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
                ivImage.visibility = View.VISIBLE

                Glide.with(this)
                        .load(bitmap)
                        .into(ivImage)
//                startActivity(Intent(this, activityname ::class.java).putExtra("image", bitmap))
//            }

            }
        }
        if (requestCode == REQUEST_IMAGE_GALLERY &&
                resultCode == Activity.RESULT_OK) {
            ivImage.visibility=View.VISIBLE
            val contentURI = data!!.data ?: return
            val bitmap = MediaStore.Images.Media.getBitmap(this!!.contentResolver, contentURI)
            ivImage.setImageBitmap(bitmap)
//            startActivity(Intent(this, activity name::class.java).putExtra("image", bitmap))

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
        }
    }
//endregion

    //region Helper Function

    private fun init() {
        btnSave.setOnClickListener(this)
        btnDisplay.setOnClickListener(this)
        btnA4.setOnClickListener(this)
        ivProfile.setOnClickListener(this)
        btnZ.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        btnGallery.setOnClickListener(this)
        btnCamera.setOnClickListener(this)

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

    private fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        this.startActivityForResult(intent,
                REQUEST_IMAGE_CAPTURE)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY)
    }


    //endregion

}
