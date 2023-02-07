package com.example.month_4.ui.utils

import android.content.Context
import android.net.Uri

class Preferences(context: Context) {

    companion object{
        private const val HAVE_SEEN_ONBOARDING_KEY = "have_seen_onBoarding"
        private const val PROFILE_NAME_KEY = "profile_key"
        private const val PROFILE_PICTURE_KEY = "profile_picture_key"
    }

    private val prefs = context.getSharedPreferences(
        "utils",
        Context.MODE_PRIVATE
    )
    fun setHaveSeenOnBoarding() {
        prefs.edit().putBoolean(HAVE_SEEN_ONBOARDING_KEY, true).apply()
    }
    fun getHaveSeenOnBoarding() = prefs.getBoolean(HAVE_SEEN_ONBOARDING_KEY, false)

    fun saveProfilePicture(pictureUri: Uri) {
        prefs.edit().putString(PROFILE_PICTURE_KEY, pictureUri.toString()).apply()
    }
    fun getProfilePicture(): Uri? {
        val uriString = prefs.getString(PROFILE_PICTURE_KEY, null)
        return if (uriString != null) Uri.parse(uriString)
        else null
    }

    fun saveProfileName(profileName:String) {
        prefs.edit().putString(PROFILE_NAME_KEY, profileName).apply() }

    fun getProfileName() = prefs.getString(PROFILE_NAME_KEY, "")

}



