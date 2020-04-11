package graeme.hosford.eventmanager.presentation.event.calendar.presentation

import graeme.hosford.eventmanager.business.event.day.EventDayListInteractor
import graeme.hosford.eventmanager.presentation.event.calendar.EventDayListPresenter
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EventDayListPresenterImpl @Inject constructor(
    private val eventDayListInteractor: EventDayListInteractor,
    processor: EventListItemUiModelProcessor
) : BaseEventListPresenterImpl(processor, eventDayListInteractor), EventDayListPresenter {

    private var startTime: Long = 0

    override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(year, month, dayOfMonth, 0, 0, 0)
        }

        startTime = calendar.timeInMillis

        eventDayListInteractor.getEventsOnDay(startTime)
    }

    override fun eventFilterOptions(model: EventListItemUiModel): Boolean {
        val start = model.startDate.timeInMillis
        val end = model.endDate.timeInMillis

        if ((start >= startTime) && (end < (startTime + TimeUnit.DAYS.toMillis(1)))) {
            return true
        }

        return false
    }
}