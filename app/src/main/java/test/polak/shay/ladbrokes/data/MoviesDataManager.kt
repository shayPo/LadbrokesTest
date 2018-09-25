package test.polak.shay.ladbrokes.data

import android.arch.persistence.room.Room
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import org.json.JSONObject
import test.polak.shay.ladbrokes.data.local.LocalMovieDataBase
import test.polak.shay.ladbrokes.data.server.TMDbDataClient
import test.polak.shay.ladbrokes.model.Movie

class MoviesDataManager : MovieDataListener
{
    private var mLocalData : LocalMovieDataBase?
    private var mServerData : TMDbDataClient
    private var mListener: MovieDataListener?
    private val mWorkHandler : Handler
    private val mMainHandler : Handler

    init
    {
        mListener = null
        mLocalData = null
        mServerData = TMDbDataClient(this)
        //init data base handler
        val handlerThread : HandlerThread = HandlerThread("DataBaseThread")
        handlerThread.start()
        mWorkHandler = Handler(handlerThread.getLooper())
        mMainHandler = Handler(Looper.getMainLooper())
    }

    fun init(context : Context)
    {
        mLocalData = Room.
                databaseBuilder(context, LocalMovieDataBase::class.java , "local_movies_data.db").
                build()
        mServerData.init(context)
    }
    ////////// MovieDataListener imp
    override fun onFailure(){}

    override fun showLocalData(data: Any) {
        if(mListener != null)
        {
            mListener!!.showLocalData(data)
        }
        mListener = null
    }

    override fun showServerData(data : JSONObject)
    {
        if(mListener != null)
        {
            mListener!!.showServerData(data)
        }
    }
    ////////////////

    private fun setListener(listener : MovieDataListener?)
    {
        if(listener != null)
        {
            mListener = listener
        }
    }

    fun getTopRatedMovies(listener : MovieDataListener?)
    {
        setListener(listener)
        mWorkHandler.post()
        {
            val movies = mLocalData?.mangeMoviesData()?.getAllData()
            if(movies?.size == 0)
            {
                mMainHandler.post { mServerData.getTopMovies() }
            }
            else
            {
                mMainHandler.post { showLocalData(movies as Any) }
            }
        }
    }

    fun updateLocalMoviesData(movies : List<Movie?>)
    {
        mWorkHandler.post()
        {
            mLocalData?.mangeMoviesData()?.deleteAll()
            for(movie in movies)
            {
                if(movie != null) {
                    mLocalData?.mangeMoviesData()?.insert(movie)
                }
            }
            mMainHandler.post { mListener?.showLocalData(movies) }
        }
    }

    fun loadCast(movieId: Int, listener : MovieDataListener?)
    {
        setListener(listener)
        mServerData.getMovieCast(movieId)
    }

    companion object {
        private var mInstance : MoviesDataManager? = null

        fun getInstance(context: Context) : MoviesDataManager
        {
            if(mInstance == null)
            {
                mInstance = MoviesDataManager()
                mInstance!!.init(context)
            }

            return mInstance!!
        }
    }
}