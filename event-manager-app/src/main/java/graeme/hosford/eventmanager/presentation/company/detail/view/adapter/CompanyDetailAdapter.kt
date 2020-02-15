package graeme.hosford.eventmanager.presentation.company.detail.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberUiModel

class CompanyDetailAdapter : BaseAdapter<CompanyMemberUiModel, CompanyDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyDetailViewHolder {
        return CompanyDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.person_item_layout,
                parent,
                false
            )
        )
    }
}

class CompanyDetailViewHolder(itemView: View) : BaseViewHolder<CompanyMemberUiModel>(itemView) {

    @BindView(R.id.member_name_text_view)
    lateinit var memberName: TextView

    override fun bind(model: CompanyMemberUiModel) {
        ButterKnife.bind(this, itemView)

        memberName.text = model.personName
    }
}