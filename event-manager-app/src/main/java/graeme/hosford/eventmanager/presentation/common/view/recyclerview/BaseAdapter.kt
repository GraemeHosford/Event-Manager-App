package graeme.hosford.eventmanager.presentation.common.view.recyclerview

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Model, ViewHolder : BaseViewHolder<Model>> :
    RecyclerView.Adapter<ViewHolder>() {

    private lateinit var items: ArrayList<Model>

    fun setItems(models: List<Model>) {
        items.clear()
        items.addAll(models)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}