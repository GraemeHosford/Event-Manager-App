package graeme.hosford.eventmanager.presentation.usereventdetail.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.usereventdetail.AddUserEventDetailPresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.AddUserEventDetailView
import javax.inject.Inject

class AddUserEventDetailFragment : BaseFragment(),
    AddUserEventDetailView {

    @Inject
    lateinit var presenter: AddUserEventDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }
}