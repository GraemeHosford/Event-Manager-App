package graeme.hosford.eventmanager.presentation.usereventdetail.detail.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.company.detail.DEFAULT_MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.company.detail.MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.UserEventDetailPresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.UserEventDetailView
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.model.UserEventDetailListUiModel
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.view.adapter.UserEventDetailListAdapter
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.view.adapter.UserEventDetailListViewHolder
import javax.inject.Inject

class UserEventDetailsListFragment :
    BaseRecyclerViewFragment<UserEventDetailListUiModel, UserEventDetailListViewHolder, UserEventDetailListAdapter>(),
    UserEventDetailView {

    @Inject
    lateinit var presenter: UserEventDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getUserEventDetails(arguments?.getString(MEMBER_ID_ARG) ?: DEFAULT_MEMBER_ID_ARG)
    }

    override fun recyclerViewAdapter(): UserEventDetailListAdapter {
        return UserEventDetailListAdapter()
    }

    override fun doOnSwipeRefresh() {
        /* Nothing for now */
    }
}