package graeme.hosford.eventmanager.presentation.event.list.owned.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.owned.EventListOwnedPresenter
import graeme.hosford.eventmanager.presentation.event.list.owned.view.adapter.EventListOwnedAdapter
import graeme.hosford.eventmanager.presentation.event.list.owned.view.adapter.EventListOwnedPresenterBridge
import graeme.hosford.eventmanager.presentation.event.list.owned.view.adapter.EventListOwnedViewHolder
import javax.inject.Inject

class EventListOwnedFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListOwnedViewHolder, EventListOwnedAdapter>(),
    EventListView {

    @Inject
    lateinit var presenter: EventListOwnedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            presenter.onFabClick()
        }
    }

    override fun addRecyclerViewDecorations(
        recyclerViewContext: Context,
        layoutOrientation: Int
    ): List<RecyclerView.ItemDecoration> = emptyList()

    override fun doOnSwipeRefresh() {
        presenter.getOwnedEvents()
    }

    override fun showEventDetail(eventId: String) {
        val args = Bundle()
        args.putString(EventListView.ARG_EVENT_ID, eventId)
        findNavController().navigate(R.id.nav_event_detail, args)
    }

    override fun startCreateNewEvent() {
        findNavController().navigate(R.id.nav_create_event)
    }

    override fun showFab(): Boolean = true

    override fun recyclerViewAdapter(): EventListOwnedAdapter {
        return EventListOwnedAdapter(
            object : EventListOwnedPresenterBridge {
                override fun onEventItemClick(eventId: String) {
                    presenter.onEventItemClick(eventId)
                }
            }
        )
    }
}