package graeme.hosford.eventmanager.presentation.attendees.detail.view

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.detail.ATTENDEE_ID_ARG
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailPresenter
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailView
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDetailAdapter
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDetailPresenterBridge
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDetailViewHolder
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDialogOptionsPresenterBridge
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.company.detail.MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.event.detail.ATTENDEES_ARG
import graeme.hosford.eventmanager.presentation.event.detail.EVENT_ID_ARG
import javax.inject.Inject

class AttendeeListFragment :
    BaseRecyclerViewFragment<AttendeesUiModel, AttendeeDetailViewHolder, AttendeeDetailAdapter>(),
    AttendeesDetailView, AttendeeDialogOptionsPresenterBridge {

    @Inject
    lateinit var presenter: AttendeesDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onResume() {
        super.onResume()

        val emails = arguments?.getStringArrayList(ATTENDEES_ARG)!!
        presenter.showAttendees(emails)
    }

    override fun showAttendeeOptions(userId: String, eventId: String) {
        val optionsDialog = AttendeeDetailOptionsDialog()
        optionsDialog.listener = this
        optionsDialog.arguments = bundleOf(ATTENDEE_ID_ARG to userId, EVENT_ID_ARG to eventId)
        optionsDialog.show(childFragmentManager, "")
    }

    override fun onViewProfileClick(userId: String) {
        findNavController().navigate(R.id.nav_profile_detail, bundleOf(MEMBER_ID_ARG to userId))
    }

    override fun onAddEventDetailClick(userId: String, eventId: String) {

    }

    override fun recyclerViewAdapter(): AttendeeDetailAdapter {
        return AttendeeDetailAdapter(
            object : AttendeeDetailPresenterBridge {
                override fun onAttendeeClick(userId: String) {
                    presenter.onAttendeeClick(userId, arguments?.getString(EVENT_ID_ARG)!!)
                }
            }
        )
    }

    override fun doOnSwipeRefresh() {
        /* Nothing for now */
    }
}