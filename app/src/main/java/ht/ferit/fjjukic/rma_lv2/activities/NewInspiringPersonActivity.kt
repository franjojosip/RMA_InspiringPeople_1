package ht.ferit.fjjukic.rma_lv2.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.util.*
import android.widget.Button
import ht.ferit.fjjukic.rma_lv2.R
import ht.ferit.fjjukic.rma_lv2.models.InspiringPerson
import ht.ferit.fjjukic.rma_lv2.repository.CodeRepository
import ht.ferit.fjjukic.rma_lv2.repository.PeopleRepository

class NewInspiringPersonActivity : AppCompatActivity() {
    private lateinit var tvEnterDate: TextView
    private lateinit var etDescription: EditText
    private lateinit var etQuote1: EditText
    private lateinit var etQuote2: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var btnChooseImage: Button
    private var imagePath: String = ""
    private lateinit var dateOfBirth: LocalDate
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_inspiring_person)
        setViews()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        this.btnSave = findViewById(R.id.btnSave)
        this.btnCancel = findViewById(R.id.btnCancel)
        this.btnChooseImage = findViewById(R.id.btnChooseImage)

        btnSave.setOnClickListener {
            when {
                tvEnterDate.text.isNotEmpty() && etDescription.text.isNotEmpty() && etQuote1.text.isNotEmpty() && etQuote2.text.isNotEmpty() && imagePath.isNotEmpty() -> {
                    val person = createInspiringPerson()
                    PeopleRepository.addInspiringPerson(person)
                    onBackPressed()

                }
                else -> Toast.makeText(
                    this,
                    "Some fields are missing, please check it.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCancel.setOnClickListener {
            onBackPressed()
        }

        btnChooseImage.setOnClickListener {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED
            ) {
                val permissions: Array<String> = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, CodeRepository.PERMISSION_CODE)
            } else {
                chooseImageFromGallery()
            }
        }

        this.tvEnterDate.setOnClickListener {

            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val newDate: LocalDate = LocalDate.of(year, month, dayOfMonth)
                    this.tvEnterDate.text = newDate.toString()
                    this.dateOfBirth = newDate
                },
                year,
                month,
                day
            )
            dpd.show()

        }
    }

    private fun createInspiringPerson(): InspiringPerson {

        val quotes: MutableList<String> = mutableListOf()
        if (this.etQuote1.text.toString().isNotEmpty()) {
            quotes.add(this.etQuote1.text.toString())
        }
        if (this.etQuote2.text.toString().isNotEmpty()) {
            quotes.add(this.etQuote2.text.toString())
        }
        return InspiringPerson(
            PeopleRepository.count() + 1,
            LocalDate.of(this.dateOfBirth.year, this.dateOfBirth.month, dateOfBirth.dayOfMonth),
            this.etDescription.text.toString(),
            quotes,
            imagePath
        )
    }

    private fun setViews() {
        this.tvEnterDate = findViewById(R.id.tvEnterDate)
        this.etDescription = findViewById(R.id.etDescription)
        this.etQuote1 = findViewById(R.id.etQuote1)
        this.etQuote2 = findViewById(R.id.etQuote2)
        this.calendar = Calendar.getInstance()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CodeRepository.PERMISSION_CODE -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                chooseImageFromGallery()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, CodeRepository.IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CodeRepository.IMAGE_PICK_CODE) {
            this.imagePath = data?.data.toString()
        }
    }
}