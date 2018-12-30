package fontaine.nice.multiscreencontroller.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.models.Screen

internal class ScreenAdapter(private val screens: List<Screen>) :
    RecyclerView.Adapter<ScreenAdapter.ScreenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_screen_item, parent, false)
        return ScreenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        val screen = screens[position]
        holder.name.text = "${screen.number}"
    }

    override fun getItemCount() = screens.size - 1

    inner class ScreenViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView =  view.findViewById(R.id.screen_number)
    }
}
