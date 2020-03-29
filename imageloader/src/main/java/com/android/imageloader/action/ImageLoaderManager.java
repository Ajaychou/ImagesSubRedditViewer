package com.android.imageloader.action;

import com.android.imageloader.Callbacks.ImageLoaderCallbacks;
import com.android.imageloader.Callbacks.ImageLoaderWrapper;
import com.android.imageloader.asynctask.ParseUrlNetwork;

import org.jetbrains.annotations.NotNull;

/**
 * Manager class for Image loader library
 */
public class ImageLoaderManager implements ImageLoaderCallbacks {
    private ImageLoaderWrapper imageLoaderWrapper;

    public ImageLoaderManager(ImageLoaderWrapper imageLoaderWrapper) {
        this.imageLoaderWrapper = imageLoaderWrapper;
    }

    /**
     * Init funnction for calling network call
     * Third party application will call this function to execute network call
     */
    public void init() {
        new ParseUrlNetwork(this).execute("https://www.reddit.com/r/images/hot.json");
    }

    @Override
    public void onSuccess(@NotNull String response) {
        imageLoaderWrapper.onSuccess(response);
    }

    @Override
    public void onFailure(@NotNull Throwable throwable) {
        imageLoaderWrapper.onFailure(throwable);
    }
}
