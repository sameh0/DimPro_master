package com.twoam.dimpro.Utilities.General


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.CardView
import android.view.*
import android.widget.Button
import com.twoam.dimpro.R


class SaveBottomSheetDialog : BottomSheetDialogFragment(), IBottomSheetCallback, View.OnClickListener {


    //region Members

    internal var view: ViewGroup? = null
    lateinit var layout: CardView
    private var listener: IBottomSheetCallback? = null
    private var action: Int = 0
    private lateinit var btnBtnW: Button
    private lateinit var btnBtnO: Button


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
        view = inflater.inflate(R.layout.bottom_sheet_save, container, false) as ViewGroup
        layout = view!!.findViewById(R.id.rlOptions)

        init()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is IBottomSheetCallback) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement IBottomSheetCallback.onBottomSheetSelectedItem")
        }

    }


    override fun onBottomSheetSelectedItem(index: Int) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBtnW -> {
                this.dismiss()
                action = 2
                navigate(action)
            }
            R.id.btnBtnO -> {
                this.dismiss()
                action = 3
                navigate(action)
            }
        }
    }
    //endregion

    //region Helper Functions
    fun init() {

        btnBtnW = layout.findViewById(R.id.btnBtnW)
        btnBtnO = layout.findViewById(R.id.btnBtnO)
        btnBtnW.setOnClickListener(this)
        btnBtnO.setOnClickListener(this)

        if (Action == 0) //btnBtnW for loading purpose
        {
            process()
        } else if (Action == 1)//btnBtnO
        {
            process()
        }


    }

    fun navigate(index: Int) {  // for action purpose
        listener?.onBottomSheetSelectedItem(index)
    }

    private fun process() {

//        context?.startActivity(Intent(context, LoginActivity::class.java))
//        activity?.onBackPressed()

    }


//endregion
}
