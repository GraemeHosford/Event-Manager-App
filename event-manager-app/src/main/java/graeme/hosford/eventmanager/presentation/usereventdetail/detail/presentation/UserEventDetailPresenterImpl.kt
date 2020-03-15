package graeme.hosford.eventmanager.presentation.usereventdetail.detail.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.usereventdetail.detail.UserEventDetailListInteractor
import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.UserEventDetailPresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.UserEventDetailView
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.model.UserEventDetailListUiModel
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.model.UserEventDetailProcessor
import javax.inject.Inject

class UserEventDetailPresenterImpl @Inject constructor(
    private val interactor: UserEventDetailListInteractor,
    private val processor: UserEventDetailProcessor
) : BasePresenter<UserEventDetailView, UserEventDetailListInteractor>(interactor),
    UserEventDetailPresenter {

    override fun onViewCreated(view: UserEventDetailView) {
        super.onViewCreated(view)
        interactor.registerCallback(UserEventDetailListCallback())
        processor.registerProcessingCallback(UserEventDetailProcessingCallback())
    }

    override fun getUserEventDetails(userId: String) {
        interactor.getUserEventDetails(userId)
    }

    private inner class UserEventDetailListCallback :
        UserEventDetailListInteractor.UserEventDetailListCallback {
        override fun onDetailsRetrieved(entities: List<UserEventDetail>) {
            processor.process(entities)
        }

        override fun onDetailRetrievalFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class UserEventDetailProcessingCallback :
        UiModelListProcessor.ProcessingCompleteCallback<UserEventDetailListUiModel> {
        override fun onProcessingComplete(models: List<UserEventDetailListUiModel>) {
            view?.showData(models)
        }

        override fun onProcessingFailure() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }
}