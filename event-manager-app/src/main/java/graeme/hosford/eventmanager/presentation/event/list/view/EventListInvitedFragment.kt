package graeme.hosford.eventmanager.presentation.event.list.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.EventListPresenter
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListAdapter
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListItemPresenterBridge
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListItemViewHolder
import javax.inject.Inject

class EventListInvitedFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListItemViewHolder, EventListAdapter>(),
    EventListView {

    @Inject
    lateinit var presenter: EventListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun showEventDetail(eventId: String) {
        presenter.onEventItemClick(eventId)
    }

    override fun startCreateNewEvent() {
        presenter.onFabClick()
    }

    override fun recyclerViewAdapter(): EventListAdapter {
        return EventListAdapter(object : EventListItemPresenterBridge {
            override fun onGoingResponseClick(eventId: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNotGoingResponseClick(eventId: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onEventListItemClick(eventId: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getCurrentUserId(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}