package com.huzaifa.bmicalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.huzaifa.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateBtn.setOnClickListener {
            calculateBMI();
        }
    }

    private fun calculateBMI(){
        val weight = binding.weigthEdit.text.toString().toFloatOrNull();
        val height = binding.heightEdit.text.toString().toFloatOrNull();

        if(weight != null && height != null){
            val bmi = weight / (height/100).pow(2);
            val bmiResult = String.format("%.2f",bmi);

            val bmiCategory = when{
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal weight"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }
            binding.resultText.text = "BMI: $bmiResult\nCategory: $bmiCategory";
        }else{
            binding.resultText.text = "Invalid Input";
        }
    }
}