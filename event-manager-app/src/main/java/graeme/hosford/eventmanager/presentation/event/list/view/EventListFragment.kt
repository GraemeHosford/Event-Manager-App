package graeme.hosford.eventmanager.presentation.event.list.view

import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListAdapter
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListItemViewHolder

class EventListFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListItemViewHolder, EventListAdapter>(),
    EventListView {

    override fun recyclerViewAdapter(): EventListAdapter {
        return EventListAdapter()
    }
}