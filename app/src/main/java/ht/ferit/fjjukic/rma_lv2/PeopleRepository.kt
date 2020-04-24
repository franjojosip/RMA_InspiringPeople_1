package ht.ferit.fjjukic.rma_lv2

import java.time.LocalDate

object PeopleRepository {

    private val inspiringPeople: MutableList<InspiringPerson>
    init {
        inspiringPeople =
            getInspiringPeople()
    }
    private fun getInspiringPeople(): MutableList<InspiringPerson> {
        return mutableListOf(
            InspiringPerson(
                1, LocalDate.of(1973, 4, 26),
                "Larry Page is an internet entrepreneur and computer scientist who teamed up with grad school buddy Sergey Brin to launch the search engine Google in 1998.",
                mutableListOf(
                    "Always deliver more than expected.",
                    "Always work hard on something uncomfortably exciting."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/larrypage"
            ),
            InspiringPerson(
                2, LocalDate.of(1956, 1, 31),
                "Guido van Rossum is a Dutch programmer best known as the author of the Python programming language.",
                mutableListOf(
                    "If you decide to design your own language, there are thousands of sort of amateur language designer pitfalls.",
                    "My own perception of that is somewhat colored by where people ask my advice, which is still, of course, about changes to Python internals or at least standard libraries."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/guidovanrossum"
            ),
            InspiringPerson(
                3, LocalDate.of(1955, 10, 28),
                "Bill Gates started his career writing software as a child, creating his first program at age 13. By age 23, Gates and his colleagues created the Microsoft empire.",
                mutableListOf(
                    "Don’t compare yourself with anyone in this world…if you do so, you are insulting yourself.",
                    "I choose a lazy person to do a hard job. Because a lazy person will find an easy way to do it."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/billgates"
            ),
            InspiringPerson(
                4, LocalDate.of(1955, 6, 8),
                "Sir Timothy John Berners-Lee also known as TimBL, is an English engineer and computer scientist best known as the inventor of the World Wide Web.",
                mutableListOf(
                    "You affect the world by what you browse.",
                    "Innovation is serendipity, so you don't know what people will make."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/bernerslee"
            ),
            InspiringPerson(
                5, LocalDate.of(1961, 7, 4),
                "Brendan Eich developed the JavaScript, which is most widely used in HTML or browsing pages.",
                mutableListOf(
                    "Always bet on JavaScript.",
                    "The main idea with Brave is that you don't have to think about Bitcoin; you just have this frictionless payment system."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/brendanreich"
            ),
            InspiringPerson(
                6, LocalDate.of(1941, 9, 9),
                "Dennis Ritchie \"Father of the C programming language\" who also created UNIX operating system along with his long-time colleague Ken Thompson.",
                mutableListOf(
                    "UNIX is basically a simple operating system, but you have to be a genius to understand the simplicity.",
                    "C is quirky, flawed, and an enormous success."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/dennisritchie"
            ),
            InspiringPerson(
                7, LocalDate.of(1950, 12, 30),
                "Bjarne Stroustrup is a Danish-born computer scientist who developed the popular C++ programming language.",
                mutableListOf(
                    "There are only two kinds of languages: the ones people complain about and the ones nobody uses.",
                    "If you think it's simple, then you have misunderstood the problem."
                ), "android.resource://ht.ferit.fjjukic.rma_lv2/drawable/bjarnestroustrup"
            )
        )
    }
    fun getListOfInspiringPeople(): MutableList<InspiringPerson> =
        inspiringPeople
    fun count(): Int =  inspiringPeople.count()
    fun removeInspiringPerson(index: Int) =  inspiringPeople.removeAt(index)
    fun addInspiringPerson(inspiringPerson: InspiringPerson) =  inspiringPeople.add(inspiringPerson)
    fun getQuote(id: Int): String{
        val randomNumber: Int = (0..1).random()
        val person: InspiringPerson? = inspiringPeople.find { person -> person.id == id }
            return person?.quotes?.get(randomNumber).toString()
    }
}