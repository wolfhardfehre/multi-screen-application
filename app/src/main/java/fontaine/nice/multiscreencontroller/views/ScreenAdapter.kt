package fontaine.nice.multiscreencontroller.views

import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.models.Screen

internal class ScreenAdapter(private val fragment: ScreenFragment,
                             private val screens: List<Screen>) : RecyclerView.Adapter<ScreenAdapter.ScreenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_screen_item, parent, false)
        return ScreenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        val screen = screens[position]
        holder.name.text = "${screen.number}"
        holder.screen = screen
    }

    override fun getItemCount() = screens.size - 1

    inner class ScreenViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var name: TextView = view.findViewById(R.id.screen_number)
        var card: CardView = view.findViewById(R.id.card_view)
        lateinit var screen: Screen

        init {
            card.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            fragment.onToggleScreen(screen)
        }
    }
}
