/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samuel.tarea4guisamuel;

/**
 *
 * @author samuel
 */
import javax.swing.JFrame;

/**
 * @author Juan Carlos Fernández Vico
 */
public class Calculadora {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculadora");
        frame.setBounds(250, 250, 0, 0);
        frame.add(new PanelPrincipal());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

    }

}
