package natalio.daw.air.model;

/**
 *
 * @author Naatalio
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import natalio.daw.air.IWarnClock;
import natalio.daw.air.model.sprites.IMove;
import natalio.daw.air.model.sprites.SpriteMove;


public class Enemy2 extends SpriteMove implements IWarnClock {
   private Image img;
    //path para la imagen
    private static String pathurl="enemigos/e2.png";
    //para la animación
    private int original_height;
    /**
     * 
     * @param inc incremento del movimiento
     * @param s tamaño del avión
     * @param p coordenadas iniciales
     * @param board rectangulo con las dimensiones del juego para no salirse
     */
    public Enemy2(int inc, Size s, Coordenada p, Rectangle board) {
        super(inc, s, p, true, true, board);
        this.img = new Image(getClass().getResourceAsStream("/" + Enemy2.pathurl));
        //cambia al mover arriba y abajo
        this.original_height=s.getHeight();
    }
    
    /**
     * dibujar, es algo más complejo al moverse las alas
     * @param gc 
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 0, 0, this.getSize().getWidth(), this.getSize().getHeight(),
                    this.getPosicion().getX(), this.getPosicion().getY(),
                    this.getSize().getWidth(), this.getSize().getHeight());
    
    }
    
    //movimiento del avión
    private void move() {
        this.move(IMove.Direction.LEFT);
        
    }
    
    /** 
     * cada vez que se recibe un tictac se mueve, faltan las balas del fighter
     */
    @Override
    public void TicTac() {
        this.move();
       //mover las balas 
    }  

   
  
}

