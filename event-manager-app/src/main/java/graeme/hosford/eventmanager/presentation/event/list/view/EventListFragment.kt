package graeme.hosford.eventmanager.presentation.event.list.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.presentation.EventListPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListAdapter
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListItemViewHolder
import javax.inject.Inject

class EventListFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListItemViewHolder, EventListAdapter>(),
    EventListView {

    @Inject
    lateinit var presenter: EventListPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun recyclerViewAdapter(): EventListAdapter {
        return EventListAdapter {
            presenter.onEventItemClick()
        }
    }

    override fun showEventDetail() {
        /* Nothing for now */
    }
}