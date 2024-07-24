package com.dicoding.mybalinaseresto

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MENU = "extra_menu"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnShare: Button = findViewById(R.id.button_share)
        btnShare.setOnClickListener {
            shareButtonClick()
        }

        // Sembunyikan judul aplikasi dari ActionBar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Tampilkan tombol panah kembali di ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Cek versi SDK
        val menu = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Menu>(EXTRA_MENU, Menu::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Menu>(EXTRA_MENU)
        }

        if (menu != null) {
            val imgPhoto: ImageView = findViewById(R.id.image_menu)
            val tvName: TextView = findViewById(R.id.text_menu_name)
            val tvPrice: TextView = findViewById(R.id.text_menu_price)
            val tvDescription: TextView = findViewById(R.id.text_menu_description)

            imgPhoto.setImageResource(menu.photo)
            tvName.text = menu.name
            tvPrice.text = menu.price
            tvDescription.text = menu.description
        }
    }

    private fun shareButtonClick() {
        //Implicit Intent fitur share
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Bagikan Halaman Detail Menu")
        startActivity(Intent.createChooser(shareIntent, "Bagikan via..."))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Kembali ke activity sebelumnya ketika tombol panah kembali ditekan
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}