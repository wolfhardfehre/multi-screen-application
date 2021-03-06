package fontaine.nice.multiscreencontroller.views

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.models.Screen
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import fontaine.nice.multiscreencontroller.Controller
import fontaine.nice.multiscreencontroller.dagger.Dagger
import fontaine.nice.multiscreencontroller.network.ScreenApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_screen.view.*
import javax.inject.Inject


class ScreenFragment : Fragment() {
    private val screenApi by lazy { ScreenApi.create() }
    private var disposable: Disposable? = null
    private lateinit var adapter: ScreenAdapter
    @Inject internal lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dagger.getComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_screen, container, false)
        adapter = ScreenAdapter(this, controller.screens)
        val layoutManager = GridLayoutManager(activity, 2)
        view.recyclerView.layoutManager = layoutManager
        view.recyclerView.itemAnimator = DefaultItemAnimator()
        view.recyclerView.adapter = adapter
        view.recyclerView.isNestedScrollingEnabled = false
        return view
    }

    fun onToggleScreen(screen: Screen) {
        disposable = screenApi.toggleScreen(screen.nextState, screen.number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onChange(screen, result) },
                { error -> show(error.message.toString()) }
            )
    }

    private fun onChange(screen: Screen, message: String) {
        if (screen.nextState == 0) screen.nextState = 1
        else screen.nextState = 0
        show(message)
        adapter.notifyDataSetChanged()
    }

    private fun show(message: String) {
        Snackbar.make(view!!.rootView, message, Snackbar.LENGTH_SHORT).show()
    }
}
