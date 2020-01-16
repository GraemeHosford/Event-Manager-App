package graeme.hosford.eventmanager.presentation.common.view

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.Unbinder
import graeme.hosford.eventmanager.presentation.common.toast.ToastExtensions.toast
import graeme.hosford.eventmanager.presentation.common.toast.ToastView

abstract class BaseActivity : AppCompatActivity(), ToastView {

    protected var unbinder: Unbinder? = null

    override fun showShortToast(text: String) {
        toast(text, length = Toast.LENGTH_SHORT)
    }

    override fun showLongToast(text: String) {
        toast(text)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder?.unbind()
    }
}