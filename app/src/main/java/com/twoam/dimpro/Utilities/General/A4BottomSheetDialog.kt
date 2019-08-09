package com.twoam.dimpro.Utilities.General


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.CardView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.twoam.dimpro.R


class A4BottomSheetDialog : BottomSheetDialogFragment(), IBottomSheetCallback, View.OnClickListener {


    //region Members

    internal var view: ViewGroup? = null
    lateinit var layout: CardView
    private var listener: IBottomSheetCallback? = null
    private var action: Int = 0
    private lateinit var tvX1: TextView
    private lateinit var tvX2: TextView
    private lateinit var tvX3: TextView
    private lateinit var tv12: TextView
    private lateinit var tv4: TextView
    private lateinit var tv1: TextView




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
        view = inflater.inflate(R.layout.bottom_sheet_a4, container, false) as ViewGroup
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
            R.id.tvX1 -> {
                this.dismiss()
                action = 4
                navigate(action)
            }
            R.id.tvX2 -> {
                this.dismiss()
                action = 5
                navigate(action)
            }R.id.tvX3 -> {
                this.dismiss()
                action = 6
                navigate(action)
            }R.id.tv1 -> {
                this.dismiss()
                action = 7
                navigate(action)
            }R.id.tv4 -> {
                this.dismiss()
                action = 8
                navigate(action)
            }R.id.tv12 -> {
                this.dismiss()
                action = 9
                navigate(action)
            }
        }
    }
    //endregion

    //region Helper Functions
    fun init() {

        tvX1 = layout.findViewById(R.id.tvX1)
        tvX2 = layout.findViewById(R.id.tvX2)
        tvX3 = layout.findViewById(R.id.tvX3)
        tv1 = layout.findViewById(R.id.tv1)
        tv4 = layout.findViewById(R.id.tv4)
        tv12 = layout.findViewById(R.id.tv12)





        tvX1.setOnClickListener(this)
        tvX2.setOnClickListener(this)
        tvX3.setOnClickListener(this)
        tv1.setOnClickListener(this)
        tv4.setOnClickListener(this)
        tv12.setOnClickListener(this)


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
