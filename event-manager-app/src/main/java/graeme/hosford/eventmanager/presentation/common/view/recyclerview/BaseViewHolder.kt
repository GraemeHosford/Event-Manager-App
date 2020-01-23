package graeme.hosford.eventmanager.presentation.common.view.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Model>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(model: Model)

}