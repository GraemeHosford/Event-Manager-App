package graeme.hosford.eventmanager.presentation.event.list.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.event.list.EventListPresenter
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListAdapter
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListItemViewHolder
import javax.inject.Inject

class EventListFragment :
    BaseRecyclerViewFragment<EventListItemUiModel, EventListItemViewHolder, EventListAdapter>(),
    EventListView {

    @Inject
    lateinit var presenter: EventListPresenter

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

    override fun recyclerViewAdapter(): EventListAdapter {
        return EventListAdapter(View.OnClickListener { presenter.onEventItemClick() })
    }

    override fun showFab(): Boolean = true

    override fun showEventDetail() {
        /* Nothing for now */
        showLongToast("Event Details Not Implemented Yet")
    }
}