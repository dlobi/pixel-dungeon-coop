package xyz.dlobi.pixeldungeoncoop.windows;

import xyz.dlobi.input.Touchscreen;
import xyz.dlobi.noosa.TouchArea;
import xyz.dlobi.pixeldungeoncoop.PixelDungeonCoop;
import xyz.dlobi.pixeldungeoncoop.scenes.PixelScene;

public class WndLobby extends WndTabbed {

    private static final int WIDTH_P    = 124;
    private static final int HEIGHT_P   = 178;

    private static final int WIDTH_L    = 218;
    private static final int HEIGHT_L   = 124;

    private int width;
    private int height;

    public WndLobby() {

        super();

        if (PixelDungeonCoop.landscape()) {

            width = WIDTH_L;
            height = HEIGHT_L;
        } else {
            width = WIDTH_P;
            height = HEIGHT_P;
        }
        resize( width, height );

        blocker = new TouchArea( 0, 0, PixelScene.uiCamera.width, PixelScene.uiCamera.height ) {
            @Override
            protected void onClick( Touchscreen.Touch touch ) {}
        };
        blocker.camera = PixelScene.uiCamera;

        add(new LabeledTab("PH"));

        for (Tab tab : tabs) {
            tab.setSize( 40, 25 );
        }
    }
}
