package graeme.hosford.eventmanager.presentation.event.list.invited.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.invited.EventListInvitedPresenter
import graeme.hosford.eventmanager.presentation.event.list.invited.view.adapter.EventListInvitedAdapter
import graeme.hosford.eventmanager.presentation.event.list.invited.view.adapter.EventListInvitedItemViewHolder
import graeme.hosford.eventmanager.presentation.event.list.invited.view.adapter.EventListInvitedPresenterBridge
import javax.inject.Inject

class EventListInvitedFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListInvitedItemViewHolder, EventListInvitedAdapter>(),
    EventListView {

    @Inject
    lateinit var presenter: EventListInvitedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            presenter.onFabClick()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun showEventDetail(eventId: String) {
        val args = Bundle()
        args.putString(EventListView.ARG_EVENT_ID, eventId)
        findNavController().navigate(R.id.nav_event_detail, args)
    }

    override fun startCreateNewEvent() {
        presenter.onFabClick()
    }

    override fun showFab(): Boolean = true

    override fun recyclerViewAdapter(): EventListInvitedAdapter {
        return EventListInvitedAdapter(
            object :
                EventListInvitedPresenterBridge {
                override fun onGoingResponseClick(eventId: String) {
                    presenter.onGoingResponseClick(eventId)
                }

                override fun onNotGoingResponseClick(eventId: String) {
                    presenter.onNotGoingResponseClick(eventId)
                }

                override fun onEventListItemClick(eventId: String) {
                    presenter.onEventItemClick(eventId)
                }

                override fun getCurrentUserId(): String {
                    return presenter.getCurrentUserId()
                }
            })
    }
}