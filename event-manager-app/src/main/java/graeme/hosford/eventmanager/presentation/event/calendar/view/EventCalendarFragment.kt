package graeme.hosford.eventmanager.presentation.event.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.FragmentEventCalendarLayoutBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.list.attending.view.EventListAttendingFragment

class EventCalendarFragment : BaseFragment() {

    interface EventCalendarListInteractionBridge {
        fun onDateSelected(year: Int, month: Int, dayOfMonth: Int)
    }

    lateinit var calendarBridge: EventCalendarListInteractionBridge

    private var binding: FragmentEventCalendarLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventCalendarLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.event_list_fragment_container, EventListAttendingFragment())
            .commit()

        binding?.calendarview?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendarBridge.onDateSelected(year, month, dayOfMonth)
        }
    }
}