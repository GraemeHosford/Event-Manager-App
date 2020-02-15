package graeme.hosford.eventmanager.presentation.common.presenter

import android.content.Intent

interface Presenter<View> {

    fun onViewCreated(view: View)

    fun onResume()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onViewDestroyed()

}