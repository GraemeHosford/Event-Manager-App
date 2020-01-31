package graeme.hosford.eventmanager.presentation.company.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment

class CompanyDetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }
}