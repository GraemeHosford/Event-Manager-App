package graeme.hosford.eventmanager.presentation.common.view.firebase

import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastExtensions.toast
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

abstract class BaseMessagingService : FirebaseMessagingService(), ToastView {

    override fun showShortToast(text: String) {
        toast(text, length = Toast.LENGTH_SHORT)
    }

    override fun showLongToast(text: String) {
        toast(text)
    }

    override fun showShortToast(string: Int) {
        showShortToast(getString(string))
    }

    override fun showLongToast(string: Int) {
        showLongToast(getString(string))
    }
}