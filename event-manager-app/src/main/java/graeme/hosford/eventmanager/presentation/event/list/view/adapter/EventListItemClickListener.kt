package graeme.hosford.eventmanager.presentation.event.list.view.adapter

interface EventListItemClickListener {

    fun onGoingResponseClick(eventId: String)

    fun onNotGoingResponseClick(eventId: String)

    fun onEventListItemClick(eventId: String)

}