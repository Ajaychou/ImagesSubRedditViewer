package com.android.imageloader.Callbacks

interface ImageLoaderCallbacks {
    /**
     * onSuccess of network call
     * @param response
     */
    fun onSuccess(response: String)

    /**
     * on failure/exceptions
     */
    fun onFailure(throwable: Throwable)
}