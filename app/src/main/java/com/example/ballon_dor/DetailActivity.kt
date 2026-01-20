package com.example.ballon_dor

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataBallonDor = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("DATA_BALLONDOR", BallonDor::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("DATA_BALLONDOR")
        }

        if (dataBallonDor != null) {
            val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
            val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
            val imgDetailPhoto: ImageView = findViewById(R.id.img_detail_photo)
            val tvStats: TextView = findViewById(R.id.tv_stats)
            val btnShare: Button = findViewById(R.id.action_share)

            tvDetailName.text = dataBallonDor.name
            tvDetailDescription.text = dataBallonDor.description
            imgDetailPhoto.setImageResource(dataBallonDor.photo)
            tvStats.text = dataBallonDor.stats

            btnShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Pemenang Ballon d'Or: ${dataBallonDor.name}\n\n${dataBallonDor.description}")
                startActivity(Intent.createChooser(shareIntent, "Bagikan info ini"))
            }
        }
    }
}