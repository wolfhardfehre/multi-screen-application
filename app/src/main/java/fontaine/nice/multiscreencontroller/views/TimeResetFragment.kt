package fontaine.nice.multiscreencontroller.views

import android.app.Application
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fontaine.nice.multiscreencontroller.Controller
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.dagger.Dagger
import fontaine.nice.multiscreencontroller.models.Screen
import fontaine.nice.multiscreencontroller.network.ScreenApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_screen.view.*
import javax.inject.Inject

class TimeResetFragment : Fragment() {
    private val screenApi by lazy { ScreenApi.create() }
    private var disposable: Disposable? = null
    private lateinit var adapter: TimeResetAdapter
    @Inject internal lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dagger.getComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time_reset, container, false)
        adapter = TimeResetAdapter(this, controller.screens)
        val layoutManager = GridLayoutManager(activity, 2)
        view.recyclerView.layoutManager = layoutManager
        view.recyclerView.itemAnimator = DefaultItemAnimator()
        view.recyclerView.adapter = adapter
        view.recyclerView.isNestedScrollingEnabled = false
        return view
    }

    fun onResetTimeOf(screen: Screen) {
        disposable = screenApi.resetTime(screen.number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> show(result) },
                { error -> show(error.message.toString()) }
            )
    }

    private fun show(message: String) {
        Snackbar.make(view!!.rootView, message, Snackbar.LENGTH_SHORT).show()
    }
}
