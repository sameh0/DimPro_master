package com.twoam.dimpro.Utilities.General

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.twoam.dimpro.Utilities.Adapter.TitleAdapter

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TitleAdapter.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
