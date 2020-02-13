package graeme.hosford.eventmanager.presentation.event.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailView
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import javax.inject.Inject

class EventDetailFragment : BaseFragment(), EventDetailView {

    @Inject
    lateinit var presenter: EventDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)

        presenter.loadEventDetail(arguments?.getString(EventListView.ARG_EVENT_ID) ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }
}