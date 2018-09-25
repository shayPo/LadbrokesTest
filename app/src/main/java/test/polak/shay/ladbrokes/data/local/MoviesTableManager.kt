package test.polak.shay.ladbrokes.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import test.polak.shay.ladbrokes.model.Movie

@Dao
interface MoviesTableManager
{
    @Insert
    fun insert(movie : Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * FROM movies_table")
    fun getAllData(): List<Movie>

    @Query("DELETE FROM movies_table")
    fun deleteAll()
}