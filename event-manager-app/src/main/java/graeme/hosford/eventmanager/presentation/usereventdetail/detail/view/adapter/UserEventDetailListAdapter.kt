package graeme.hosford.eventmanager.presentation.usereventdetail.detail.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.UserEventDetailItemLayoutBinding
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.model.UserEventDetailListUiModel

class UserEventDetailListAdapter :
    BaseAdapter<UserEventDetailListUiModel, UserEventDetailListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserEventDetailListViewHolder {
        return UserEventDetailListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_event_detail_item_layout, parent, false)
        )
    }
}


class UserEventDetailListViewHolder(itemView: View) :
    BaseViewHolder<UserEventDetailListUiModel>(itemView) {
    override fun bind(model: UserEventDetailListUiModel) {
        val binding = UserEventDetailItemLayoutBinding.bind(itemView)

        binding.userEventDetailNameTextView.text = model.eventName
        binding.userEventDetailSubjectTextView.text = model.subject
        binding.userEventDetailDetailTextView.text = model.detail
    }
}