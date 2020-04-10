package graeme.hosford.eventmanager.presentation.event.calendar.presentation

import graeme.hosford.eventmanager.business.event.day.EventDayListInteractor
import graeme.hosford.eventmanager.presentation.event.calendar.EventDayListPresenter
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import java.util.*
import javax.inject.Inject

class EventDayListPresenterImpl @Inject constructor(
    private val eventDayListInteractor: EventDayListInteractor,
    processor: EventListItemUiModelProcessor
) : BaseEventListPresenterImpl(processor, eventDayListInteractor), EventDayListPresenter {

    override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(year, month, dayOfMonth, 0, 0, 0)
        }

        eventDayListInteractor.getEventsOnDay(calendar.timeInMillis)
    }
}