package graeme.hosford.eventmanager.presentation.event.detail.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import javax.inject.Inject

class EventDetailFragment : BaseFragment() {

    @Inject
    lateinit var presenter: EventDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}