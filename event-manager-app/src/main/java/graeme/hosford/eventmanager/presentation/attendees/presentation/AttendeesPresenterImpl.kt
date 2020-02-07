package graeme.hosford.eventmanager.presentation.attendees.presentation

import graeme.hosford.eventmanager.business.attendees.AttendeesInteractor
import graeme.hosford.eventmanager.presentation.attendees.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.AttendeesView
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import javax.inject.Inject

class AttendeesPresenterImpl @Inject constructor(
    private val interactor: AttendeesInteractor
) : BasePresenter<AttendeesView, AttendeesInteractor>(interactor),
    AttendeesPresenter