package xyz.dlobi.pixeldungeoncoop.scenes;

import xyz.dlobi.noosa.Camera;
import xyz.dlobi.pixeldungeoncoop.PixelDungeonCoop;
import xyz.dlobi.pixeldungeoncoop.ui.Archs;
import xyz.dlobi.pixeldungeoncoop.ui.ExitButton;

public class LobbyScene extends PixelScene {

    @Override
    public void create() {
        super.create();

        uiCamera.visible = false;

        int w = Camera.main.width;
        int h = Camera.main.height;

        Archs archs = new Archs();
        archs.setSize( w, h );
        add( archs );

        ExitButton btnExit = new ExitButton();
        btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
        add( btnExit );
    }

    @Override
    protected void onBackPressed() { PixelDungeonCoop.switchNoFade( TitleScene.class ); }
}
