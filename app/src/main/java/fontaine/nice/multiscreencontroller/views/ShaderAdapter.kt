package fontaine.nice.multiscreencontroller.views

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fontaine.nice.multiscreencontroller.R
import fontaine.nice.multiscreencontroller.models.Shader

internal class ShaderAdapter(private val fragment: ShaderFragment,
                             private val shader: List<Shader>)
    : RecyclerView.Adapter<ShaderAdapter.ShaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShaderViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_screen_item, parent, false)
        return ShaderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShaderViewHolder, position: Int) {
        val single = shader[position]
        holder.name.text = single.name
        holder.shader = single
    }

    override fun getItemCount() = shader.size - 1

    inner class ShaderViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var name: TextView = view.findViewById(R.id.screen_number)
        var card: CardView = view.findViewById(R.id.card_view)
        lateinit var shader: Shader

        init {
            card.setOnClickListener(this)
            name.textSize = 16F
        }

        override fun onClick(v: View?) {
            fragment.onShaderChangeTo(shader)
        }
    }
}
