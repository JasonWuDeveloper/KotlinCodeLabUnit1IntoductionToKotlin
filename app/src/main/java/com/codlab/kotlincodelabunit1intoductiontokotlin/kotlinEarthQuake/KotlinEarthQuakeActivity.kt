package com.codlab.kotlincodelabunit1intoductiontokotlin.kotlinEarthQuake

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.codlab.kotlincodelabunit1intoductiontokotlin.EarthQuake.EarthQuakeAdapter
import com.codlab.kotlincodelabunit1intoductiontokotlin.EarthQuake.QueryUtils
import com.codlab.kotlincodelabunit1intoductiontokotlin.databinding.EarthquakeActivityBinding

class KotlinEarthQuakeActivity: AppCompatActivity() {
    private lateinit var binding: EarthquakeActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EarthquakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var earthquakes = QueryUtils.extractEarthquakes()

//        var earthquakeListView= binding.list

        val adapter = EarthQuakeAdapter(this, earthquakes)

        binding.list.adapter = adapter

        binding.list.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, view: View, position: Int, long: Long) {
                var currentEarthquake = adapter.getItem(position)

                var earthquakeUri = Uri.parse(currentEarthquake?.url)

                var websiteIntent = Intent(Intent.ACTION_VIEW,earthquakeUri)

                startActivity(websiteIntent)
            }

        })
    }
}