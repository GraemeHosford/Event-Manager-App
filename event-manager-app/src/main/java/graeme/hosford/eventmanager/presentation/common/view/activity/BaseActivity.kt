package graeme.hosford.eventmanager.presentation.common.view.activity

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.Unbinder
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastExtensions.toast
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

abstract class BaseActivity : AppCompatActivity(),
    ToastView {

    protected var unbinder: Unbinder? = null

    override fun onDestroy() {
        super.onDestroy()
        unbinder?.unbind()
    }

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