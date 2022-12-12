package az.khayalsharifli.algoritmatask.tools

import com.squareup.moshi.Moshi

object Utils {
    inline fun <reified T> jsonToModel(data: String): T {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(T::class.java)
        val product = jsonAdapter.fromJson(data)
        return product!!
    }
}