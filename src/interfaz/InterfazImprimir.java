/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mundo.Cola;
import mundo.ListaTempo;
import mundo.Nodo;
import mundo.Solicitud;

/**
 *
 * @author Nilson
 */
public class InterfazImprimir extends Application {

    private ListaTempo listaSolicitudes;
    private Cola impresora1, impresora2, colaDeEspera;

    //TableView y elementos necesarios para la tabla
    TableView<Solicitud> tabla;
    TableView<Solicitud> tablaImpresora2;
    TableView<Solicitud> tablaSubCola;

    private ObservableList<Solicitud> solicituds;
    private ObservableList<Solicitud> solicituds2;
    private ObservableList<Solicitud> solicitudsSubB;

    //atributos de la interfaz
    private Label etiquetaIMP1, etiquetaIMP2, etiquetaSub_B;
    private TextArea viewImpre1, viewImpre2;

    private Stage ventanaFinal;
    private Scene lastEscenario;

    private StackPane orgNormal;
    private VBox orgVerticalTotal, orgVeticalA, orgVerticalB, orgVerticalSub_B;
    private HBox orgHorizontalAreas, orgHorizontalButtonBox;

    private Button btnAtras, btnImprimir, btnVerColas;

    private boolean estado;
    private static int hecho;

    public InterfazImprimir(ListaTempo listIn) {
        listaSolicitudes = listIn;
        hecho = 0;
    }

    public InterfazImprimir(ListaTempo listaIn, Cola impresoraA, Cola impresoraB, Cola colaDeEspera) {

        listaSolicitudes = listaIn;

        impresora1 = impresoraA;
        impresora2 = impresoraB;
        this.colaDeEspera = colaDeEspera;
        hecho = 0;

    }

