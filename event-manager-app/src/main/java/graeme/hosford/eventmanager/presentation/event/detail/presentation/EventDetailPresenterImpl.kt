package graeme.hosford.eventmanager.presentation.event.detail.presentation

import graeme.hosford.eventmanager.business.event.detail.EventDetailInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailView
import javax.inject.Inject

class EventDetailPresenterImpl @Inject constructor(
    private val interactor: EventDetailInteractor
) : BasePresenter<EventDetailView, EventDetailInteractor>(interactor), EventDetailPresenter