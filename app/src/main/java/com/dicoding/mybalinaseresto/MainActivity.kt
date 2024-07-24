package com.dicoding.mybalinaseresto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private  lateinit var rvMenus: RecyclerView
    private val list = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMenus = findViewById(R.id.rv_menus)
        rvMenus.setHasFixedSize(true)

        list.addAll(getListMenus())
        showRecyclerList()
    }

    private fun showSelectedMenu(menu: Menu) {
        Toast.makeText(this, "Kamu memilih menu " + menu.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Explicit Intent ke about activity
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListMenus(): ArrayList<Menu> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMenu = ArrayList<Menu>()
        for (i in dataName.indices) {
            val menu = Menu(dataName[i], dataPrice[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listMenu.add(menu)
        }
        return listMenu
    }

    private fun showRecyclerList() {
        rvMenus.layoutManager = LinearLayoutManager(this)
        val listMenuAdapter = ListMenuAdapter(list)
        rvMenus.adapter = listMenuAdapter

        listMenuAdapter.setOnItemClickCallback(object : ListMenuAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Menu) {
                showSelectedMenu(data)
                // Explicit Intent ke detail activity
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_MENU, data)
                startActivity(detailIntent)
            }
        })
    }
}