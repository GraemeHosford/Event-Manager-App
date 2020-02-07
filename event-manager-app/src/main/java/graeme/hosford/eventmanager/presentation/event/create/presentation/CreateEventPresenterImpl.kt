package graeme.hosford.eventmanager.presentation.event.create.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.create.CreateEventInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventView
import javax.inject.Inject

class CreateEventPresenterImpl @Inject constructor(
    private val interactor: CreateEventInteractor
) : BasePresenter<CreateEventView, CreateEventInteractor>(interactor), CreateEventPresenter {

    override fun onViewCreated(view: CreateEventView) {
        super.onViewCreated(view)
        interactor.registerCallback(InteractorCallback())
    }

    override fun onChooseAttendeesButtonClick() {
        view?.showChooseAttendeesFragment()
    }

    override fun onCreateEventButtonClick(name: String, description: String, location: String) {
        if (name.isBlank() || description.isBlank() || location.isBlank()) {
            view?.showLongToast(R.string.create_event_error_empty_fields)
        } else {
            interactor.createEvent(name, description, location)
        }
    }

    private inner class InteractorCallback : CreateEventInteractor.CreateEventCallback {
        override fun onEventSaved() {
            view?.showLongToast(R.string.create_event_success)
        }

        override fun onEventSaveFailed() {
            view?.showLongToast(R.string.create_event_error)
        }
    }
}