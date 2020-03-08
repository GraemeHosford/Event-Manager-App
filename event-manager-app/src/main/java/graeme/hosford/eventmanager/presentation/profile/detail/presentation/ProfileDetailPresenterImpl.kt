package graeme.hosford.eventmanager.presentation.profile.detail.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.profile.detail.ProfileDetailInteractor
import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelSingleProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.detail.DEFAULT_MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.profile.detail.ProfileDetailPresenter
import graeme.hosford.eventmanager.presentation.profile.detail.ProfileDetailView
import graeme.hosford.eventmanager.presentation.profile.detail.model.ProfileDetailUiModel
import graeme.hosford.eventmanager.presentation.profile.detail.model.ProfileDetailUiModelProcessor
import javax.inject.Inject

class ProfileDetailPresenterImpl @Inject constructor(
    private val interactor: ProfileDetailInteractor,
    private val processor: ProfileDetailUiModelProcessor
) : BasePresenter<ProfileDetailView, ProfileDetailInteractor>(interactor),
    ProfileDetailPresenter {

    override fun onViewCreated(view: ProfileDetailView) {
        super.onViewCreated(view)
        interactor.registerCallback(ProfileDetailInteractorCallback())
        processor.registerProcessingCallback(ProfileDetailUiModelProcessingCallback())
    }

    override fun getPersonDetail(personId: String) {
        if (personId != DEFAULT_MEMBER_ID_ARG) {
            interactor.getUserDetails(personId)
        } else {
            interactor.getCurrentUserProfile()
        }
    }

    private inner class ProfileDetailInteractorCallback :
        ProfileDetailInteractor.ProfileDetailCallback {
        override fun onUserRetrieved(user: Person) {
            processor.process(user)
        }

        override fun onUserRetrieveFail() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class ProfileDetailUiModelProcessingCallback :
        UiModelSingleProcessor.ProcessingCompleteCallback<ProfileDetailUiModel> {
        override fun onProcessingComplete(model: ProfileDetailUiModel) {
            view?.setData(model)
        }

        override fun onProcessingFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }
}