    @Override
    public void start(Stage stage) {
        estado = false;

        etiquetaIMP1 = new Label("Impresora #1");
        etiquetaIMP2 = new Label("Impresora #2");
        etiquetaSub_B = new Label("Sub Cola");

        viewImpre1 = new TextArea();
        viewImpre1.setEditable(false);

        viewImpre2 = new TextArea();
        viewImpre2.setEditable(false);

        btnAtras = new Button("Atras");
        btnImprimir = new Button("Imprimir");
        btnVerColas = new Button("Ver SubCola Impresora #2");

        orgHorizontalAreas = new HBox(50);
        orgHorizontalButtonBox = new HBox(25);

        orgVerticalB = new VBox(15);
        orgVeticalA = new VBox(15);
        orgVerticalSub_B = new VBox(15);

        orgVerticalTotal = new VBox(10);
        orgNormal = new StackPane();

        //Configuracion de los organizadores
        orgHorizontalAreas.setAlignment(Pos.CENTER);
        orgHorizontalButtonBox.setAlignment(Pos.CENTER);

        orgVeticalA.setAlignment(Pos.CENTER);
        orgVerticalB.setAlignment(Pos.CENTER);
        orgVerticalSub_B.setAlignment(Pos.CENTER);

        orgVerticalTotal.setAlignment(Pos.CENTER);
        orgNormal.setAlignment(Pos.CENTER);

        //Configuracion de los textArea
        //
        Nodo tempoTest = new Nodo();
        tempoTest = listaSolicitudes.getInicio();
        String total = "";
        for (int x = 0; x < listaSolicitudes.getContador(); x++) {
            total = total + tempoTest.getDato().getAreaDeTrabajo();
            tempoTest = tempoTest.getSiguiente();

        }
        viewImpre1.setText(total);

        //Creaccion y asignacion de tablas
        //Creacion de columnas para para la tabla principal
        //Columna de Area
        TableColumn<Solicitud, String> areaColumna = new TableColumn<>("Area");
        areaColumna.setMinWidth(200);
        areaColumna.setCellValueFactory(new PropertyValueFactory<>("areaDeTrabajo"));
        //Columna de Tipo de impresion
        TableColumn<Solicitud, String> tipoColumna = new TableColumn<>("Tipo de Trabajo");
        tipoColumna.setMinWidth(200);
        tipoColumna.setCellValueFactory(new PropertyValueFactory<>("tipoDeTrabajo"));

        tabla = new TableView<>();
        tabla.setItems(getSolicitudsA());
        tabla.getColumns().addAll(areaColumna, tipoColumna);

        //Tabla Impresora #2
        //Configuracion de la columnas
        TableColumn<Solicitud, String> areaColumnaB = new TableColumn<>("Area");
        areaColumnaB.setMinWidth(200);
        areaColumnaB.setCellValueFactory(new PropertyValueFactory<>("areaDeTrabajo"));
        //Columna de Tipo de impresion
        TableColumn<Solicitud, String> tipoColumnaB = new TableColumn<>("Tipo de Trabajo");
        tipoColumnaB.setMinWidth(200);
        tipoColumnaB.setCellValueFactory(new PropertyValueFactory<>("tipoDeTrabajo"));

        tablaImpresora2 = new TableView<>();
        tablaImpresora2.setItems(getSolicitudsImpresoraB());
        tablaImpresora2.getColumns().addAll(areaColumnaB, tipoColumnaB);

        //Tabla SubCola
        //Configuracion Columnas de la SubCola
        TableColumn<Solicitud, String> areaColumnaSub = new TableColumn<>("Area");
        areaColumnaSub.setMinWidth(200);
        areaColumnaSub.setCellValueFactory(new PropertyValueFactory<>("areaDeTrabajo"));
        //Columna de Tipo de impresion
        TableColumn<Solicitud, String> tipoColumnaSub = new TableColumn<>("Tipo de Trabajo");
        tipoColumnaSub.setMinWidth(200);
        tipoColumnaSub.setCellValueFactory(new PropertyValueFactory<>("tipoDeTrabajo"));

        tablaSubCola = new TableView<>();

        tablaSubCola.setItems(getSubCola());
        tablaSubCola.getColumns().addAll(areaColumnaSub, tipoColumnaSub);

//Asignacion de elementos a los organizadores, y organizadores a organizadores padres
        orgVeticalA.getChildren().addAll(etiquetaIMP1, tabla);
        orgVerticalB.getChildren().addAll(etiquetaIMP2, tablaImpresora2);
        orgVerticalSub_B.getChildren().addAll(etiquetaSub_B, tablaSubCola);

        orgHorizontalAreas.getChildren().addAll(orgVeticalA, orgVerticalB);
        orgHorizontalButtonBox.getChildren().addAll(btnAtras, btnImprimir, btnVerColas);

        orgVerticalTotal.getChildren().addAll(orgHorizontalAreas, orgHorizontalButtonBox);

        orgNormal.getChildren().add(orgVerticalTotal);

        //Eventos de los botones 
        btnAtras.setOnAction((t) -> {

            InterfazApp rui = new InterfazApp(impresora1, impresora2, colaDeEspera, listaSolicitudes);
            rui.start(ventanaFinal);
            ventanaFinal.close();

        });

        btnVerColas.setOnAction((t)
                -> {
            if (estado == false) {
                orgHorizontalAreas.getChildren().add(orgVerticalSub_B);

                estado = true;

                btnVerColas.setText("Dejar de ver SubCola Impresora #2");

            } else if (estado == true) {

                orgHorizontalAreas.getChildren().remove(orgVerticalSub_B);
                estado = false;
                btnVerColas.setText("Ver SubCola Impresora #2");

            }
        });

        btnImprimir.setOnAction((t)
                -> //testeo: C:\Users\Luis\Documents\txt\testFinal.txt, C:\Users\Luis\Documents\txt\pruebaDeEscritorio.txt
                {
                    /*
                    if (impresora2.getInicio().getDato().getTipoDeTrabajo().equalsIgnoreCase("ByN")) {
                        Nodo dinamico = impresora2.desEncolar();
                        
                        notificacionPrint(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
                        
                        if(colaDeEspera.getContador()!=0)
                        {
                        Nodo dinamicoDos = colaDeEspera.desEncolar();
                        impresora2.encolar(dinamicoDos);
                        }     
                        
                        actualizarTablaBoth();
                            

                    } else if (impresora2.getContador() == 1 && impresora2.getInicio().getDato().getTipoDeTrabajo().equalsIgnoreCase("Color") && colaDeEspera.getContador() == 0) {
                        Nodo dinamico = impresora2.desEncolar();
                        actualizarTablaBoth();
                        notificacionPrint(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());

                    } else if(impresora2.getInicio().getDato().getTipoDeTrabajo().equalsIgnoreCase("Color")&&impresora2.getContador()>1)
                    {
                        Nodo dinamico = impresora2.desEncolar();
                        impresora2.encolar(dinamico);
                        actualizarTablaBoth();
                        notificacionColor();     
                    }
                     */

                    imprimirA();
                    imprimirB();

                });

        //Creacion del escenario, sumado a la asignacion de sus componentes
        lastEscenario = new Scene(orgNormal, 1350, 600);

        ventanaFinal = new Stage();
        ventanaFinal.setResizable(false);
        ventanaFinal.setTitle("Vista control de impresoras");
        ventanaFinal.setScene(lastEscenario);
        ventanaFinal.centerOnScreen();
        stage = ventanaFinal;
        ventanaFinal.show();

    }

