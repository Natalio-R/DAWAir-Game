package natalio.daw.air.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import natalio.daw.air.IWarnClock;
import natalio.daw.air.model.sprites.IMove;
import natalio.daw.air.model.sprites.SpriteMove;

/**
 *
 * @author Natalio Rabasco
 */
public class Enemy extends SpriteMove implements IWarnClock {
   private Image img;
    //path para la imagen
    private static String pathurl="enemigos/e1.png";
    //para la animación
    private int original_height;
    
     public List<Bullets> balas;
   
    /**
     * 
     * @param inc incremento del movimiento
     * @param s tamaño del avión
     * @param p coordenadas iniciales
     * @param board rectangulo con las dimensiones del juego para no salirse
     */
    public Enemy(int inc, Size s, Coordenada p, Rectangle board) {
        super(inc, s, p, true, true, board);
        this.img = new Image(getClass().getResourceAsStream("/" + Enemy.pathurl));
        //cambia al mover arriba y abajo
        this.original_height=s.getHeight();
         this.balas = new ArrayList<>();
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
    
           this.balas.forEach(b -> b.draw(gc));
    }
    
    public void crearBalas(){
    
    if(0.96<Math.random()){
    Bullets tempo = new Bullets(6,new Coordenada(this.getPosicion().getX(),this.getPosicion().getY()),board);
    this.balas.add(tempo);
    tempo.setDireccion(Direction.LEFT);
    
    }
    }
    
    //movimiento del Enemigo
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
       this.balas.forEach(ba -> ba.TicTac());
        this.balas.removeIf(a->{
                return !a.isLive();
        
        });
    }  

   
  
}