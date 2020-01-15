package graeme.hosford.eventmanager.presentation.common.view

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import graeme.hosford.eventmanager.presentation.common.toast.ToastExtensions.toast
import graeme.hosford.eventmanager.presentation.common.toast.ToastView

abstract class BaseActivity : AppCompatActivity(), ToastView {

    override fun showShortToast(text: String) {
        toast(text, length = Toast.LENGTH_SHORT)
    }

    override fun showLongToast(text: String) {
        toast(text)
    }
}