    public ObservableList<Solicitud> getSolicitudsA() {

        solicituds = FXCollections.observableArrayList();

        Nodo nodoDeCambio = impresora1.getInicio();

        for (int x = 0; x < impresora1.getContador(); x++) {

            solicituds.add(new Solicitud(nodoDeCambio.getDato().getAreaDeTrabajo(), nodoDeCambio.getDato().getTipoDeTrabajo()));
            nodoDeCambio = nodoDeCambio.getSiguiente();

        }

        return solicituds;
    }

    public ObservableList<Solicitud> getSolicitudsImpresoraB() {
        solicituds2 = FXCollections.observableArrayList();

        Nodo nodoDeCambio = impresora2.getInicio();

        for (int x = 0; x < impresora2.getContador(); x++) {

            solicituds2.add(new Solicitud(nodoDeCambio.getDato().getAreaDeTrabajo(), nodoDeCambio.getDato().getTipoDeTrabajo()));
            nodoDeCambio = nodoDeCambio.getSiguiente();

        }

        return solicituds2;
    }

    public ObservableList<Solicitud> getSubCola() {
        solicitudsSubB = FXCollections.observableArrayList();

        Nodo nodoDeCambio = colaDeEspera.getInicio();

        for (int x = 0; x < colaDeEspera.getContador(); x++) {

            solicitudsSubB.add(new Solicitud(nodoDeCambio.getDato().getAreaDeTrabajo(), nodoDeCambio.getDato().getTipoDeTrabajo()));
            nodoDeCambio = nodoDeCambio.getSiguiente();

        }

        return solicitudsSubB;
    }

