package test.polak.shay.ladbrokes.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import test.polak.shay.ladbrokes.R
import test.polak.shay.ladbrokes.presenter.LoadingPresenter

class LoadDataActivity : AppCompatActivity()
{
    private val mPresenter : LoadingPresenter = LoadingPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_activity)
        this.lifecycle.addObserver(mPresenter)
        mPresenter.init(this)
    }

}