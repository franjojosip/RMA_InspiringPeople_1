package ht.ferit.fjjukic.rma_lv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var inspiringPersonAdapter: InspiringPeopleRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val inspiringPeopleListener = object: InspiringPeopleListener{
            override fun onShowQuote(index: Int) {
                Toast.makeText(applicationContext, PeopleRepository.getQuote(index), Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this@MainActivity.inspiringPersonAdapter = InspiringPeopleRecyclerAdapter(PeopleRepository.getListOfInspiringPeople(), inspiringPeopleListener)
            adapter = this@MainActivity.inspiringPersonAdapter
        }
    }
}
