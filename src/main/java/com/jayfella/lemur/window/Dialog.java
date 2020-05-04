package com.jayfella.lemur.window;

import com.simsilica.lemur.Panel;
import org.jetbrains.annotations.NotNull;

public interface Dialog {

    /**
     * Sets the window manager.
     * Used internally.
     *
     * @param windowManager the WindowManager to set.
     */
    void setWindowManager(@NotNull WindowManager windowManager);
    @NotNull Panel getDialogPanel();

    /**
     * Closes the dialog.
     */
    void closeDialog();

    /**
     * An event that is fired when a button is pressed.
     * @param button the button that has been pressed.
     */
    void buttonPressed(DialogButton button);

}
