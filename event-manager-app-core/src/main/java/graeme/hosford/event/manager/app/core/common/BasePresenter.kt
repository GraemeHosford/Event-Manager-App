package graeme.hosford.event.manager.app.core.common

import androidx.annotation.CallSuper
import androidx.annotation.UiThread

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
class BasePresenter<V> {
    /**
     * This field is not marked as [Nullable] as it would force subclasses to perform a null check at every
     * access. The base presenter delegates to the subclass, depending on the lifecycle state, the need to perform such
     * check.
     */
    protected var view: V? = null

    @CallSuper
    fun onViewCreated(view: V) {
        this.view = view
    }

    @CallSuper
    fun onViewDestroyed() {
        view = null
    }
}