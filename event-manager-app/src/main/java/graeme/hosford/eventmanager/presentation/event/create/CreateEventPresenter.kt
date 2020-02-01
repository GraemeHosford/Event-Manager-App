package graeme.hosford.eventmanager.presentation.event.create

interface CreateEventPresenter {

    fun onCreateEventButtonClick(name: String, description: String, location: String)

}