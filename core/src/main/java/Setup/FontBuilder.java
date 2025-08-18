package Setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontBuilder {

    public BitmapFont bitmap;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public int size;

    public FontBuilder() {
        this(100);
    }

    public FontBuilder(int size) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        this.size = size;
        parameter.size = size;
        bitmap = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public void draw(SpriteBatch batch, String text, float x, float y) {
        bitmap.draw(batch, text, x, y + ((float) size));
    }

    public float getWidth(String text) {
        GlyphLayout layout = new GlyphLayout(bitmap, text);
        return layout.width;
    }

    public float getHeight(String text) {
        GlyphLayout layout = new GlyphLayout(bitmap, text);
        return layout.height;
    }
}







