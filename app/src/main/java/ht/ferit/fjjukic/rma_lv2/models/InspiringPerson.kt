package ht.ferit.fjjukic.rma_lv2.models

import java.time.LocalDate

data class InspiringPerson(
    val id: Int,
    val dateOfBirth: LocalDate,
    val shortDescription: String,
    val quotes: MutableList<String>,
    val imagePath: String) {
}