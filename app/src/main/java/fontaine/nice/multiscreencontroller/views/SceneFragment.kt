package fontaine.nice.multiscreencontroller.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.network.ScreenApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_scene.view.*

class SceneFragment : Fragment() {
    private val screenApi by lazy { ScreenApi.create() }
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scene, container, false)
        view.buttonLocalScreen.setOnClickListener { onScreen(0) }
        view.buttonGlobalScreen.setOnClickListener { onScreen(1) }
        view.buttonRandomScreen.setOnClickListener { onScreen(2) }
        view.buttonRandomizedTime.setOnClickListener { onTimeReset(0) }
        view.buttonSynchronizedTime.setOnClickListener { onTimeReset(1) }
        view.buttonManualMode.setOnClickListener { onPlayMode(0) }
        view.buttonPresetMode.setOnClickListener { onPlayMode(1) }
        return view
    }

    private fun onScreen(mode: Int) {
        disposable = screenApi.screenSpan(mode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> show(result) },
                { error -> show(error.message.toString()) }
            )
    }

    private fun onTimeReset(synced: Int) {
        disposable = screenApi.resetTimes(synced)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> show(result) },
                { error -> show(error.message.toString()) }
            )
    }

    private fun onPlayMode(mode: Int) {
        disposable = screenApi.playMode(mode)
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
