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

            //Si el String MuestraPantalla contiene la letra C, hago que las variables tengan su valor inicial
            if (((JButton) o).getText().contains("C")) {

                MuestraPantalla = "";
                numeros = "0";
                lado_izquierdo = 0;

            }

            areaTexto.setText(MuestraPantalla);

            //En el caso en el que el texto del botón no es ningún operador, y el String 
            //MuestraPantalla no está vacia, mete el número que contenga el botón en numeros
            if (((JButton) o).getText().equals("0") || ((JButton) o).getText().equals("1")
                    || ((JButton) o).getText().equals("2") || ((JButton) o).getText().equals("3")
                    || ((JButton) o).getText().equals("4") || ((JButton) o).getText().equals("5")
                    || ((JButton) o).getText().equals("6") || ((JButton) o).getText().equals("7")
                    || ((JButton) o).getText().equals("8") || ((JButton) o).getText().equals("9") || ((JButton) o).getText().equals("-") && MuestraPantalla.length() != 0) {

                numeros += ((JButton) o).getText();

            }
            //Cuando el usuario pulse el botón igual, dependiendo de la operación dará un resultado
            if (MuestraPantalla.contains("=")) {
                //La variable que se utilizará para mostrar el resultado de las operaciones de suma,
                // resta y multiplicación
                int resultadoSinDecimales = 0;
                //La La variable que se utilizará para mostrar el resultado de las operaciones de división
                double resultadoConDecimales = 0;
                //Hago un casting de numeros(String) a numeroInt(int que será la que se use para operar
                int numeroInt = Integer.parseInt(numeros);
                //Si el operador contiene un +:
                if (MuestraPantalla.contains("+")) {
                    //Esta es la operación
                    resultadoSinDecimales = (lado_izquierdo + numeroInt);
                    //En la pantalla se muestra la operación y el resultado obtenido
                    areaTexto.setText("" + MuestraPantalla + resultadoSinDecimales);
                    //Vuelvo a poner todas las variables a 0
                    MuestraPantalla = "";
                    numeros = "0";
                    lado_izquierdo = 0;
                    resultadoSinDecimales = 0;

                } else if (MuestraPantalla.contains("*")) {
                    //Para la multiplicación he tenido que hacerla sumando dado que 
                    // con el operador * no me funcionaba
                    for (int i = 0; i < numeroInt; i++) {
                        resultadoSinDecimales += lado_izquierdo;
                    }
                    //En la pantalla se muestra la operación y el resultado obtenido
                    areaTexto.setText("" + MuestraPantalla + resultadoSinDecimales);
                    //Vuelvo a poner todas las variables a 0
                    MuestraPantalla = "";
                    numeros = "0";
                    lado_izquierdo = 0;
                    resultadoSinDecimales = 0;

                } else if (MuestraPantalla.contains("-")) {
                    //He utilizado el operador + porque el segundo número se metía como un 
                    //número negativo y se realizaba una resta, los números se terminaban
                    //sumando, por ejemplo si el usuario metía 5-5, el resultado era 
                    //10 dado que en realidad lo que hacía era 5 - -5.
                    resultadoSinDecimales = lado_izquierdo + numeroInt;
                    //En la pantalla se muestra la operación y el resultado obtenido
                    areaTexto.setText("" + MuestraPantalla + resultadoSinDecimales);
                    //Vuelvo a poner todas las variables a 0
                    MuestraPantalla = "";
                    numeros = "0";
                    lado_izquierdo = 0;
                    resultadoSinDecimales = 0;

                } else if (MuestraPantalla.contains("/")) {
                    //Esta es la operación, he utilizado la variable resultadoConDecimales,
                    //que es un double, porque la división es la única que puede dar decimales
                    resultadoConDecimales = lado_izquierdo / numeroInt;
                    //En la pantalla se muestra la operación y el resultado obtenido
                    areaTexto.setText("" + MuestraPantalla + resultadoConDecimales);
                    //Vuelvo a poner todas las variables a 0
                    MuestraPantalla = "";
                    numeros = "0";
                    lado_izquierdo = 0;
                    resultadoConDecimales = 0;
                }
            }

        }

    }
}
