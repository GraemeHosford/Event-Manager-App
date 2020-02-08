package graeme.hosford.eventmanager.presentation.common.view

import android.view.View

object ViewExtensions {

    fun View.isVisible(): Boolean {
        return visibility == View.VISIBLE
    }

}