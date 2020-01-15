package graeme.hosford.eventmanager.presentation.common.toast

import android.content.Context
import android.widget.Toast

object ToastExtensions {

    fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, text, length).show()
    }

}