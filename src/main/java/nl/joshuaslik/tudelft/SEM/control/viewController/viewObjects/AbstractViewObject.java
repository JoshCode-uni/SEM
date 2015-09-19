/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

/**
 *
 * @author faris
 */
public abstract class AbstractViewObject implements IViewObject {
    
    private final GameController gameController;
    
    public AbstractViewObject(GameController gc){
        this.gameController = gc;
    }
    
    protected abstract Node getNode();
    
    @Override
    public void destroy() {
        gameController.removeNode(getNode());
    }
}
