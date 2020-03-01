package graeme.hosford.eventmanager.presentation.event.list.attending

import graeme.hosford.eventmanager.presentation.event.list.common.BaseEventListPresenter

interface EventListAttendingPresenter : BaseEventListPresenter {
    fun getAttendingEvents()
}