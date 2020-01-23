package graeme.hosford.eventmanager.presentation.common.view.fragment

import androidx.fragment.app.Fragment
import butterknife.Unbinder

abstract class BaseFragment : Fragment() {

    protected val unbinder: Unbinder? = null

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }
}