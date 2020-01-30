package graeme.hosford.eventmanager.presentation.common.view.toast

import androidx.annotation.StringRes

interface ToastView {

    fun showShortToast(text: String)

    fun showLongToast(text: String)

    fun showShortToast(@StringRes string: Int)

    fun showLongToast(@StringRes string: Int)

}