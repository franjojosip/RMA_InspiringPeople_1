package ht.ferit.fjjukic.rma_lv2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ht.ferit.fjjukic.rma_lv2.InspiringPeopleListener
import ht.ferit.fjjukic.rma_lv2.R
import ht.ferit.fjjukic.rma_lv2.InspiringPerson
import kotlinx.android.synthetic.main.inspiring_people_list_item.view.*

class InspiringPeopleViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView){


    fun bind(inspiringPerson: InspiringPerson, inspiringPeopleListener: InspiringPeopleListener){
        itemView.inspiringPersonDateOfBirth.text = ("Date of birth: ${inspiringPerson.dateOfBirth}")
        itemView.inspiringPersonDsc.text = ("Description: ${inspiringPerson.shortDescription}")

        val requestOptions: RequestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this.itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(inspiringPerson.imagePath)
            .into(itemView.inspiringPersonImg)

        itemView.inspiringPersonImg.setOnClickListener { inspiringPeopleListener.onShowQuote(inspiringPerson.id) }
    }
}