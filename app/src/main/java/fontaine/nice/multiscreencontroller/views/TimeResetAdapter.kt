package fontaine.nice.multiscreencontroller.views

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.models.Screen

internal class TimeResetAdapter(private val fragment: TimeResetFragment,
                                private val screens: List<Screen>) : RecyclerView.Adapter<TimeResetAdapter.TimeResetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeResetViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_screen_item, parent, false)
        return TimeResetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimeResetViewHolder, position: Int) {
        val screen = screens[position]
        holder.name.text = "${screen.number}"
        holder.screen = screen
    }

    override fun getItemCount() = screens.size - 1

    inner class TimeResetViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var name: TextView = view.findViewById(R.id.screen_number)
        var card: CardView = view.findViewById(R.id.card_view)
        lateinit var screen: Screen

        init {
            card.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            fragment.onResetTimeOf(screen)
        }
    }
}
