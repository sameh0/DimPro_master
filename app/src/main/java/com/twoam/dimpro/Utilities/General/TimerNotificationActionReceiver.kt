package com.twoam.dimpro.Utilities.General

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.resocoder.timertutorial.AppConstants
import com.twoam.dimpro.Utilities.Adapter.TitleAdapter

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            AppConstants.ACTION_STOP -> {
                TitleAdapter.removeAlarm(context)
                PrefUtil.setTimerState(TitleAdapter.TimerState.Stopped, context)

            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = TitleAdapter.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                TitleAdapter.removeAlarm(context)
                PrefUtil.setTimerState(TitleAdapter.TimerState.Paused, context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = TitleAdapter.setAlarm(context, TitleAdapter.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(TitleAdapter.TimerState.Running, context)

            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = TitleAdapter.setAlarm(context, TitleAdapter.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(TitleAdapter.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

            }
        }
    }
}
