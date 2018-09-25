package test.polak.shay.ladbrokes.presenter

import android.content.Context
import com.beust.klaxon.Klaxon
import org.json.JSONObject
import test.polak.shay.ladbrokes.data.MoviesDataManager
import test.polak.shay.ladbrokes.model.Movie

abstract class MoviesDataPresenter
{
    protected var mContext : Context? = null

    protected fun updateLocalMoviesData(data: JSONObject)
    {
        if(mContext != null) {
            val movieJsonArray = data.getJSONArray("results")
            val moviesArray = MutableList(movieJsonArray.length(), { index -> Klaxon().parse<Movie>(movieJsonArray[index].toString()) })
            MoviesDataManager.getInstance(mContext!!).updateLocalMoviesData(moviesArray.toList())
        }
    }
}