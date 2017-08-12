package xyz.dlobi.pixeldungeoncoop.ui;

import android.app.Activity;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import xyz.dlobi.noosa.Camera;
import xyz.dlobi.noosa.RenderedText;
import xyz.dlobi.noosa.ui.Component;
import xyz.dlobi.pixeldungeoncoop.PixelDungeonCoop;

import static android.R.attr.maxLength;

public class TextInput extends Component {

    private EditText textBox;

    public TextInput() {
        super();
        PixelDungeonCoop.instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textBox = new EditText(PixelDungeonCoop.instance);

                textBox.setTypeface(RenderedText.getFont());
                textBox.setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(maxLength)});
                textBox.setTypeface( RenderedText.getFont() );
                textBox.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

                //sets to single line and changes enter key input to be the same as the positive button
                textBox.setSingleLine();
                textBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        onSelect(true);
                        return true;
                    }
                });

                //doesn't let the keyboard take over the whole UI
                textBox.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

                //centers text
                textBox.setGravity(Gravity.CENTER);

                FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(
                        0,
                        0,
                        Gravity.START | Gravity.TOP);
                layout.setMargins(0, 0, 0, 0);
                PixelDungeonCoop.instance.addContentView(textBox, layout);
            }
        });
    }

    @Override
    protected void layout() {
        super.layout();


        PixelDungeonCoop.instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Camera camera = camera();
                if (camera == null) {
                    // If not added to a parent Gizmo yet, use default camera
                    camera = Camera.main;
                }

                final float scaledZoom = camera.zoom * (PixelDungeonCoop.dispWidth /
                        (float) PixelDungeonCoop.width);

                textBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, 9 * scaledZoom);

                FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(
                        (int)(width*scaledZoom),
                        (int)((height)*scaledZoom),
                        Gravity.START | Gravity.TOP);
                layout.setMargins((int)(x*scaledZoom), (int)(y*scaledZoom), 0, 0);
                textBox.setLayoutParams(layout);
            }
        });
    }

    protected void onSelect(boolean positive ) {};

    @Override
    public void onAdded() {
        super.onAdded();
        layout();
    }

    @Override
    public void destroy() {
        super.destroy();
        if (textBox != null){
            PixelDungeonCoop.instance.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //make sure we remove the edit text and soft keyboard
                    ((ViewGroup) textBox.getParent()).removeView(textBox);

                    InputMethodManager imm = (InputMethodManager)PixelDungeonCoop
                            .instance.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textBox.getWindowToken(), 0);

                    //Soft keyboard sometimes triggers software buttons, so make sure to reassert immersive
                    PixelDungeonCoop.updateImmersiveMode();

                    textBox = null;
                }
            });
        }
    }
}
