package graeme.hosford.eventmanager.presentation.event.list.invited

import graeme.hosford.eventmanager.presentation.event.list.common.BaseEventListPresenter

interface EventListInvitedPresenter : BaseEventListPresenter {
    fun getInvitedEvents()

    fun onGoingResponseClick(eventId: String)

    fun onNotGoingResponseClick(eventId: String)
}