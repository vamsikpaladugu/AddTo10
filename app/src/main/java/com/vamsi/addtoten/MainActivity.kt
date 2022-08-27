package com.vamsi.addtoten

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    var tv:TextView? = null
    var et:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tv = findViewById(R.id.tvResult);
        et = findViewById(R.id.etExpression);

        val num = intent.getIntExtra("exp",0);
        findViewById<EditText>(R.id.etExpression).setText(num.toString());

        generate(null);

    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            if (intent?.action == "result") {

                val res = intent.getStringArrayListExtra("res");

                tv?.append("Count "+res?.size);
                tv?.append("\n");


                res?.forEach { str ->

                    tv?.append(str);
                    tv?.append("\n");

                }


            }
        }
    }

    override fun onStart() {

        LocalBroadcastManager.getInstance(this).registerReceiver(broadCastReceiver,
            IntentFilter("result")
        )

        super.onStart()
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadCastReceiver)
        super.onStop()
    }

    fun generate(view: View?) {

        val exp = Integer.parseInt(et?.text.toString());

        tv?.text = ""

        val intent = Intent(this,MyIntentService::class.java)
        intent.putExtra("number",exp);
        startService(intent)


    }
}