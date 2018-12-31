package fontaine.nice.multiscreencontroller.views

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
import fontaine.nice.multiscreencontroller.models.Shader
import fontaine.nice.multiscreencontroller.network.ScreenApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_screen.view.*
import javax.inject.Inject

class ShaderFragment : Fragment() {
    private val screenApi by lazy { ScreenApi.create() }
    private var disposable: Disposable? = null
    private lateinit var adapter: ShaderAdapter
    @Inject internal lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dagger.getComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_shader, container, false)
        adapter = ShaderAdapter(this, controller.shader)
        val layoutManager = GridLayoutManager(activity, 1)
        view.recyclerView.layoutManager = layoutManager
        view.recyclerView.itemAnimator = DefaultItemAnimator()
        view.recyclerView.adapter = adapter
        view.recyclerView.isNestedScrollingEnabled = false
        return view
    }

    fun onShaderChangeTo(shader: Shader) {
        disposable = screenApi.changeShader(shader.number)
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
