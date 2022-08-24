package com.vamsi.addtoten

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.*

class MyIntentService(name: String?) : IntentService(name) {

    constructor() : this("MyIntentService") {

    }

    override fun onHandleIntent(intent: Intent?) {

        if (intent != null) {

            val ans = GeneratePossibility().generateSolution(intent.getIntExtra("number",0))

            val intSend = Intent("result");

            intSend.putStringArrayListExtra("res",ans);

            LocalBroadcastManager.getInstance(this).sendBroadcast(intSend);

        };
    }
}