package graeme.hosford.eventmanager.presentation.event.calendar

import graeme.hosford.eventmanager.presentation.event.list.common.BaseEventListPresenter

interface EventDayListPresenter : BaseEventListPresenter {

    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int)
}