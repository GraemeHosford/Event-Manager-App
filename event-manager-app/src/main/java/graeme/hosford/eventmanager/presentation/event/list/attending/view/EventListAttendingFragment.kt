package graeme.hosford.eventmanager.presentation.event.list.attending.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.attending.EventListAttendingPresenter
import graeme.hosford.eventmanager.presentation.event.list.attending.view.adapter.EventListAttendingAdapter
import graeme.hosford.eventmanager.presentation.event.list.attending.view.adapter.EventListAttendingItemViewHolder
import graeme.hosford.eventmanager.presentation.event.list.attending.view.adapter.EventListAttendingPresenterBridge
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import javax.inject.Inject

class EventListAttendingFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListAttendingItemViewHolder, EventListAttendingAdapter>(),
    EventListView {

    @Inject
    lateinit var presenter: EventListAttendingPresenter

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

    override fun addRecyclerViewDecorations(
        recyclerViewContext: Context,
        layoutOrientation: Int
    ): List<RecyclerView.ItemDecoration> = emptyList()

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun startCreateNewEvent() {
        findNavController().navigate(R.id.nav_create_event)
    }

    override fun recyclerViewAdapter(): EventListAttendingAdapter {
        return EventListAttendingAdapter(
            object :
                EventListAttendingPresenterBridge {
                override fun onEventListItemClick(eventId: String) {
                    presenter.onEventItemClick(eventId)
                }

                override fun getCurrentUserId(): String {
                    return presenter.getCurrentUserId()
                }
            })
    }

    override fun showFab(): Boolean = true

    override fun showEventDetail(eventId: String) {
        val args = Bundle()
        args.putString(EventListView.ARG_EVENT_ID, eventId)
        findNavController().navigate(R.id.nav_event_detail, args)
    }

}