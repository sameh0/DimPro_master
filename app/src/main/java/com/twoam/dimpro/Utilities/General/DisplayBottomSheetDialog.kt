package com.twoam.dimpro.Utilities.General


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.CardView
import android.view.*
import android.widget.Button
import android.widget.ImageView
import com.twoam.dimpro.R


class DisplayBottomSheetDialog : BottomSheetDialogFragment(), IBottomSheetCallback, View.OnClickListener {


    //region Members

    internal var view: ViewGroup? = null
    lateinit var layout: CardView
    private var closeListener: IBottomSheetCallback? = null
    private var action: Int = 0
    private var ivImage: ImageView? = null
    private  lateinit  var btnBtnO: Button
    private  lateinit var btnBtnZ: Button


    var Action: Int //0 log out 1 delete 2 cancel order
        get() {
            return action
        }
        set(action) {
            this.action = action
        }


    //endregion

    //region Events
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.bottom_sheet_display, container, false) as ViewGroup
        layout = view!!.findViewById(R.id.rlOptions)

        init()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is IBottomSheetCallback) {
            closeListener = context
        } else {
            throw ClassCastException(context.toString() + " must implement IBottomSheetCallback.onBottomSheetSelectedItem")
        }

    }


    override fun onBottomSheetSelectedItem(index: Int) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBtnO -> {
                this.dismiss()
                action = 0
                navigate(action)
            }
            R.id.btnBtnZ -> {
                this.dismiss()
                action = 1
                navigate(action)
            }
        }
    }
    //endregion

    //region Helper Functions
    fun init() {

        btnBtnO = layout.findViewById(R.id.btnBtnO)
        btnBtnZ = layout.findViewById(R.id.btnBtnZ)

        btnBtnO.setOnClickListener(this)
        btnBtnZ.setOnClickListener(this)

        if (Action == 0) //btnBtnO for loading purpose
        {
            process()
        } else if (Action == 1)//btnBtnO
        {
            process()
        }


    }

    fun navigate(index: Int) {  // for action purpose
        closeListener?.onBottomSheetSelectedItem(index)
    }

    private fun process() {

//        context?.startActivity(Intent(context, LoginActivity::class.java))
//        activity?.onBackPressed()

    }


//endregion
}
