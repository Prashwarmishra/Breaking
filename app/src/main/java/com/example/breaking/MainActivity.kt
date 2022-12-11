package com.example.breaking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    private val categories: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chipGroup: ChipGroup = findViewById(R.id.chip_group)
        chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            categories.clear()
            for (id in checkedId) {
                val chip: Chip = group.findViewById(id)
                categories.add(chip.text.toString())
            }
        }
    }

    fun handleLatestNewsButtonClick(view: View) {
        val customSearchInput: EditText = findViewById(R.id.search_box)
        val customSearchText: String = customSearchInput.text.toString()

        val intent = Intent(view.context, NewsActivity::class.java)
        intent.putExtra(NewsActivity.QUERY, customSearchText)
        intent.putExtra(NewsActivity.CATEGORIES, categories.toString())
        startActivity(intent)
    }
}