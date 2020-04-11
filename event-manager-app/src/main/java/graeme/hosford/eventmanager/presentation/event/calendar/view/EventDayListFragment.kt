package graeme.hosford.eventmanager.presentation.event.calendar.view

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.calendar.EventDayListPresenter
import graeme.hosford.eventmanager.presentation.event.calendar.view.adapter.EventDayListAdapter
import graeme.hosford.eventmanager.presentation.event.calendar.view.adapter.EventDayListPresenterBridge
import graeme.hosford.eventmanager.presentation.event.calendar.view.adapter.EventDayListViewHolder
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import java.util.*
import javax.inject.Inject

class EventDayListFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventDayListViewHolder, EventDayListAdapter>(),
    EventListView, EventCalendarFragment.EventCalendarListInteractionBridge {

    @Inject
    lateinit var presenter: EventDayListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)

        (requireParentFragment() as EventCalendarFragment).calendarBridge = this

        /* Show current day by default */
        val calendar = Calendar.getInstance()
        onDateSelected(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONDAY),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        presenter.onDateSelected(year, month, dayOfMonth)
    }

    override fun addRecyclerViewDecorations(
        recyclerViewContext: Context,
        layoutOrientation: Int
    ): List<RecyclerView.ItemDecoration> = emptyList()

    override fun recyclerViewAdapter(): EventDayListAdapter {
        return EventDayListAdapter(
            object : EventDayListPresenterBridge {
                override fun onEventItemClick(eventId: String) {
                    presenter.onEventItemClick(eventId)
                }
            }
        )
    }

    override fun doOnSwipeRefresh() {

    }

    override fun showEventDetail(eventId: String) {
        val args = Bundle()
        args.putString(EventListView.ARG_EVENT_ID, eventId)
        findNavController().navigate(R.id.nav_event_detail, args)
    }

    override fun startCreateNewEvent() {
        findNavController().navigate(R.id.nav_create_event)
    }
}