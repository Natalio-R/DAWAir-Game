package natalio.daw.air.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import natalio.daw.air.model.sprites.SpriteMove;

/**
 *
 * @author Natalio Rabasco
 */
public class Bullets extends SpriteMove {
    private Image img;
    private Direction direccion;
    private static String pathurl = "bullets/bullet_rigth.png";
    private int inc = 3;
  
    public Bullets( int inc,Coordenada p, Rectangle board) {
        super(inc, new Size(18, 4), p, true, true, board);
        this.img = new Image(getClass().getResourceAsStream("/" + Bullets.pathurl));
    }

    public void setDireccion(Direction d) {
        this.direccion = d;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 0, 0, this.getSize().getWidth(), this.getSize().getHeight(),
                this.getPosicion().getX(), this.getPosicion().getY(),
                this.getSize().getWidth(), this.getSize().getHeight());
    }

    private void move() {
        this.move(this.direccion);
    }

    public void TicTac() {
        this.move();
    }
}
