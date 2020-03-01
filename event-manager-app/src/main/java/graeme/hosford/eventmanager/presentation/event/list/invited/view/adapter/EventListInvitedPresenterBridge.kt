package graeme.hosford.eventmanager.presentation.event.list.invited.view.adapter

import graeme.hosford.eventmanager.presentation.event.list.common.view.adapter.BaseEventListItemPresenterBridge

interface EventListInvitedPresenterBridge : BaseEventListItemPresenterBridge {
    fun onGoingResponseClick(eventId: String)

    fun onNotGoingResponseClick(eventId: String)
}