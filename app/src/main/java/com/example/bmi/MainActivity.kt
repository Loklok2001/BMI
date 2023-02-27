package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewBMI : ImageView = findViewById(R.id.imageViewBMI)

        val textViewBMI : TextView = findViewById(R.id.textView)
        val textViewStatus : TextView = findViewById(R.id.textViewStatus)

        val editTextWeight : EditText = findViewById(R.id.editTextWeight)
        val editTextHeight : EditText = findViewById(R.id.editTextHeight)

        val calculateButton : Button = findViewById(R.id.calculateButton)
        val resetButton : Button = findViewById(R.id.resetButton)

        calculateButton.setOnClickListener {
            if(editTextWeight.text.isEmpty()){
                editTextWeight.setError(getString(R.string.valueRequired))
                return@setOnClickListener
            }

            if(editTextHeight.text.isEmpty()){
                editTextHeight.setError(getString(R.string.valueRequired))
                return@setOnClickListener
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()

            if(weight <= 0){
                editTextWeight.setError(getString(R.string.valueMin))
                return@setOnClickListener
            }

            if(height <= 0){
                editTextHeight.setError(getString(R.string.valueMin))
                return@setOnClickListener
            }

            var bmi = weight/(height/100).pow(2)
            textViewBMI.text = String.format("%s : %.2f" , getString(R.string.bmi) , bmi);
            if(bmi < 18.5){
                imageViewBMI.setImageResource(R.drawable.under)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.under))
            }else if(bmi > 25){
                imageViewBMI.setImageResource(R.drawable.over)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.over))
            }else{
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }
        }

        resetButton.setOnClickListener {
            editTextWeight.text.clear()
            editTextHeight.text.clear()
            imageViewBMI.setImageResource(R.drawable.empty)
            textViewStatus.text = getString(R.string.status )
            textViewBMI.text =getString(R.string.bmi )
        }
    }
}