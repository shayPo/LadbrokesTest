package test.polak.shay.ladbrokes.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import test.polak.shay.ladbrokes.model.Movie


@Database(entities = [ Movie::class ], version =  1)
@TypeConverters(Converters::class)
abstract class LocalMovieDataBase : RoomDatabase()
{
    abstract fun mangeMoviesData() : MoviesTableManager
}