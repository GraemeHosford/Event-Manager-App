package graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.storage.FirebaseStorage
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.PersonItemLayoutBinding
import graeme.hosford.eventmanager.presentation.GlideApp
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder

class AttendeeDetailAdapter(
    private val attendeeDetailPresenterBridge: AttendeeDetailPresenterBridge
) : BaseAdapter<AttendeesUiModel, AttendeeDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendeeDetailViewHolder {
        return AttendeeDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.person_item_layout,
                parent,
                false
            ),
            attendeeDetailPresenterBridge
        )
    }
}

class AttendeeDetailViewHolder(
    itemView: View,
    private val presenterBridge: AttendeeDetailPresenterBridge
) : BaseViewHolder<AttendeesUiModel>(itemView) {
    override fun bind(model: AttendeesUiModel) {
        val binding = PersonItemLayoutBinding.bind(itemView)

        binding.memberNameTextView.text = model.displayName

        val imageRef = FirebaseStorage.getInstance().getReference(model.imageUrl)

        GlideApp.with(itemView)
            .load(imageRef)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .into(binding.personItemLayoutImage)

        binding.root.setOnClickListener {
            presenterBridge.onAttendeeClick(model.id)
        }
    }
}