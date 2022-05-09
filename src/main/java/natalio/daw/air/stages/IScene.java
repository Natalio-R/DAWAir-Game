/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package natalio.daw.air.stages;

import natalio.daw.air.model.sprites.IDrawable;
import natalio.daw.air.model.sprites.IKeyListener;
import natalio.daw.air.IWarnClock;



/**
 *
 * @author Pedro
 */
public interface IScene extends IKeyListener, IWarnClock, IDrawable{
    public enum SceneState{
        PRE_STARTED,
        STARTED,
        PAUSED,
        PRE_END,
        END
    }
    public SceneState getState();
    public void start();
    public void stop();
    public void pause();
    public void reset();
}
