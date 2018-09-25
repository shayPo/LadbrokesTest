package test.polak.shay.ladbrokes.data.local

import android.arch.persistence.room.TypeConverter


class Converters
{
        companion object {

            @TypeConverter
            @JvmStatic
            fun fromList(value: List<Int>): String {
                return if (value == null) "" else value.toString()
            }

            @TypeConverter
            @JvmStatic
            fun toList(s: String?): List<Int> {
                return listOf<Int>()
            }
        }
    /*
    @TypeConverter
    @JvmStatic
    fun ListToString(value: List<Int>?) = ""//{if(value == null) "" else  value.toString()}

    @TypeConverter
    @JvmStatic
    fun StringToList(s : String?) = listOf<Movie>()
    */
}