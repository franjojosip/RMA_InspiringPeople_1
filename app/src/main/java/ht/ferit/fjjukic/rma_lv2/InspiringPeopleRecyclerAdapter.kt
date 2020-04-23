package ht.ferit.fjjukic.rma_lv2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InspiringPeopleRecyclerAdapter(

    listOfPeople: MutableList<InspiringPerson>,
    inspiringPersonListener: InspiringPeopleListener

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var inspiringPeople: MutableList<InspiringPerson> = mutableListOf()
    private var inspiringPeopleListener: InspiringPeopleListener

    init{
        this.inspiringPeople.addAll(listOfPeople)
        this.inspiringPeopleListener = inspiringPersonListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return InspiringPeopleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.inspiring_people_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is InspiringPeopleViewHolder -> holder.bind(this.inspiringPeople[position], inspiringPeopleListener)
        }
    }

    override fun getItemCount(): Int {
        return this.inspiringPeople.size
    }

    fun setNewInspiringPeopleList(inspiringPeopleList: MutableList<InspiringPerson>){
        this.inspiringPeople.clear()
        this.inspiringPeople.addAll(inspiringPeopleList)
        this.notifyDataSetChanged()
    }
}