package graeme.hosford.eventmanager.presentation.common.view.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Model>(itemView: View, itemClickListener: (View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener(itemClickListener)
    }

    abstract fun bind(model: Model)

}