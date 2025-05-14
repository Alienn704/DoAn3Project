package com.example.doan3project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3project.Model.ProductCartModel
import com.example.doan3project.adapter.ProCartAdapter
import com.example.doan3project.stored.UserStore
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShopingCartActivity : AppCompatActivity() {
    private lateinit var prListCart: ArrayList<ProductCartModel>
    private lateinit var userPref: UserStore
    private lateinit var bottomNavigationView: BottomNavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_cart)

        userPref= UserStore(applicationContext)
        prListCart= arrayListOf();
        getProductCart()

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

    private fun getProductCart() {
        findViewById<RecyclerView>(R.id.home_cart).layoutManager =
            GridLayoutManager(applicationContext, 1, RecyclerView.VERTICAL, false)
        prListCart.clear()
        val url = "http://192.168.1.5/doan3/getallproductcart.php?id_user=${userPref.getId()}"
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            for (x in 0 until response.length()) {
                prListCart.add(
                    ProductCartModel(
                        response.getJSONObject(x).getInt("id_product"),
                        response.getJSONObject(x).getInt("id_category_product"),
                        response.getJSONObject(x).getString("title_product"),
                        response.getJSONObject(x).getString("image_product"),
                        response.getJSONObject(x).getInt("price_product"),
                        response.getJSONObject(x).getInt("amount")
                    )
                )
            }
            if(prListCart.size == 0){
                Toast.makeText(this, "Khong co", Toast.LENGTH_SHORT).show()
            }else{
                val adp = ProCartAdapter(prListCart)
                adp.setOnItemClickListener(object : ProCartAdapter.OnClickListener {
                    override fun onClickBlog(position: Int) {
                        val intentSca = Intent(this@ShopingCartActivity, ProductDetails::class.java)
                        intentSca.putExtra("proid", prListCart[position].Id.toString())
                        intentSca.putExtra("title", prListCart[position].PrdName)
                        intentSca.putExtra("price", prListCart[position].PrdPrice.toString())
                        intentSca.putExtra("image", prListCart[position].PrdImages)
                        startActivity(intentSca)
                    }

                })
                findViewById<RecyclerView>(R.id.home_cart).adapter = adp
            }

        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)


    }

    private fun isShopingCart(): Boolean {
        val packageName = "com.example.doan3project"
        val mainActivityName = "com.example.doan3project.ShopingCartActivity"

        val currentPackageName = this.packageName

        val currentClassName = this.javaClass.name

        return packageName == this.packageName && mainActivityName == this.javaClass.name
    }
}