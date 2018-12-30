package fontaine.nice.multiscreencontroller.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fontaine.nice.multiscreencontroller.R
import android.support.v7.widget.RecyclerView
import fontaine.nice.multiscreencontroller.models.Screen
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.TypedValue

class ScreenFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: ScreenAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_screen, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        val screens = (1..11).map { number -> Screen(number) }.toList()
        adapter = ScreenAdapter(screens)
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView!!.layoutManager = layoutManager
        //recyclerView!!.addItemDecoration(GridSpacingItemDecoration(10, dpToPx(8), true))
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter
        recyclerView!!.isNestedScrollingEnabled = false
        return view
    }

    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }
}
