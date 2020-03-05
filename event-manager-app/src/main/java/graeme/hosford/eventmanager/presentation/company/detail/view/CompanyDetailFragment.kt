package graeme.hosford.eventmanager.presentation.company.detail.view

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailPresenter
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailView
import graeme.hosford.eventmanager.presentation.company.detail.MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberUiModel
import graeme.hosford.eventmanager.presentation.company.detail.view.adapter.CompanyDetailAdapter
import graeme.hosford.eventmanager.presentation.company.detail.view.adapter.CompanyDetailViewHolder
import graeme.hosford.eventmanager.presentation.company.detail.view.adapter.CompanyMemberPresenterBridge
import javax.inject.Inject

class CompanyDetailFragment :
    BaseRecyclerViewFragment<CompanyMemberUiModel, CompanyDetailViewHolder, CompanyDetailAdapter>(),
    CompanyDetailView {

    @Inject
    lateinit var presenter: CompanyDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getCompanyMembers()
    }

    override fun doOnSwipeRefresh() {
        presenter.getCompanyMembers()
    }

    override fun showMemberDetail(id: String) {
        val args = Bundle()
        args.putString(MEMBER_ID_ARG, id)
        findNavController().navigate(R.id.nav_profile_detail, args)
    }

    override fun recyclerViewAdapter(): CompanyDetailAdapter {
        return CompanyDetailAdapter(object : CompanyMemberPresenterBridge {
            override fun onCompanyMemberClick(id: String) {
                presenter.onCompanyMemberClick(id)
            }
        })
    }
}