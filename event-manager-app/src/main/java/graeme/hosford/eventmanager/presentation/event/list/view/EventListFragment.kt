package graeme.hosford.eventmanager.presentation.event.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import graeme.hosford.eventmanager.databinding.FragmentEventListLayoutBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.list.view.adapter.EventListViewPagerAdapter

class EventListFragment :
    BaseFragment() {

    private var binding: FragmentEventListLayoutBinding? = null
    private val safeBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListLayoutBinding.inflate(inflater, container, false)
        return safeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = safeBinding.eventTabs
        val viewPager = safeBinding.eventViewPager

        viewPager.adapter = EventListViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = "$pos"
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}