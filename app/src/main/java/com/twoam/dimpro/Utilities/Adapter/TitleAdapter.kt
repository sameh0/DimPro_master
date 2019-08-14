package com.twoam.dimpro.Utilities.Adapter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.twoam.dimpro.Model.Title
import com.twoam.dimpro.R
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import com.twoam.dimpro.Utilities.General.PrefUtil
import com.twoam.dimpro.Utilities.General.TimerExpiredReceiver
import com.twoam.dimpro.Utilities.widget.CircleCountDownView
import kotlinx.android.synthetic.main.titles_recycle_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


/**
 * Created by Mokhtar on 6/30/2019.
 */

class TitleAdapter(private val context: Context, private val titlesList: ArrayList<Title>)
    : RecyclerView.Adapter<TitleAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var title: Title = Title()
    private var countDownInterval = 1000L //1 seconds
    private var minutesInMS = 1800000L //30 minutes
    private var totalMS = 1800L //30 minutes
    private var format = "%02d:%02d:%02d"
    private var listHolders = ArrayList<MyViewHolder>()
    private var timerState = TimerState.Stopped

    enum class TimerState {
        Stopped, Paused, Running
    }

    companion object {
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long {
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleAdapter.MyViewHolder {

        val view = inflater.inflate(R.layout.titles_recycle_layout, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: TitleAdapter.MyViewHolder, position: Int) {
        title = titlesList[position]

        holder.minutesRemaining = holder.getTimeRemaining(holder.expireDate)

//        if (listHolders.contains(holder)) {
//
//
//        } else {
        holder.id = position
        listHolders.add(holder)
        Glide.with(context).load(R.drawable.profile)
                .into(holder.titleImage)

        holder.tvxl.text = title.x1
        holder.tv12.text = title.x2
        holder.tvX2.text = title.x3
        holder.tv4.text = title.x4
        holder.tvX3.text = title.x5
        holder.tv_4.text = title.x6



        if (holder.cTimer != null)
            holder.cTimer!!.cancel()

            holder.ivTimer.endTime = (holder.minutesRemaining) /  60
        holder.ivTimer.initTime = 0

        holder.startTimer(holder.minutesRemaining, countDownInterval, holder.tvTimer)

//        }

    }

    override fun getItemCount(): Int {
        return titlesList.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnTouchListener {

        var timerLengthSeconds: Long = 0
        var timerState = TimerState.Stopped
        var minutesRemaining: Long = 0
        var expireDate = "August 14, 2019 at 15:30:47 PM UTC+2"
        var id = 0
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

            tvxl.setOnTouchListener(this)
            tvX2.setOnTouchListener(this)
            tvX3.setOnTouchListener(this)

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

                override fun onFinish() {
                }
            }
            cTimer!!.start()

        }

        fun getTimeRemaining(expireDate: String): Long {//"August 14, 2019 at 15:30:47 PM UTC+2"
            val offset = TimeZone.getDefault().rawOffset + TimeZone.getDefault().dstSavings
            val now = System.currentTimeMillis() + offset

            var dateTime = expireDate.split(" at")
            var newDate = dateTime[0] + dateTime[1]
            val dateFormatter = SimpleDateFormat("MMMM dd, yyyy HH:mm:ss aa")


            val expireDate = dateFormatter.parse(newDate)
            val currentDate = Calendar.getInstance().time

            return (expireDate.time - currentDate.time)
        }

        private fun getDate(ourDate: String): String {
            var ourDate = ourDate
            try {
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                formatter.timeZone = TimeZone.getTimeZone("UTC")
                val value = formatter.parse(ourDate)

                val dateFormatter = SimpleDateFormat("MM-dd-yyyy HH:mm") //this format changeable
                dateFormatter.timeZone = TimeZone.getDefault()
                ourDate = dateFormatter.format(value)

                //Log.d("ourDate", ourDate);
            } catch (e: Exception) {
                ourDate = "00-00-0000 00:00"
            }

            return ourDate
        }

    }

}
