package graeme.hosford.eventmanager.presentation.common.view.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import butterknife.Unbinder
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastExtensions.toast

abstract class BaseFragment : Fragment(), ToastView {

    protected val unbinder: Unbinder? = null

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun showShortToast(text: String) {
        context?.toast(text, length = Toast.LENGTH_SHORT)
    }

    override fun showLongToast(text: String) {
        context?.toast(text)
    }

    override fun showShortToast(string: Int) {
        showShortToast(context?.getString(string)!!)
    }

    override fun showLongToast(string: Int) {
        showLongToast(context?.getString(string)!!)
    }
}