    public void notificacionPrint(String area, String typeImpresion) {

        Alert impreso = new Alert(Alert.AlertType.INFORMATION);
        impreso.setContentText("Se ha impreso una solicitud de: " + area + ", su tipo de impresion es: " + typeImpresion);
        impreso.show();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterfazImprimir.class.getName()).log(Level.SEVERE, null, ex);
        }

        impreso.close();

    }

    public void notificacionColor(String area, String typoPrint) {

        Alert impreso = new Alert(Alert.AlertType.INFORMATION);
        impreso.setContentText("Se ha devuelto a la cola la solicitud: " + area + ", su tipo de impresion es: " + typoPrint);
        impreso.show();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterfazImprimir.class.getName()).log(Level.SEVERE, null, ex);
        }

        impreso.close();

    }

    public void noElementsNoficacion() {

        Alert impreso = new Alert(Alert.AlertType.WARNING);
        impreso.setContentText("No hay elementos en la impresora: ");
        impreso.show();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterfazImprimir.class.getName()).log(Level.SEVERE, null, ex);
        }

        impreso.close();

    }

    public void imprimirA()
    {
        System.out.println("wait....");
        if (impresora1.colaVacio()!=true)
        {
            int corredor = impresora1.getStaticoInt();
            System.out.println("wait!!!!!");
            while (corredor!=0)
            {                 
                Nodo dinamico = impresora1.desEncolar();
                corredor--;
                if(dinamico.getDato().getTipoDeTrabajo().equalsIgnoreCase("ByN"))
                {
                
                    notificacionPrint(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
  
                }else if(dinamico.getDato().getTipoDeTrabajo().equalsIgnoreCase("Color"))
                {
                    boolean hayByN = false;
                    Nodo recorrido = impresora1.getInicio();
                    for (int i = 0; i < impresora1.getContador(); i++)
                    {
                        if (recorrido.getDato().getTipoDeTrabajo().equalsIgnoreCase("ByN")){
                            
                            hayByN = true;
                            System.out.println("Area:"+recorrido.getDato().getAreaDeTrabajo()+"TipoPrint:"+recorrido.getDato().getTipoDeTrabajo());
                            
                        }
                        recorrido = recorrido.getSiguiente();                    
                    }
                    
                    if (hayByN)
                    {
                        impresora1.encolar(dinamico);
                        
                        notificacionColor(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
                        corredor++;
                        
                    }else
                    {
                        notificacionPrint(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
                    }
                }
                
            }
            solicituds.clear();
            
        }
        
        
        
        
        
    }

    public void imprimirB() { //testeo C:\Users\Luis\Documents\txt\testFinal.txt

        System.out.println("wait....");
        if (impresora2.colaVacio()!=true)
        {
            int corredor = impresora2.getStaticoInt();
            System.out.println("wait!!!!!");
            while (corredor!=0)
            {                 
                Nodo dinamico = impresora2.desEncolar();
                corredor--;
                if(dinamico.getDato().getTipoDeTrabajo().equalsIgnoreCase("ByN"))
                {
                
                    notificacionPrint(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
                    if(colaDeEspera.colaVacio()!=true){
                    impresora2.encolar(colaDeEspera.desEncolar());
                    corredor++;
                    System.out.println("Se ha agregado a la impresora B");
                    }
                    //impresora2.encolar(colaDeEspera.desEncolar());  // ..... terminar
                    //corredor++;
                    
                }else if(dinamico.getDato().getTipoDeTrabajo().equalsIgnoreCase("Color"))
                {
                    boolean hayByN = false;
                    Nodo recorrido = impresora2.getInicio();
                    for (int i = 0; i < impresora2.getContador(); i++)
                    {
                        if (recorrido.getDato().getTipoDeTrabajo().equalsIgnoreCase("ByN")){
                            
                            hayByN = true;
                            System.out.println("Area:"+recorrido.getDato().getAreaDeTrabajo()+"TipoPrint:"+recorrido.getDato().getTipoDeTrabajo());
                            
                        }
                        recorrido = recorrido.getSiguiente();                    
                    }
                    
                    if (hayByN)
                    {
                        impresora2.encolar(dinamico);
                        
                        notificacionColor(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
                        corredor++;
                        
                    }else
                    {
                        notificacionPrint(dinamico.getDato().getAreaDeTrabajo(), dinamico.getDato().getTipoDeTrabajo());
                    if(colaDeEspera.colaVacio()!=true){
                    impresora2.encolar(colaDeEspera.desEncolar());
                    corredor++;
                    System.out.println("Se ha agregado a la impresora B");
                    }
                    }
                }
                
            }
            
            solicituds2.clear();
            solicitudsSubB.clear();
        }
        

    }

    public void actualizarTablaBoth() {

        if (impresora2.colaVacio()) {
            noElementsNoficacion();

        } else {
            solicituds2.clear();
            tablaImpresora2.setItems(getSolicitudsImpresoraB());
            tablaImpresora2.refresh();

        }

        if (colaDeEspera.colaVacio()) {

            noElementsNoficacion();

        } else {
            solicitudsSubB.clear();
            tablaSubCola.setItems(getSubCola());
            tablaSubCola.refresh();
        }

    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
