package graeme.hosford.eventmanager.presentation

import android.content.Context
import android.content.Intent
import graeme.hosford.eventmanager.presentation.login.view.LoginActivity

object CoreIntents {

    fun startAppIntent(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

}