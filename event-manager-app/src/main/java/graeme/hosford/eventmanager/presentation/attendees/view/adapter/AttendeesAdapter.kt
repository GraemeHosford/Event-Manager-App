package graeme.hosford.eventmanager.presentation.attendees.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder

class AttendeesAdapter : BaseAdapter<AttendeesUiModel, AttendeesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendeesViewHolder {
        return AttendeesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.person_item_layout,
                parent,
                false
            )
        )
    }
}

class AttendeesViewHolder(itemView: View) : BaseViewHolder<AttendeesUiModel>(itemView) {

    @BindView(R.id.member_name_text_view)
    lateinit var personName: TextView

    override fun bind(model: AttendeesUiModel) {
        ButterKnife.bind(this, itemView)

        personName.text = model.name
    }
}