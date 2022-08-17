package com.codlab.kotlincodelabunit1intoductiontokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codlab.kotlincodelabunit1intoductiontokotlin.databinding.ActivityMainBinding
import java.lang.reflect.Type
import java.text.NumberFormat

class MainActivity : AppCompatActivity(),MVPContract.View {
    lateinit var binding: ActivityMainBinding
    private var presenter: Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    presenter = Presenter(this)
        with(binding) {

            btnCalculate.setOnClickListener {
                calculateTip()
            }
        }
    }
    fun updateRadioType(type: SelectId) {
        with(binding) {
            when(type) {
                SelectId.TWENTY -> optionTwentyPercent
                SelectId.EIGHTEEN -> optionEighteenPercent
                else -> optionFifteenPercent

            }
        }
    }
    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
   override fun updateTipResult(result: String) {
        binding.tipResult.text = result
    }fff
}