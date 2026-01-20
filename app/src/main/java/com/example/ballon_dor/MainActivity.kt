package com.example.ballon_dor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvBallonDor: RecyclerView
    private val list = ArrayList<BallonDor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBallonDor = findViewById(R.id.rv_ballondor)
        rvBallonDor.setHasFixedSize(true)

        list.addAll(getListBallonDor())
        showRecyclerList()
    }

    private fun getListBallonDor(): ArrayList<BallonDor> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = typedArrayOf(
            R.drawable.ballon_dor_2025,
            R.drawable.ballon_dor_2024,
            R.drawable.ballon_dor_2023,
            R.drawable.ballon_dor_2022,
            R.drawable.ballon_dor_2021,
            R.drawable.ballon_dor_2019,
            R.drawable.ballon_dor_2018,
            R.drawable.ballon_dor_2017,
            R.drawable.ballon_dor_2016,
            R.drawable.ballon_dor_2015
        )
        val stats = resources.getStringArray(R.array.data_stats)
        val listBallonDor = ArrayList<BallonDor>()
        for (i in dataName.indices) {
            val ballonDor = BallonDor(dataName[i], dataDescription[i], dataPhoto[i], stats[i])
            listBallonDor.add(ballonDor)
        }
        return listBallonDor
    }

    private fun typedArrayOf(vararg resourceIds: Int): IntArray {
        return resourceIds
    }

    private fun showRecyclerList() {
        rvBallonDor.layoutManager = LinearLayoutManager(this)
        val listBallonDorAdapter = ListBallonDorAdapter(list)
        rvBallonDor.adapter = listBallonDorAdapter

        listBallonDorAdapter.setOnItemClickCallback(object : ListBallonDorAdapter.OnItemClickCallback {
            override fun onItemClicked(data: BallonDor) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA_BALLONDOR", data)
                startActivity(intentToDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> { // ID yang wajib tadi
                // Pindah ke halaman AboutActivity
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}