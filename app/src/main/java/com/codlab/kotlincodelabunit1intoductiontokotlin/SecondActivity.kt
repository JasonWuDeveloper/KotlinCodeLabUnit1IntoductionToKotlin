package com.codlab.kotlincodelabunit1intoductiontokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codlab.kotlincodelabunit1intoductiontokotlin.EarthQuake.EarthquakeActivity
import com.codlab.kotlincodelabunit1intoductiontokotlin.databinding.ActivitySecondActivtyBinding
import com.codlab.kotlincodelabunit1intoductiontokotlin.kotlinEarthQuake.KotlinEarthQuakeActivity

class SecondActivity : AppCompatActivity() {
     private lateinit var binding: ActivitySecondActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondActivtyBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val i = Intent(this, KotlinEarthQuakeActivity::class.java)

        binding.btnIntentEarthQuakeActivity.setOnClickListener {
            startActivity(i)
        }
    }
}