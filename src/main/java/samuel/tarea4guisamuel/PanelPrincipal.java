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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;

    private static String MuestraPantalla = "";
    private static int lado_izquierdo = 0;
    //Contiene un 0 la variable 0 dado que necesito que tenga algún número para que no me de error al 
    //meterlo en lado_izquierdo
    private static String numeros = "0";

    // Constructor
    public PanelPrincipal() {
        initComponents();

    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);
        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();
        // Si es un botón

        if (o instanceof JButton) {

            //Si el String que se utiliza para mostrar las operaciones está vacía 
            //y el texto del botón es * o / ,muestra en pantalla el mensaje que aparcece debajo
            if ((MuestraPantalla.isEmpty())
                    && (((JButton) o).getText().equals("*")
                    || ((JButton) o).getText().equals("/"))) {

                o = "SYNTAX ERROR";
                areaTexto.setText(o.toString());

            }
            
            //Mete el texto que contiene el botón en MuestraPantalla 
            if (!((JButton) o).getText().contains("C")) {
                MuestraPantalla += ((JButton) o).getText();
            }
            
            //Si el texto del botón contiene alguno de esos operadores y el String 
            //MuestraPantalla está vacia, mete los números que se hubieran introducido en la variable
            //lado_izquierdo para poder después operar con ella.
            //!MuestraPantalla.isEmpty() está porque permito que se ponga - para poder poner números en 
            //negativos
            if (((JButton) o).getText().equals("*") || ((JButton) o).getText().equals("+")
                    || ((JButton) o).getText().equals("-") || ((JButton) o).getText().equals("/") && MuestraPantalla.length() != 0) {

                lado_izquierdo = Integer.parseInt(numeros);
                numeros = "0";

            }

        }

    }
}
