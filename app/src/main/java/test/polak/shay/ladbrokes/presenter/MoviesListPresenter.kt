package test.polak.shay.ladbrokes.presenter

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.content.Intent
import org.json.JSONObject
import test.polak.shay.ladbrokes.data.MovieDataListener
import test.polak.shay.ladbrokes.data.MoviesDataManager
import test.polak.shay.ladbrokes.model.Movie
import test.polak.shay.ladbrokes.view.MovieDetailsActivity
import test.polak.shay.ladbrokes.view.adapter.MoviesAdapter

class MoviesListPresenter  : MoviesDataPresenter(),LifecycleObserver, MovieDataListener {

    private var mListAdapter : MoviesAdapter? = null
    var mIsSearchOnScreen = false

    fun init(context: Context, adapter : MoviesAdapter)
    {
        mContext = context
        mListAdapter = adapter
        MoviesDataManager.getInstance(context).getTopRatedMovies(this)
    }

//////// MovieDataListener imp
    override fun onFailure()
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showServerData(data: JSONObject)
    {
        updateLocalMoviesData(data)
    }

    override fun showLocalData(data: Any)
    {
        mListAdapter?.updateMoviesList(data as List<Movie>)
    }
    ////////

    fun searchInMoviesList(word: String)
    {
        mIsSearchOnScreen = true
        mListAdapter?.search(word)
    }

    fun displayAllMoviesList()
    {
        mIsSearchOnScreen = false
        mListAdapter?.displayAllData()
    }

    fun refreshListData()
    {
        MoviesDataManager.getInstance(mContext!!).updateLocalMoviesData(listOf<Movie>())
        MoviesDataManager.getInstance(mContext!!).getTopRatedMovies(this)
    }

    fun displayFullDataScreen(data: Movie) {
        val intent = Intent(mContext!!, MovieDetailsActivity::class.java)
        intent.putExtra("movieData", data)
        mContext!!.startActivity(intent)
    }
}
