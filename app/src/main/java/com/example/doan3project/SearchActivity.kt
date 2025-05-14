package com.example.doan3project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.KeyEvent
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3project.Model.ProductModel
import com.example.doan3project.databinding.ActivitySearchBinding
import com.example.doan3project.stored.SearchStored

class SearchActivity : AppCompatActivity() {
    private lateinit var searchStored: SearchStored
    private lateinit var listSearch: List<String>
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchStored = SearchStored(this)
        listSearch = searchStored.getSearch()

        for (x in 0..listSearch.size-1){
            print(listSearch)
            var adp = ArrayAdapter(this, android.R.layout.simple_list_item_1,listSearch)
            binding.historySearch.adapter=adp
        }

        binding.searchBox.setOnKeyListener(){
                _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                saveSearch()
                val i = Intent(this, AfterSearchActivity::class.java)
                i.putExtra("searchtext", binding.searchBox.text.toString())
                startActivity(i)
                true
            } else {
                false
            }
        }

    }

    private fun saveSearch(){
        //...
        searchStored.saveSearch(binding.searchBox.text.toString())
    }
}