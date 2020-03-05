package graeme.hosford.eventmanager.presentation.company.detail.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.storage.FirebaseStorage
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.PersonItemLayoutBinding
import graeme.hosford.eventmanager.presentation.GlideApp
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

    override fun bind(model: CompanyMemberUiModel) {
        val binding = PersonItemLayoutBinding.bind(itemView)

        binding.memberNameTextView.text = model.personName
        
        val storageRef = FirebaseStorage.getInstance().getReference(model.personImageUrl)

        GlideApp.with(itemView)
            .load(storageRef)
            .error(R.drawable.ic_person)
            .placeholder(R.drawable.ic_person)
            .into(binding.personItemLayoutImage)
    }
}