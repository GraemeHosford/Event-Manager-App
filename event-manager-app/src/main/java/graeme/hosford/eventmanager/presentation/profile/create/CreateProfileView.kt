package graeme.hosford.eventmanager.presentation.profile.create

import android.graphics.Bitmap

const val CAMERA_REQUEST_CODE = 1910

interface CreateProfileView {
    fun startCamera()

    fun setProfileImage(image: Bitmap)
}