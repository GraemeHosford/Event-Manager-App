package graeme.hosford.eventmanager.presentation.company.detail.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailView
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberUiModel
import graeme.hosford.eventmanager.presentation.company.detail.presentation.CompanyDetailPresenterImpl
import graeme.hosford.eventmanager.presentation.company.detail.view.adapter.CompanyDetailAdapter
import graeme.hosford.eventmanager.presentation.company.detail.view.adapter.CompanyDetailViewHolder
import javax.inject.Inject

class CompanyDetailFragment :
    BaseRecyclerViewFragment<CompanyMemberUiModel, CompanyDetailViewHolder, CompanyDetailAdapter>(),
    CompanyDetailView {

    @Inject
    lateinit var presenter: CompanyDetailPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun recyclerViewAdapter(): CompanyDetailAdapter {
        return CompanyDetailAdapter()
    }
}