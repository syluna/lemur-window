package com.jayfella.lemur.window.debug;

import com.jayfella.lemur.window.JmeWindow;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;

public class WindowManagerDebugWindow extends JmeWindow {

    private final String countFormat = "Window Count: %d";
    private final Label windowCountLabel;

    public WindowManagerDebugWindow() {
        super("Window Manager Debug");

        Container container = new Container();
        windowCountLabel = container.addChild(new Label(countFormat));

        setContent(container);
    }

    @Override
    public void update(float tpf) {
        windowCountLabel.setText(String.format(countFormat, getWindowManager().getWindowCount()));
    }

}
