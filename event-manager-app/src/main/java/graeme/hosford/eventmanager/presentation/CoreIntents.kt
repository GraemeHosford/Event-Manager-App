package graeme.hosford.eventmanager.presentation

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.login.view.LoginActivity

object CoreIntents {

    fun startAppIntent(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    fun openCameraIntent(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }

    fun sendEmailIntent(context: Context, email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email)

        context.startActivity(
            Intent.createChooser(
                emailIntent,
                context.getString(R.string.send_email_chooser_title)
            )
        )
    }

}