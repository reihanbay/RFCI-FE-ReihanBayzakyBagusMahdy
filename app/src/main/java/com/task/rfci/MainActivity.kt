package com.task.rfci

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPref: PreferenceUtil
    private lateinit var btnReverse : Button
    private lateinit var btnUndoRedo : Button
    private lateinit var etInput : EditText
    private lateinit var tvOutput : TextView
    var lastClickTime: Long = 0
    var addChar = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = PreferenceUtil(this)
        etInput = findViewById(R.id.et_input)
        tvOutput = findViewById(R.id.tv_output)
        btnReverse = findViewById(R.id.btn_reverse)
        btnUndoRedo = findViewById(R.id.btn_undo_redo)

        btnReverse.setOnClickListener(this)
        btnUndoRedo.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_reverse -> {
                if (etInput.text.isEmpty()) {
                    etInput.error = "Inputan Kosong"
                } else {
                    if (tvOutput.text.toString() == etInput.text.toString()){
                        tvOutput.text.toString().reversed()
                    }
                    tvOutput.text = etInput.text.toString().reversed()
                }
            }
            R.id.btn_undo_redo -> {
                val clickTime = System.currentTimeMillis()
                if (clickTime - lastClickTime < 200 && etInput.text.isNotEmpty()) {
                    var text = sharedPref.getString("TEXT_REDO").toString()
                    etInput.setText(text)
                    tvOutput.setText(text)

                } else if (clickTime - lastClickTime > 500  && etInput.text.isNotEmpty()){
                    var text = etInput.text.toString()
                    val size = text.length -2
                    addChar += text[size+1]
                    Log.d("TES", addChar)
                    val textSlice = text.slice(0..size)
                    sharedPref.put("TEXT_REDO", textSlice+addChar.reversed())
                    sharedPref.put("TEXT",textSlice)
                    etInput.setText(textSlice)
                    tvOutput.setText(textSlice)

                } else {
                    etInput.error = "Inputan Kosong"
                }
                lastClickTime = clickTime
            }
        }
    }

}