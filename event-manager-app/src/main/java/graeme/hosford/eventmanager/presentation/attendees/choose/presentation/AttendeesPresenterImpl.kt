package graeme.hosford.eventmanager.presentation.attendees.choose.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.attendees.AttendeesInteractor
import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.attendees.choose.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.choose.AttendeesView
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeeComparator
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModelProcessor
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import javax.inject.Inject

class AttendeesPresenterImpl @Inject constructor(
    private val interactor: AttendeesInteractor,
    private val processor: AttendeesUiModelProcessor
) : BasePresenter<AttendeesView, AttendeesInteractor>(interactor),
    AttendeesPresenter {

    private val attendees: ArrayList<String> = ArrayList()

    override fun onViewCreated(view: AttendeesView) {
        super.onViewCreated(view)
        interactor.registerCallback(AttendeesCallback())
        processor.registerProcessingCallback(ProcessorCallback())

        processor.setListComparator(AttendeeComparator.attendeeNameComparator)
    }

    override fun onResume() {
        super.onResume()
        getCompanyMembers()
    }

    override fun getCompanyMembers() {
        interactor.getCompanyMembers()
    }

    override fun onAttendeeClick(email: String) {
        if (attendees.contains(email)) {
            attendees.remove(email)
        } else {
            attendees.add(email)
        }
    }

    override fun onConfirmAttendeesButtonClick() {
        view?.returnAttendeeEmails()
    }

    override fun getAttendees(): ArrayList<String> = attendees

    private inner class AttendeesCallback : AttendeesInteractor.AttendeesCallback {
        override fun onCompanyMembersRetrieved(people: List<Person>) {
            processor.process(people)
        }

        override fun onMemberRetrievalFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class ProcessorCallback :
        UiModelListProcessor.ProcessingCompleteCallback<AttendeesUiModel> {
        override fun onProcessingComplete(models: List<AttendeesUiModel>) {
            view?.showData(models)
        }

        override fun onProcessingFailure() {
            view?.showErrorScreen()
        }
    }
}