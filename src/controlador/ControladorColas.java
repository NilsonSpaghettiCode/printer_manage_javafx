/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.sound.sampled.SourceDataLine;

import mundo.Cola;
import mundo.ListaTempo;
import mundo.Nodo;
import mundo.Solicitud;

/**
 *
 * @author Nilson
 */
public class ControladorColas {

    private ListaTempo recibido;
    private Cola impresoraA, impresoraB, impresoraEspera;

    public ControladorColas(ListaTempo recibido, Cola impresoraA, Cola impresoraB, Cola impresoraEspera) //Constructor Importante
    {
        this.recibido = recibido;
        this.impresoraA = impresoraA;
        this.impresoraB = impresoraB;
        this.impresoraEspera = impresoraEspera;
    }

    public ControladorColas(ListaTempo recibido) //Contructor Importante
    {
        System.out.println("Lista Temporal recibida");
        this.recibido = recibido;
        impresoraA = new Cola();
        impresoraB = new Cola();
        impresoraEspera = new Cola();

    }

    public ControladorColas(ListaTempo recibido, Cola impresoraA, Cola impresoraB) {
        this.recibido = recibido;
        this.impresoraA = impresoraA;
        this.impresoraB = impresoraB;
    }

    public static void main(String[] args) //Test
    {
        Solicitud solicitud = new Solicitud("GERENCIA", "ByN");
        Solicitud solicitud2 = new Solicitud("MERCADEO", "ByN");
        Solicitud solicitud3 = new Solicitud("ADMON", "Color");
        Solicitud solicitud4 = new Solicitud("PRODUC", "ByN");
        Nodo nodo1 = new Nodo(solicitud);
        Nodo nodo2 = new Nodo(solicitud2);
        Nodo nodo3 = new Nodo(solicitud3);
        Nodo nodo4 = new Nodo(solicitud4);

        Cola colaA = new Cola();
        Cola colaB = new Cola();
        ListaTempo lista = new ListaTempo();
        lista.añadir(nodo1);
        lista.añadir(nodo2);
        lista.añadir(nodo3);
        lista.añadir(nodo4);

        lista.verLista();

        ControladorColas ctrlc = new ControladorColas(lista);
        ctrlc.AsignarColas();

        colaB = ctrlc.getImpresoraB();
        colaA = ctrlc.getImpresoraA();

        colaA.imprimirCola();
        colaB.imprimirCola();

        System.out.println("Antes");
        System.out.println(colaA.getContador());
        System.out.println(colaA.getStaticoInt());

        colaA.desEncolar();
        System.out.println("Despues");

        System.out.println(colaA.getContador());
        System.out.println(colaA.getStaticoInt());

        lista.verLista();

    }

    public ControladorColas AsignarColas() {

        ListaTempo listaDeCorrido = recibido;

        Nodo aSave = new Nodo();
        aSave = listaDeCorrido.desListar();

        System.out.println("ANTES");

        System.out.println("Valor temporal de la impresora A es:" + impresoraA.getContador()); //pruebaDeEscritorio.txt
        System.out.println("Valor temporal de la impresora B es:" + impresoraB.getContador());
        System.out.println("Valor temporal de la impresora SuB_B es:" + impresoraEspera.getContador());

        for (int x = 0; x < listaDeCorrido.getContadorStatic(); x++) {

            if (aSave.getDato().getAreaDeTrabajo().equalsIgnoreCase("GERENCIA")) {

                if (impresoraA.getContador() > 2)
                {
                    if (impresoraA.getContador() == impresoraB.getContador())
                    {
                        
                    impresoraA.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("+Gerencia");
                        
                        
                    } else if(impresoraB.getContador()> impresoraA.getContador())
                    {
                    
                    impresoraA.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("+Gerencia");
                        
                        
                    }else if (impresoraB.getContador()>=3){
                        
                    impresoraA.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("+Gerencia");
                        
                    } else {
                        
                        impresoraB.encolar(aSave);
                        aSave = listaDeCorrido.desListar();
                        System.out.println("++Gerencia");
                    }
                } else {

                    impresoraA.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("+Gerencia");

                }

            } else if (aSave.getDato().getAreaDeTrabajo().equalsIgnoreCase("ADMON")) {
                impresoraA.encolar(aSave);
                aSave = listaDeCorrido.desListar();
                System.out.println("+Administracion");

            } else if (aSave.getDato().getAreaDeTrabajo().equalsIgnoreCase("PRODUC")) {

                if (impresoraB.getContador() > 2) {
                    impresoraEspera.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("++Product");

                } else {

                    impresoraB.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("+Product");

                }

                /*
                impresoraB.encolar(aSave);
                aSave = listaDeCorrido.desListar();
                System.out.println("+Product");
                 */
            } else if (aSave.getDato().getAreaDeTrabajo().equalsIgnoreCase("MERCADEO")) {

                if (impresoraB.getContador() > 2) {

                    impresoraEspera.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("++Mercadeo");

                } else {

                    impresoraB.encolar(aSave);
                    aSave = listaDeCorrido.desListar();
                    System.out.println("+Mercadeo");

                }

                /*
                impresoraB.encolar(aSave);
                aSave = listaDeCorrido.desListar();
                System.out.println("+Mercadeo");
                 */
            }

            System.out.println("DESPUES");
            System.out.println("Valor temporal de la impresora A es:" + impresoraA.getContador()); //pruebaDeEscritorio.txt
            System.out.println("Valor temporal de la impresora B es:" + impresoraB.getContador());
            System.out.println("Valor temporal de la impresora SuB_B es:" + impresoraEspera.getContador());

            System.out.println(x);
        }

        ControladorColas ctrlRetornado = new ControladorColas(recibido, impresoraA, impresoraB, impresoraEspera);

        return ctrlRetornado;

    }

    public void asignacion() {
        Nodo temporal = recibido.getInicio();
        for (int i = 0; i < recibido.getContador(); i++) {

            if (temporal.getDato().getAreaDeTrabajo().equalsIgnoreCase("ADMON")) {

                impresoraA.encolar(recibido.desListar());
                temporal = temporal.getSiguiente();

            } else if (temporal.getDato().getAreaDeTrabajo().equalsIgnoreCase("GERENCIA")) {

                impresoraA.encolar(recibido.desListar());
                temporal = temporal.getSiguiente();

            } else if (temporal.getDato().getAreaDeTrabajo().equalsIgnoreCase("PRODUC")) {

                impresoraB.encolar(recibido.desListar());
                temporal = temporal.getSiguiente();

            } else if (temporal.getDato().getAreaDeTrabajo().equalsIgnoreCase("MERCANCIA")) {
                impresoraB.encolar(recibido.desListar());
                temporal = temporal.getSiguiente();

            }

        }

    }

    public ListaTempo getRecibido() {
        return recibido;
    }

    public void setRecibido(ListaTempo recibido) {
        this.recibido = recibido;
    }

    public Cola getImpresoraA() {
        return impresoraA;
    }

    public void setImpresoraA(Cola impresoraA) {
        this.impresoraA = impresoraA;
    }

    public Cola getImpresoraB() {
        return impresoraB;
    }

    public void setImpresoraB(Cola impresoraB) {
        this.impresoraB = impresoraB;
    }

    public Cola getImpresoraEspera() {
        return impresoraEspera;
    }

    public void setImpresoraEspera(Cola impresoraEspera) {
        this.impresoraEspera = impresoraEspera;
    }

}
