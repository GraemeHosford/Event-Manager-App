package graeme.hosford.eventmanager.presentation.event.list.common.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import graeme.hosford.eventmanager.presentation.event.list.attending.view.EventListAttendingFragment
import graeme.hosford.eventmanager.presentation.event.list.invited.view.EventListInvitedFragment
import graeme.hosford.eventmanager.presentation.event.list.owned.view.EventListOwnedFragment

private const val NUM_TABS = 3

class EventListViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EventListAttendingFragment()
            1 -> EventListInvitedFragment()
            2 -> EventListOwnedFragment()
            else -> throw IllegalStateException("Should never get here")
        }
    }
}