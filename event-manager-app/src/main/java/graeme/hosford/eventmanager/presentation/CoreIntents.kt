package graeme.hosford.eventmanager.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
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
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
        }

        if (emailIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(
                Intent.createChooser(
                    emailIntent,
                    context.getString(R.string.send_email_chooser_title)
                )
            )
        }
    }

}