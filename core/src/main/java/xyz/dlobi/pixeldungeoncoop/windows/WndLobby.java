package xyz.dlobi.pixeldungeoncoop.windows;

import xyz.dlobi.input.Touchscreen;
import xyz.dlobi.noosa.BitmapText;
import xyz.dlobi.noosa.Group;
import xyz.dlobi.noosa.TouchArea;
import xyz.dlobi.pixeldungeoncoop.PixelDungeonCoop;
import xyz.dlobi.pixeldungeoncoop.scenes.PixelScene;
import xyz.dlobi.pixeldungeoncoop.scenes.TitleScene;
import xyz.dlobi.pixeldungeoncoop.ui.RedButton;
import xyz.dlobi.pixeldungeoncoop.ui.TextInput;
import xyz.dlobi.pixeldungeoncoop.ui.Window;

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

        String[] labels =
                {"LAN"};
        Group[] pages =
                {new LanPage()};

        for (int i=0; i < pages.length; i++) {

            add( pages[i] );

            Tab tab = new LobbyTab( labels[i], pages[i] );
            tab.setSize( 40, 25 );
            add( tab );
        }

        select( 0 );
    }

    @Override
    public void onBackPressed() {
        PixelDungeonCoop.switchNoFade(TitleScene.class);
    }

    private class LobbyTab extends LabeledTab {

        private Group page;

        public LobbyTab( String label, Group page ) {
            super( label );
            this.page = page;
        }

        @Override
        protected void select( boolean value ) {
            super.select( value );
            if (page != null) {
                page.visible = page.active = selected;
            }
        }
    }

    private class LanPage extends Group {
        LanPage() {

            super();

            RedButton hostBtn = new RedButton("Host");
            hostBtn.setRect(1, height - 21, width / 2 - 2, 20);
            add(hostBtn);

            RedButton directBtn = new RedButton("Direct Connect") {
                @Override
                protected void onClick() {
                    super.onClick();

                    parent.add(new WndDirectConnect());
                }
            };
            directBtn.setRect(width / 2 + 2, height - 21, width / 2 - 2, 20);
            add(directBtn);
        }
    }

    private class WndDirectConnect extends Window {
        public final int WIDTH = 100;
        public final int HEIGHT = 50;
        WndDirectConnect() {
            resize(WIDTH, HEIGHT);

            BitmapText addrHint = PixelScene.createText( "Address:", 9 );
            addrHint.hardlight( 0xCCCCCC );
            addrHint.measure();
            addrHint.y = (HEIGHT - addrHint.height()) / 4 - 1;
            add( addrHint );

            TextInput input = new TextInput();
            input.setRect(addrHint.width() + 1, 1, WIDTH - addrHint.width() - 2, HEIGHT / 2 - 1);
            add(input);

            RedButton connectBtn = new RedButton("Connect");
            connectBtn.setRect(1, HEIGHT / 2 , WIDTH - 2, HEIGHT / 2 - 1);
            add(connectBtn);
        }
    }
}
