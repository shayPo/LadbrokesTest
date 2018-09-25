package test.polak.shay.ladbrokes.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cast_view.view.*
import kotlinx.android.synthetic.main.movie_details_activity.*
import test.polak.shay.ladbrokes.R
import test.polak.shay.ladbrokes.model.CastMember
import test.polak.shay.ladbrokes.model.Movie
import test.polak.shay.ladbrokes.presenter.MovieDetailsPresenter

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsPresenter.CastListener {

    private val mPresenter: MovieDetailsPresenter = MovieDetailsPresenter()
    private var mData: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_activity)
        mData = intent.getParcelableExtra<Movie>("movieData")

        val title_ = mData!!.title
        val release_ = mData!!.release_date
        val overview_ = mData!!.overview
        val rate_ = mData!!.popularity.toString()

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + mData!!.poster_path).into(poster_image)
        movie_title.text = "Title = $title_"
        year.text = "Release date = $release_"
        rate.text = "Rate = $rate_"
        overview.text = "Overview = $overview_"

        mPresenter.init(this, this, mData!!.id)
    }

    override fun updateView(castList: List<CastMember?>) {
        val Inflater = LayoutInflater.from(this)
        for (cast in castList)
        {
            val view = Inflater.inflate(R.layout.cast_view, root, false)
            view.apply{
                name.text = cast?.name
                if(cast?.profile_path!!.isNotEmpty()) {
                    Glide.with(this).load("https://image.tmdb.org/t/p/w500" + cast?.profile_path).into(actor_image)
                }
            }
            root.addView(view)
        }
    }
}