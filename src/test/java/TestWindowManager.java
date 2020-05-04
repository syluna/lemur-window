
import com.jayfella.lemur.window.*;
import com.jayfella.lemur.window.debug.WindowManagerDebugWindow;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.BaseStyles;

public class TestWindowManager extends SimpleApplication {

    public static void main(String... args) {

        TestWindowManager testWindowManager = new TestWindowManager();

        AppSettings appSettings = new AppSettings(true);
        appSettings.setResolution(800, 600);

        testWindowManager.setSettings(appSettings);
        testWindowManager.setShowSettings(false);
        testWindowManager.start();
    }

    @Override
    public void simpleInitApp() {

        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle(BaseStyles.GLASS);

        SimpleWindowManager windowManager = new SimpleWindowManager(this);

        // stagger the windows so it looks pretty.
        // if we don't set a location they are centered automatically.

        int posX = 50;
        int posY = cam.getHeight() - 50;

        int stagger = 50;

        // add a bunch of generic stuff.
        for (int i = 0; i < 5; i++) {
            Window window = windowManager.add(new JmeWindow("Test Window", new Label("Test #" + i)));
            window.setLocation(posX, posY);

            posX += stagger;
            posY -= stagger;
        }

        // a window with buttons to show some dialogs.
        Container dialogTestContent = new Container("null");
        dialogTestContent.addChild(new Label("Click the button to test a dialog."));

        Button showDialogButton = dialogTestContent.addChild(new Button("Show me a dialog."));
        showDialogButton.setInsets(new Insets3f(20, 0, 0, 0));

        showDialogButton.addCommands(Button.ButtonAction.Click, source -> {

            windowManager.showDialog(new JmeDialog(
                    "Test Dialog",
                    "This is a test Dialog. Click any button to close me.",
                    DialogButton.Ok, DialogButton.Cancel) {
                @Override
                public void buttonPressed(DialogButton buttonType) {

                    if (buttonType == DialogButton.Ok) {
                        closeDialog();
                    }
                    else if (buttonType == DialogButton.Cancel) {
                        windowManager.showDialog(new JmeDialog(
                                "Test Dialog 2",
                                "You pressed the Cancel button."));
                    }

                }
            });

        });

        Button showDialogButton2 = dialogTestContent.addChild(new Button("Show me another dialog"));
        showDialogButton2.setInsets(new Insets3f(5, 0, 0, 0));
        showDialogButton2.addClickCommands(source -> {
            windowManager.showDialog(new JmeDialog(
                    "Test Dialog 2",
                    "I am a generic information dialog."));
        });

        Window testDialogWindow = windowManager.add(new JmeWindow("Test Dialog", dialogTestContent));
        testDialogWindow.setLocation(posX, posY);
        posX += stagger;
        posY -= stagger;

        // put the debug window at the top-right
        Window debugWindow = windowManager.add(new WindowManagerDebugWindow());
        Vector3f debugWindowSize = debugWindow.getPreferredSize();
        debugWindow.setLocation(cam.getWidth() - debugWindowSize.x - 10, cam.getHeight() - 10);

        // put the custom window below the debug window
        Window testJmeWindow = windowManager.add(new TestJmeWindow());
        Vector3f testWindowSize = testJmeWindow.getPreferredSize();
        testJmeWindow.setLocation(cam.getWidth() - testWindowSize.x - 10,
                debugWindow.getLocation().y - debugWindowSize.y - 10);

    }

    private class TestJmeWindow extends JmeWindow {

        private final Geometry cube;

        public TestJmeWindow() {
            super("My Awesome Window");

            final float cubeSize = 10;

            Container contentContainer = new Container("null");
            contentContainer.addChild(new Label("I am a custom window."));
            contentContainer.addChild(new Label("You cannot close me."));

            contentContainer.addLight(new DirectionalLight(
                    new Vector3f(-1, -1, -1).normalizeLocal(),
                    ColorRGBA.White));

            // add a moving geometry for fun.
            cube = new Geometry("Rotating Cube", new Box(cubeSize, cubeSize, cubeSize));
            cube.setMaterial(new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"));
            // cube.getMaterial().setColor("Color", ColorRGBA.Orange);
            setContent(contentContainer);

            // get the size of the window so I can position the cube where I want it.
            Vector3f prefSize = getPreferredSize();
            cube.setLocalTranslation(prefSize.x - cubeSize - 30, -20, 0);
            contentContainer.attachChild(cube);

        }

        @Override
        public boolean windowClosing() {
            // don't let the user close the window.
            return false;
        }

        @Override
        public void update(float tpf) {
            cube.rotate(tpf * .25f, tpf * .5f, tpf * .75f);
        }

    }

}
