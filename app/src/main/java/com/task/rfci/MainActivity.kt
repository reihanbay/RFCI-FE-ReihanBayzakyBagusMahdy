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

    private lateinit var btnReverse : Button
    private lateinit var btnUndoRedo : Button
    private lateinit var etInput : EditText
    private lateinit var tvOutput : TextView
    lateinit var basicText: String
    lateinit var textReversed: String
    var lastClickTime: Long = 0
    var clicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.et_input)
        tvOutput = findViewById(R.id.tv_output)
        btnReverse = findViewById(R.id.btn_reverse)
        btnUndoRedo = findViewById(R.id.btn_undo_redo)

        basicText = etInput.text.toString()
        textReversed = basicText.reversed()
        if(etInput.text.isNotEmpty()){
            tvOutput.text = etInput.text.toString()
        }
        btnReverse.setOnClickListener(this)
        btnUndoRedo.setOnClickListener (this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btn_reverse -> {
                if (etInput.text.isEmpty()) {
                    etInput.error = "Inputan Kosong"
                } else {
                    tvOutput.text = etInput.text.toString().reversed()
                }
            }
            R.id.btn_undo_redo -> {
                clicked++
                val clickTime = System.currentTimeMillis()
                if (clickTime - lastClickTime > 300 && etInput.text.isNotEmpty()) {
                    tvOutput.text = etInput.text.toString()
                } else if (clickTime - lastClickTime < 300  && etInput.text.isNotEmpty()){
                    tvOutput.text = etInput.text.toString().reversed()
                } else {
                    etInput.error = "Inputan Kosong"
                }
                lastClickTime = clickTime
            }
        }
    }

}