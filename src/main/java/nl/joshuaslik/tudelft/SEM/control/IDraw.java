/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import javafx.scene.Node;

/**
 *
 * @author faris
 */
public interface IDraw {

    public void drawOnScreen(Node n);

    public void removeFromScreen(Node n);

    public void playerDied();
}
