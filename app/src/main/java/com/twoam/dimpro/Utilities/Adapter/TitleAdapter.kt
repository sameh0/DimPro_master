package com.twoam.dimpro.Utilities.Adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.twoam.dimpro.Model.Title
import com.twoam.dimpro.R


import java.util.ArrayList
import android.provider.Settings.System.DATE_FORMAT
import android.os.CountDownTimer
import android.view.MotionEvent
import com.twoam.dimpro.R.id.*
import com.twoam.dimpro.R.string.text1
import com.twoam.dimpro.Utilities.widget.CircleCountDownView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.titles_recycle_layout.view.*
import java.util.concurrent.TimeUnit


/**
 * Created by Mokhtar on 6/30/2019.
 */

class TitleAdapter(private val context: Context, private val titlesList: ArrayList<Title>)
    : RecyclerView.Adapter<TitleAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var title: Title = Title()
    private var handler = Handler()
    private var runnable: Runnable? = null
    private val formDays = 1000 * 60 * 60 * 24
    private var minutesInitTime=30L //30  minutes
    private var countDownInterval = 1000L //1 seconds
    private var minutesInMS = 1800000L //30 minutes
    private var format = "%02d:%02d:%02d"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleAdapter.MyViewHolder {

        val view = inflater.inflate(R.layout.titles_recycle_layout, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: TitleAdapter.MyViewHolder, position: Int) {
        title = titlesList[position]


        Glide.with(context).load(R.drawable.profile)
                .into(holder.titleImage)

        holder.tvxl.text = title.x1
        holder.tv12.text = title.x2
        holder.tvX2.text = title.x3
        holder.tv4.text = title.x4
        holder.tvX3.text = title.x5
        holder.tv_4.text = title.x6

        if (holder.cTimer != null) {
            holder.stopTimer()
        }

        holder.startTimer(minutesInMS, countDownInterval, holder.tvTimer)
        holder.ivTimer.endTime = 60
        holder.ivTimer.initTime = 30

//        initView(holder)



    }

    override fun getItemCount(): Int {
        return titlesList.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    fun initView(holder: TitleAdapter.MyViewHolder) {

//
        holder.ivTimer.endTime = 60
        holder.ivTimer.initTime = minutesInitTime
        holder.ivTimer.setListener(CircleCountDownView.OnFinishCycleProgressBar {
            if (holder.cTimer != null) {
                holder.cTimer!!.onFinish()
            }
        })

        startCountDownTimerMin(holder)
    }

    fun startCountDownTimerMin(holder: TitleAdapter.MyViewHolder) {
        holder.cTimer = object : CountDownTimer(holder.ivTimer.endTime * formDays /*final time**/, formDays.toLong() /*interval**/) {
            override fun onTick(millisUntilFinished: Long) {
                holder.ivTimer.onTick(holder.ivTimer)
                holder.tvTimer.text = "" + String.format(format,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            }

            override fun onFinish() {
            }
        }
        holder.cTimer!!.start()
    }

        fun cancelCounter(countdownTimer: CountDownTimer?) {
        countdownTimer?.cancel()
    }

    fun stopCountDown(holder: TitleAdapter.MyViewHolder) {
        cancelCounter(holder.cTimer)

        holder.ivTimer.clear()
//        mProgressMinute.clearAnimation()
        holder.ivTimer.isFirstTime = true

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnTouchListener {

        var titleImage: ImageView = itemView.findViewById(R.id.ivImage)
        var tvxl: TextView = itemView.findViewById(R.id.tvxl)
        var tv12: TextView = itemView.findViewById(R.id.tv12)
        var tvX2: TextView = itemView.findViewById(R.id.tvX2)
        var tv4: TextView = itemView.findViewById(R.id.tv4)
        var tvX3: TextView = itemView.findViewById(R.id.tvX3)
        var tv_4: TextView = itemView.findViewById(R.id.tv_4)
        var tvTimer: TextView = itemView.findViewById(R.id.tvTimer)
        var ivTimer: CircleCountDownView = itemView.findViewById(R.id.ivTimer)
        var cTimer: CountDownTimer? = null

        init {

//            itemView.setOnClickListener { v ->
//                // get position
//                val pos = adapterPosition
//                // check if item still exists
//                if (pos != RecyclerView.NO_POSITION) {
//                    title = titlesList[pos]
//                }
//            }

            tvxl.setOnTouchListener(this)
//            tvxl.setOnClickListener({
//                val pos = adapterPosition
//                // check if item still exists
//                if (pos != RecyclerView.NO_POSITION) {
//                    title = titlesList[pos]
//                }
//
//            })


            tvX2.setOnTouchListener(this)

//            tvX2.setOnClickListener({
//                val pos = adapterPosition
//                // check if item still exists
//                if (pos != RecyclerView.NO_POSITION) {
//                    title = titlesList[pos]
//                }
//
//            })


            tvX3.setOnTouchListener(this)
//            tvX3.setOnClickListener({
//                val pos = adapterPosition
//                // check if item still exists
//                if (pos != RecyclerView.NO_POSITION) {
//                    title = titlesList[pos]
//                }
//
//            })


            tvTimer.setOnClickListener({
                val pos = adapterPosition
                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {
                    title = titlesList[pos]
                }

            })


        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (v!!.id) {
                tvxl.id -> {
                    itemView.tvxl.isPressed = true
                    itemView.tvX2.isPressed = false
                    itemView.tvX3.isPressed = false

                }

                tvX2.id -> {
                    itemView.tvX2.isPressed = true
                    itemView.tvxl.isPressed = false
                    itemView.tvX3.isPressed = false
                }
                tvX3.id -> {
                    itemView.tvX2.isPressed = false
                    itemView.tvxl.isPressed = false
                    itemView.tvX3.isPressed = true
                }

            }
            return true
        }


        fun startTimer(minutesInMS: Long, countDownInterval: Long, tvTimer: TextView) {
            cTimer = object : CountDownTimer(minutesInMS, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    ivTimer.onTick(ivTimer)
                    tvTimer.text = "" + String.format(format,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));


                }

                override fun onFinish() {}
            }
            cTimer!!.start()

        }

        //called on back pressed or stop to avoid memory leak
        fun stopTimer() {
            if (cTimer != null)
                cTimer!!.cancel()
        }


    }
}
