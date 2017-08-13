package xyz.dlobi.pixeldungeoncoop.scenes;

import xyz.dlobi.noosa.Camera;
import xyz.dlobi.noosa.Game;
import xyz.dlobi.pixeldungeoncoop.PixelDungeonCoop;
import xyz.dlobi.pixeldungeoncoop.ui.Archs;
import xyz.dlobi.pixeldungeoncoop.ui.ExitButton;
import xyz.dlobi.pixeldungeoncoop.ui.RedButton;
import xyz.dlobi.pixeldungeoncoop.ui.TextInput;
import xyz.dlobi.pixeldungeoncoop.ui.Window;
import xyz.dlobi.pixeldungeoncoop.windows.WndLobby;

public class LobbyScene extends PixelScene {

    @Override
    public void create() {
        super.create();

        uiCamera.visible = false;

        int w = Camera.main.width;
        int h = Camera.main.height;

        Archs archs = new Archs();
        archs.setSize(w, h);
        add(archs);

        WndLobby lobbyWindow = new WndLobby();
        lobbyWindow.select(0);
        add(lobbyWindow);

        ExitButton btnExit = new ExitButton();
        btnExit.setPos(Camera.main.width - btnExit.width(), 0);
        add(btnExit);
    }

    @Override
    protected void onBackPressed() {
        PixelDungeonCoop.switchNoFade(TitleScene.class);
    }
}
