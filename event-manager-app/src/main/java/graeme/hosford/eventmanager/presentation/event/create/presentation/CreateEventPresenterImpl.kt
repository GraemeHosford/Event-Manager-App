package graeme.hosford.eventmanager.presentation.event.create.presentation

import graeme.hosford.eventmanager.business.event.create.CreateEventInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventView
import javax.inject.Inject

class CreateEventPresenterImpl @Inject constructor(
    private val interactor: CreateEventInteractor
) : BasePresenter<CreateEventView, CreateEventInteractor>(interactor), CreateEventPresenter