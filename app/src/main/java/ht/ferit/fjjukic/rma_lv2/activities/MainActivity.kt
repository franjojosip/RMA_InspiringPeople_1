package ht.ferit.fjjukic.rma_lv2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ht.ferit.fjjukic.rma_lv2.interfaces.InspiringPeopleListener
import ht.ferit.fjjukic.rma_lv2.recyclerView.InspiringPeopleRecyclerAdapter
import ht.ferit.fjjukic.rma_lv2.R
import ht.ferit.fjjukic.rma_lv2.repository.PeopleRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var inspiringPersonAdapter: InspiringPeopleRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        setFloatingActionButtonListener()
    }

    private fun initRecyclerView() {
        val inspiringPeopleListener = object :
            InspiringPeopleListener {
            override fun onShowQuote(index: Int) {
                Toast.makeText(
                    applicationContext,
                    PeopleRepository.getQuote(index),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this@MainActivity.inspiringPersonAdapter =
                InspiringPeopleRecyclerAdapter(
                    PeopleRepository.getListOfInspiringPeople(),
                    inspiringPeopleListener
                )
            this.adapter = this@MainActivity.inspiringPersonAdapter
        }
    }

    private fun setFloatingActionButtonListener() {
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, NewInspiringPersonActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        this.inspiringPersonAdapter.setNewInspiringPeopleList(PeopleRepository.getListOfInspiringPeople())
    }
}
