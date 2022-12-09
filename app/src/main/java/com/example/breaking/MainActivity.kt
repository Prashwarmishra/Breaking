package com.example.breaking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chipGroup: ChipGroup = findViewById(R.id.chip_group)
        chipGroup.setOnCheckedStateChangeListener { group, checkedId ->

            for (id in checkedId) {
                val chip: Chip = group.findViewById(id)
                Log.d("checkedId", chip.text.toString())
            }
        }
    }

    fun handleLatestNewsButtonClick(view: View) {
//        val intent = Intent(this, )
    }


}