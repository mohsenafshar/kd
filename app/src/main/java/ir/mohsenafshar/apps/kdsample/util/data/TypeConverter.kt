package ir.mohsenafshar.apps.kdsample.util.data

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

object TypeConverter {

    inline fun <reified ListType> getResultAsList(list: List<Any>?): List<ListType> {
        if (list == null) return emptyList()
        return try {
            val gson = GsonBuilder().create()
            val json = gson.toJson(list)
            val type = TypeToken.getParameterized(ArrayList::class.java, ListType::class.java).type
            val obj: List<ListType> = gson.fromJson<List<ListType>>(json, type)
            obj
        } catch (e: IOException) {
            e.printStackTrace()
            ArrayList()
        }
    }

    fun <T> getResultAsList(clazz: Class<T>, list: List<Any>?): List<T> {
        return try {
            val gson = GsonBuilder().create()
            val json = gson.toJson(list)
            val obj: List<T> = gson.fromJson<List<T>>(json, getType(clazz))
            obj
        } catch (e: IOException) {
            e.printStackTrace()
            ArrayList()
        }
    }

    fun <T> getResultAsList(type: Type, list: List<Any>?): List<T>? {
        return try {
            val gson = GsonBuilder().create()
            val json = gson.toJson(list)
            val obj: List<T> = gson.fromJson<List<T>>(json, getListType(type))
            obj
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun <T> getType(clazz : Class<T>): Type {
        return TypeToken.getParameterized(ArrayList::class.java, clazz).type
    }

    private fun getListType(type: Type): Type {
        return TypeToken.getParameterized(List::class.java, type).type
    }
}