package com.example.doan3project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_cart -> {
                    val intent = Intent(this, ShopingCartActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                else -> false
            }

        }

    }
    private fun isProfileActivity(): Boolean {
        val packageName = "com.example.doan3project"
        val mainActivityName = "com.example.doan3project.ProfileActivity"

        val currentPackageName = this.packageName

        val currentClassName = this.javaClass.name

        return packageName == this.packageName && mainActivityName == this.javaClass.name
    }
}