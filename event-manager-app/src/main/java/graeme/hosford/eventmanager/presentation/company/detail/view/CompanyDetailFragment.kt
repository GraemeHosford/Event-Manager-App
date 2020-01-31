package graeme.hosford.eventmanager.presentation.company.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailView
import graeme.hosford.eventmanager.presentation.company.detail.presentation.CompanyDetailPresenterImpl
import javax.inject.Inject

class CompanyDetailFragment : BaseFragment(), CompanyDetailView {

    @Inject
    lateinit var presenter: CompanyDetailPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }
}