package net.aquadc.advancedkotlinpatterns.feature.coroutines

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.GET
import java.lang.reflect.Type

private val impl =
        Retrofit.Builder()
                .baseUrl("http://ip-api.com/")
                .addConverterFactory(object : Converter.Factory() {
                    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>?, retrofit: Retrofit): Converter<ResponseBody, *>? =
                            when (type) {
                                IpApi.Location::class.java ->  Converter<ResponseBody, IpApi.Location> {
                                    val json = JSONObject(it.string())
                                    IpApi.Location(
                                            country = json.getString("country"),
                                            city = json.getString("city"),
                                            latitude = json.getDouble("lat"),
                                            longitude = json.getDouble("lon")
                                    )
                                }
                                else -> throw UnsupportedOperationException("unsupported type: $type")
                            }
                })
                .build()
                .create(IpService::class.java)

object IpApi : IpService by impl {

    class Location(
            val country: String,
            val city: String,
            val latitude: Double,
            val longitude: Double
    )

}

interface IpService {
    @get:GET("json")
    val myLocation: Call<IpApi.Location>
}