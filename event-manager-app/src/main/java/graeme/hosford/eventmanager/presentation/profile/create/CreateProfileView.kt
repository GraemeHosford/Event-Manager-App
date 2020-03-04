package graeme.hosford.eventmanager.presentation.profile.create

import android.graphics.Bitmap
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

const val CAMERA_REQUEST_CODE = 1910

interface CreateProfileView: ToastView {
    fun startCamera()

    fun setProfileImage(image: Bitmap)

    fun showCompanyCreationFlow()

    fun showMainActivity()
}