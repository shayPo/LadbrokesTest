package test.polak.shay.ladbrokes.presenter

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import com.beust.klaxon.Klaxon
import org.json.JSONObject
import test.polak.shay.ladbrokes.data.MovieDataListener
import test.polak.shay.ladbrokes.data.MoviesDataManager
import test.polak.shay.ladbrokes.model.CastMember

class MovieDetailsPresenter : MovieDataListener, LifecycleObserver
{
    private var mListener : CastListener? = null

    fun init(context: Context, listener: CastListener, id: Int)
    {
        mListener = listener
        MoviesDataManager.getInstance(context).loadCast(id, this)
    }

    override fun onFailure()
    {

    }

    override fun showServerData(data: JSONObject)
    {
        if(mListener != null) {
            val castJsonArray = data.getJSONArray("cast")
            val castArray = MutableList(castJsonArray.length(), { index -> Klaxon().parse<CastMember>(castJsonArray[index].toString()) })
            mListener!!.updateView(castArray.toList())
        }
    }

    override fun showLocalData(data: Any)
    {

    }

    interface CastListener
    {
        fun updateView(castList : List<CastMember?>)
    }

}