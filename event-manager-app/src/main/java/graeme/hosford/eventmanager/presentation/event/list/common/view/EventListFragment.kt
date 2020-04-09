package graeme.hosford.eventmanager.presentation.event.list.common.view

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.FragmentEventListLayoutBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.list.common.view.adapter.EventListViewPagerAdapter

class EventListFragment :
    BaseFragment() {

    private var binding: FragmentEventListLayoutBinding? = null
    private val safeBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListLayoutBinding.inflate(inflater, container, false)
        return safeBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.event_list_options_menu, menu)
        DrawableCompat.wrap(menu[0].icon).mutate()
            .setTint(ContextCompat.getColor(requireContext(), android.R.color.white))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.calendar_option -> {
                findNavController().navigate(R.id.nav_calendar)
                true
            }
            else -> false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = safeBinding.eventTabs
        val viewPager = safeBinding.eventViewPager

        viewPager.adapter =
            EventListViewPagerAdapter(
                this
            )

        val tabNames =
            arrayOf(R.string.attending_text, R.string.invited_text, R.string.my_events_text)

        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = getString(tabNames[pos])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}