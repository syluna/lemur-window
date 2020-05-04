package com.jayfella.lemur.window;

import com.jme3.app.Application;
import org.jetbrains.annotations.NotNull;

public interface WindowManager {

    @NotNull Application getApplication();

    @NotNull
    Window add(@NotNull Window window);
    boolean remove(@NotNull Window window);

    Window getByTitle(@NotNull String title);
    Window getById(@NotNull String id);

    int getWindowCount();

    void bringToFront(@NotNull Window window);
    void sendToBack(@NotNull Window window);

    void showDialog(@NotNull String title, @NotNull String text);
    void showDialog(@NotNull Dialog dialog);
    void closeDialog(@NotNull Dialog dialog);
}
