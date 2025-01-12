/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import spdvi.logica.LogicaJMenu;

/**
 *
 * @author magi
 */
public class LookAndFeel {

    public LookAndFeel() {
    }
    
    public void setLAF(String theme, JFrame aixo)
    {
        try {
            LogicaJMenu.setTema(theme);
            UIManager.setLookAndFeel(LogicaJMenu.getTema());
            SwingUtilities.updateComponentTreeUI(aixo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
