package graeme.hosford.eventmanager.presentation.common.presenter

import android.content.Intent
import androidx.annotation.CallSuper
import androidx.annotation.UiThread
import graeme.hosford.eventmanager.business.common.Interactor

/**
 * The Presenter is the orchestrator of the View. It reacts to lifecycle events and user actions, it manages the
 * user-modified state, it triggers data loading and receives (and manipulates) the data coming from the business layer
 * and passes it on to the View.
 *
 *
 * **Note:** as the majority of base components in this sample app, this class is not comprehensive and it's only
 * used as a demonstration of a more complex real implementation.
 */
@UiThread
abstract class BasePresenter<View, I : Interactor<*>>(private val interactor: I) : Presenter<View> {

    protected var view: View? = null

    @CallSuper
    override fun onViewCreated(view: View) {
        this.view = view
        interactor.onCreate()
    }

    @CallSuper
    override fun onResume() {
    }

    @CallSuper
    override fun onViewDestroyed() {
        view = null
    }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

}