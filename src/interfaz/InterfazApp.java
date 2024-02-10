package interfaz;

import controlador.Controlador;
import controlador.ControladorColas;

import java.util.Optional;

import javax.sound.sampled.SourceDataLine;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import mundo.Cola;
import mundo.ListaTempo;
import mundo.Nodo;
import mundo.Solicitud;

/**
 *
 * @author Nilson
 */
public class InterfazApp extends Application
{
    //Componentes esenciales para la app
    private Stage mainVentana;
    private Scene escenario;
    
    private Button btnLocalizarTxT, btnAsignarSoli,btnImprimir,btnCerrar;
    private Label etiquetaTitulo;
    
    //Organizadores
    
    private StackPane orgNormal;
    private HBox orgHorizontal;
    private VBox orgVertical;
    
    private Cola impresoraA, impresoraB, impresoraSubB; //La impresora subB representa la cola de espera de la misma 
    private ListaTempo listaSolicitudes;
    
    
    
    
    
    public InterfazApp()
    {
        System.out.println("Clase principal creada");
  
    }
    
    public InterfazApp(Controlador ctrl)
    {
        System.out.println("El valor que introdujo es"+ctrl.sendDirecction());
        
    }
    
    public InterfazApp(ListaTempo lista)
    {
     
        
        impresoraA = new Cola(); //instanciamiento impresora A
        impresoraB = new Cola();   //instanciamiento impresora B     
        impresoraSubB = new Cola();//instanciamiento cola de espera para B
        listaSolicitudes = lista; 
        
        
    }

    public InterfazApp(Cola impresoraA, Cola impresoraB, Cola impresoraSubB, ListaTempo listaSolicitudes)
    {
        this.impresoraA = impresoraA;
        this.impresoraB = impresoraB;
        this.impresoraSubB = impresoraSubB;
        this.listaSolicitudes = listaSolicitudes;
    }
    
    
    
    
    
    
    
    public static void main(String[] args)
    {
         /* Test de las clases
        Solicitud solicitud = new Solicitud("Gerencia", "Color");
        Solicitud solicitud2 = new Solicitud("Mercadeo", "ByN");
        Solicitud solicitud3 = new Solicitud("Administraccion", "Color");
        Solicitud solicitud4 = new Solicitud("Produccion", "ByN");
        Nodo nodo1 = new Nodo(solicitud);
        Nodo nodo2 = new Nodo(solicitud2);
        Nodo nodo3 = new Nodo(solicitud3);
        Nodo nodo4 = new Nodo(solicitud4);
        Cola colaPrincipal = new  Cola();
        colaPrincipal.encolar(nodo1);
        colaPrincipal.encolar(nodo2); 
        colaPrincipal.encolar(nodo3);
        colaPrincipal.encolar(nodo4);
        colaPrincipal.imprimirCola();
        System.out.println("Contador:"+colaPrincipal.getContador());
        
        colaPrincipal.imprimirCola();
        
        Nodo recopilado = colaPrincipal.desEncolar();
        
        System.out.println("Tipo de trabajo:"+recopilado.getDato().getTipoDeTrabajo());
        System.out.println("Area de trabajo:"+recopilado.getDato().getAreaDeTrabajo());
        
        colaPrincipal.imprimirCola();
        
        colaPrincipal.desEncolar();
        colaPrincipal.desEncolar();
        colaPrincipal.imprimirCola();  
        colaPrincipal.desEncolar();
        colaPrincipal.imprimirCola();
        System.out.println(colaPrincipal.getContador());
        colaPrincipal.primeroEnCola();
        
       colaPrincipal.encolar(nodo4);
       colaPrincipal.primeroEnCola();
       colaPrincipal.imprimirCola();
        */
        
        
        launch(args);
        
    }

