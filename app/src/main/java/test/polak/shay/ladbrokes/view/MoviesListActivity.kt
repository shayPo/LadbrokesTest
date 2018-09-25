package test.polak.shay.ladbrokes.view

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dinuscxj.refresh.RecyclerRefreshLayout
import kotlinx.android.synthetic.main.movies_list_activity.*
import test.polak.shay.ladbrokes.R
import test.polak.shay.ladbrokes.model.Movie
import test.polak.shay.ladbrokes.presenter.MoviesListPresenter
import test.polak.shay.ladbrokes.view.adapter.MoviesAdapter

class MoviesListActivity : AppCompatActivity(),LifecycleOwner, RecyclerRefreshLayout.OnRefreshListener, MoviesAdapter.OnMovieItemClick {

    private val mPresenter : MoviesListPresenter = MoviesListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_list_activity)
        this.lifecycle.addObserver(mPresenter)
        val adapter = MoviesAdapter()
        adapter.mListener = this
        movies_list.layoutManager = LinearLayoutManager(this)
        movies_list.adapter = adapter
        mPresenter.init(this, adapter)
        refresh_layout.setOnRefreshListener(this)
    }

    override fun onBackPressed() {
        if(mPresenter.mIsSearchOnScreen)
        {
            mPresenter.displayAllMoviesList()
        }
        else
        {
            super.onBackPressed()
        }
    }

    override fun onClick(data: Movie)
    {
       mPresenter.displayFullDataScreen(data)
    }

    override fun onRefresh()
    {
        mPresenter.refreshListData()
        refresh_layout.setRefreshing(false)
    }

    fun startSearch(view : View)
    {
        mPresenter.searchInMoviesList(search_word.text.toString())
        search_word.text.clear()
    }
}