package test.polak.shay.ladbrokes.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.Intent
import android.util.Log
import org.json.JSONObject
import test.polak.shay.ladbrokes.data.MovieDataListener
import test.polak.shay.ladbrokes.data.MoviesDataManager
import test.polak.shay.ladbrokes.view.MoviesListActivity

class LoadingPresenter() : MoviesDataPresenter(),LifecycleObserver, MovieDataListener
{
    fun init(context: Context)
    {
        mContext = context
        MoviesDataManager.getInstance(context).getTopRatedMovies(this)
    }

    override fun onFailure()
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLocalData(data : Any)
    {
        val intent = Intent(mContext!!, MoviesListActivity::class.java)
        mContext!!.startActivity(intent)
    }

    override fun showServerData(data: JSONObject)
    {
        updateLocalMoviesData(data)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy()
    {
        Log.d("what_if","on destroy load activity")
    }
}