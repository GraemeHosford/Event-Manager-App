package graeme.hosford.eventmanager.presentation.common.presenter;

import android.content.Intent;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

import graeme.hosford.eventmanager.business.common.Interactor;

/**
 * The Presenter is the orchestrator of the View. It reacts to lifecycle events and user actions, it manages the
 * user-modified state, it triggers data loading and receives (and manipulates) the data coming from the business layer
 * and passes it on to the View.
 * <p>
 * <b>Note:</b> as the majority of base components in this sample app, this class is not comprehensive and it's only
 * used as a demonstration of a more complex real implementation.
 */
@UiThread
@SuppressWarnings("WeakerAccess")
public abstract class BasePresenter<View, I extends Interactor> {

    /**
     * This field is not marked as {@link Nullable} as it would force subclasses to perform a null check at every
     * access. The base presenter delegates to the subclass, depending on the lifecycle state, the need to perform such
     * check.
     */
    protected View view;

    private I interactor;

    public BasePresenter(I interactor) {
        this.interactor = interactor;
    }

    @CallSuper
    public void onViewCreated(@NonNull View view) {
        this.view = view;
        interactor.onCreate();
    }

    @CallSuper
    public void onViewDestroyed() {
        view = null;
    }

    @CallSuper
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

}