package graeme.hosford.eventmanager.presentation.attendees.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.attendees.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.AttendeesView
import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeesAdapter
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeesViewHolder
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import javax.inject.Inject

class AttendeesFragment :
    BaseRecyclerViewFragment<AttendeesUiModel, AttendeesViewHolder, AttendeesAdapter>(),
    AttendeesView {

    @Inject
    lateinit var presenter: AttendeesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun recyclerViewAdapter(): AttendeesAdapter = AttendeesAdapter()
}