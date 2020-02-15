package graeme.hosford.eventmanager.business.event.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.event.detail.EventDetailFirebaseAccess
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class EventDetailInteractorImpl @Inject constructor(
    private val eventDetailFirebaseAccess: EventDetailFirebaseAccess,
    private val currentUserInteractor: CurrentUserInteractor
) : BaseInteractor<EventDetailInteractor.EventDetailCallback>(), EventDetailInteractor {

    private var eventId: String = ""

    override fun onCreate() {
        super.onCreate()
        eventDetailFirebaseAccess.registerCallback(EventDetailCallback())
        currentUserInteractor.registerCallback(UserCallback())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(currentUserInteractor)

    override fun retrieveEvent(eventId: String) {
        this.eventId = eventId
        currentUserInteractor.getUserCompanyId()
    }

    private inner class UserCallback: CurrentUserInteractor.UserCompanyListener {
        override fun onAddUserCompanySuccess() {
        }

        override fun onAddUserCompanyFailure() {
        }

        override fun onUserInfoRetrieved(info: Any?) {
            eventDetailFirebaseAccess.getEventDetails(info as String, eventId)
        }

        override fun onUserInfoRetrievalFailed() {
            callback?.onLoadFailure()
        }
    }

    private inner class EventDetailCallback : EventDetailFirebaseAccess.EventDetailCallback {
        override fun onEventRetrieved(event: Event) {
            callback?.onEventLoaded(event)
        }

        override fun onEventRetrievalFailed() {
            callback?.onLoadFailure()
        }
    }

}