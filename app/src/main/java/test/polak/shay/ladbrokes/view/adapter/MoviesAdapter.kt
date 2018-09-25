package test.polak.shay.ladbrokes.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_table_card.*
import test.polak.shay.ladbrokes.R
import test.polak.shay.ladbrokes.model.Movie

class MoviesAdapter(private var mDisplay : MutableList<Movie> = mutableListOf()) : RecyclerView.Adapter<MoviesAdapter.Holder>(), View.OnClickListener {

    private var mData : List<Movie>? = listOf()
    var mListener : OnMovieItemClick? = null

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    interface OnMovieItemClick
    {
        fun onClick(data : Movie)
    }

    override fun onClick(p0: View?) {
        if(mListener != null && p0 != null)
        {
            mListener?.onClick(mDisplay.get(p0!!.tag as Int))
        }
    }

    fun search(word : String)
    {
        mDisplay.clear()
        for(movie in mData!!)
        {
         if(movie.title.contains(word))
         {
             mDisplay.add(movie)
         }
        }
        notifyDataSetChanged()
    }

    fun displayAllData()
    {
        mDisplay = mData!!.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = mDisplay.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
    {
        val view = parent.inflate(R.layout.movie_table_card)
        view.setOnClickListener(this)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(mDisplay[position], position)

    fun updateMoviesList(toShow: List<Movie>) {
        mData = toShow.toList()
        mDisplay = toShow.toMutableList()
        notifyDataSetChanged()
    }

    class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(data: Movie, position : Int)
        {
            val title_ = data.title
            val release_ = data.release_date
            var overview_length = data.overview.length
            if(overview_length > 50)
            {
                overview_length = 50
            }
            val overview_ = data.overview.subSequence(0,overview_length)
            val rate_ = data.popularity.toString()
            containerView.tag = position

            Glide.with(containerView.context).load("https://image.tmdb.org/t/p/w500" + data.poster_path).into(poster_image)
            title.text = "Title = $title_"
            year.text = "Release date = $release_"
            rate.text = "Rate = $rate_"
            overview.text = "Overview = $overview_"
        }
    }
}