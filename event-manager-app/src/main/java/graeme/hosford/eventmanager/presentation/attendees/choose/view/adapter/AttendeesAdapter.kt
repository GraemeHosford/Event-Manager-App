package graeme.hosford.eventmanager.presentation.attendees.choose.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.storage.FirebaseStorage
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.AttendeeItemLayoutBinding
import graeme.hosford.eventmanager.presentation.GlideApp
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.common.view.ViewExtensions.isVisible
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder

class AttendeesAdapter(private val clickListener: AttendeeClickListener) :
    BaseAdapter<AttendeesUiModel, AttendeesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendeesViewHolder {
        return AttendeesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.attendee_item_layout,
                parent,
                false
            ),
            clickListener
        )
    }
}

class AttendeesViewHolder(itemView: View, private val clickListener: AttendeeClickListener) :
    BaseViewHolder<AttendeesUiModel>(itemView) {

    override fun bind(model: AttendeesUiModel) {
        val binding = AttendeeItemLayoutBinding.bind(itemView)

        binding.attendeeItemLayoutIncludedName.memberNameTextView.text = model.displayName

        val imageRef = FirebaseStorage.getInstance().getReference(model.imageUrl)

        GlideApp.with(itemView)
            .load(imageRef)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .into(binding.attendeeItemLayoutIncludedName.personItemLayoutImage)

        itemView.setOnClickListener {
            if (binding.attendeeCheckmark.isVisible()) {
                binding.attendeeCheckmark.visibility = View.INVISIBLE
            } else {
                binding.attendeeCheckmark.visibility = View.VISIBLE
            }

            clickListener.onAttendeeClick(model.id)
        }
    }
}