    @Override
    public void start(Stage stage) 
    {
        etiquetaTitulo = new Label("Bienvenido al menu de control de impresion\n                             MinuTeach");
        etiquetaTitulo.setTextAlignment(TextAlignment.JUSTIFY);     
        
        btnLocalizarTxT = new Button("Buscar Arhivo");
        
        
        
        btnAsignarSoli = new Button(" Asignar impresiones");
       
        btnImprimir = new Button("Imprimir");
        
        btnCerrar = new Button("Cerrar");
        
        
        orgHorizontal = new HBox();
        orgHorizontal.setAlignment(Pos.CENTER);
        
        orgVertical = new VBox(30);
        orgVertical.setAlignment(Pos.CENTER);
        
        orgNormal = new StackPane();
        orgNormal.setAlignment(Pos.CENTER);
        
        
        orgHorizontal.getChildren().add(etiquetaTitulo);
        orgVertical.getChildren().addAll(orgHorizontal,btnLocalizarTxT,btnAsignarSoli,btnImprimir,btnCerrar);
        orgNormal.getChildren().add(orgVertical);
        
        //Eventos
        
        btnCerrar.setOnAction((t) -> 
        {
            System.exit(0);

        });
        
        btnLocalizarTxT.setOnAction((t) ->
        {
            InterfazLectura ril = new  InterfazLectura();
            
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setContentText("Para poder localizar su archivo recuerde lo siguiente:"
                    + "\n 1.    El archivo debe tener el siguiente nombre Solicitudes.txt"
                    + "\n 2.    El archivo debe estar en una carpeta."
                    + "\n 3.    Verfique que la direccion al final tenga el .txt"
                    + "\n 4.    Al aceptar se le dara un ejemplo, porfavor elimine los \nespacios a la izquiera del ejemplo.");     
            Optional<ButtonType> result = alerta.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK)
                {
                mainVentana.close();
                ril.start(mainVentana);
                }      
        });
        
        btnAsignarSoli.setOnAction((t) ->
        {
            
            try {
                
                System.out.println("1#####");
                ControladorColas organizar = new ControladorColas(listaSolicitudes);
                System.out.println("2######");
                ControladorColas retornado = organizar.AsignarColas();
                
                impresoraA = retornado.getImpresoraA();
                impresoraB = retornado.getImpresoraB();
                impresoraSubB = retornado.getImpresoraEspera();
                
                
                Alert alertaDeAsignacion = new Alert(AlertType.INFORMATION);
                alertaDeAsignacion.setContentText("La asignacion se realizo correctamente segun los requerimientos,"
                                               + " a continuacion observara el numero de solicitudes de impresion para cada impresora,"
                                               + " incluida la cola de espera de la impresora 2. "
                                               + "\n#1 Impresora:"+impresoraA.getContador()+""
                                               + "\n#2 Impresora:"+impresoraB.getContador()+""
                                               + "\nCola de espera:"+impresoraSubB.getContador());
                
                alertaDeAsignacion.show();
                
                //btnAsignarSoli.setDisable(true); Verificar ultimato

                //System.out.println(organizar.getImpresoraA().getInicio().getDato().getAreaDeTrabajo());
                System.out.println("Cola de la impresora #1"); //Segundo test
                System.out.println("");
                impresoraA.imprimirCola();
                System.out.println("Cola de la impresora #2");
                System.out.println("");
                impresoraB.imprimirCola();
                System.out.println("Cola de la impresora Sub");
                System.out.println("");
                impresoraSubB.imprimirCola();
                
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Para realizar esta accion primero debe establecer la direccion de su archivo");
                alert.show();

                System.out.println(e);

            }
            
            
            
            
            
        });
        
        btnImprimir.setOnAction((t) ->{



            try {

            InterfazImprimir rii= new InterfazImprimir(listaSolicitudes,impresoraA,impresoraB,impresoraSubB);
            rii.start(mainVentana);
            mainVentana.close();

            } catch (Exception e) 
            {
                
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Para realizar esta accion, primero debe establecer la direccion de su archivo,"
                                   + " y posteriomente seleccionar el boton [Asignar impresiones], para asignar las solicitudes de manera correcta.");
                alert.show();

            }
            



        });
        
        
        
        
        escenario = new Scene(orgNormal, 450, 300);
        
        mainVentana = new Stage();
        mainVentana.setTitle("MinuTech");
        mainVentana.setResizable(false);
        //  mainVentana.getIcons().add(e); liea opcional
        mainVentana.setScene(escenario);
        stage = mainVentana;
        mainVentana.show();
        
        
        
        
        
    }
    
    
    
}
