package graeme.hosford.eventmanager.presentation.event.create

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CreateEventPresenter : Presenter<CreateEventView> {

    fun onChooseAttendeesButtonClick()

    fun onCreateEventButtonClick(name: String, description: String, location: String)

}