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
import com.twoam.dimpro.R.string.text1
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

    private var countDownInterval = 1000L //1 seconds
    private var minutesInMS = 1800000L //30 minutes
    private var format = "%02d:%02d:%02d"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleAdapter.MyViewHolder {

        val view = inflater.inflate(R.layout.titles_recycle_layout, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: TitleAdapter.MyViewHolder, position: Int) {
        title = titlesList[position]

///////////////// edit for api later reusing ///////////////////
//        Glide.with(context).load(title.image)
//                .into(holder.titleImage)

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

    }

    override fun getItemCount(): Int {
        return titlesList.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleImage: ImageView = itemView.findViewById(R.id.ivImage)
        var tvxl: TextView = itemView.findViewById(R.id.tvxl)
        var tv12: TextView = itemView.findViewById(R.id.tv12)
        var tvX2: TextView = itemView.findViewById(R.id.tvX2)
        var tv4: TextView = itemView.findViewById(R.id.tv4)
        var tvX3: TextView = itemView.findViewById(R.id.tvX3)
        var tv_4: TextView = itemView.findViewById(R.id.tv_4)
        var tvTimer: TextView = itemView.findViewById(R.id.tvTimer)
        var cTimer: CountDownTimer? = null

        init {

            itemView.setOnClickListener { v ->
                // get position
                val pos = adapterPosition
                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {
                    title = titlesList[pos]
                }
            }

            tvxl.setOnClickListener({
                val pos = adapterPosition
                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {
                    title = titlesList[pos]
                }

            })




            tvX2.setOnClickListener({
                val pos = adapterPosition
                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {
                    title = titlesList[pos]
                }

            })


            tvX3.setOnClickListener({
                val pos = adapterPosition
                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {
                    title = titlesList[pos]
                }

            })




            tvTimer.setOnClickListener({
                val pos = adapterPosition
                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {
                    title = titlesList[pos]
                }

            })

        }


        fun startTimer(minutesInMS: Long, countDownInterval: Long, tvTimer: TextView) {
            cTimer = object : CountDownTimer(minutesInMS, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
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
