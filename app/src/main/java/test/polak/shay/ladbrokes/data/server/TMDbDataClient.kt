package test.polak.shay.ladbrokes.data.server

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.json.JSONObject
import test.polak.shay.ladbrokes.data.MovieDataListener

class TMDbDataClient(private val mListener: MovieDataListener) : Response.Listener<String>, Response.ErrorListener {

    private val API_KET = "4e0be2c22f7268edffde97481d49064a"
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val TOP_MOVIES = "${BASE_URL}top_rated?api_key=$API_KET&language=en-US&page=1"
    private val CAST = "/credits?api_key=$API_KET"
    private var mQueue: RequestQueue?

    init {
        mQueue = null
    }

    fun init(context: Context) {
        if(mQueue == null) {
            mQueue = Volley.newRequestQueue(context)
        }
    }

    override fun onResponse(response: String?) {
        if(response != null)
        {
          mListener.showServerData(JSONObject(response))
        }

    }

    override fun onErrorResponse(error: VolleyError?) {
        mListener.onFailure()
    }

    fun getTopMovies() {
        val stringRequest = StringRequest(Request.Method.GET, TOP_MOVIES, this, this)
        mQueue?.add(stringRequest)
    }

    fun getMovieCast(movieId: Int)
    {
        val stringRequest = StringRequest(Request.Method.GET, BASE_URL + movieId + CAST, this, this)
        mQueue?.add(stringRequest)
    }
}