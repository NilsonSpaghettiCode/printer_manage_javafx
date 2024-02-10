/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import controlador.Controlador;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Nilson
 */
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mundo.LecturaTXT;
import mundo.ListaTempo;
import mundo.Nodo;
public class InterfazLectura extends Application 
{
    private Stage ventanaLectura;
    private Scene escenario;
    
    private Button btnAtras,btnlocalizar;
    private TextField direccion;
    private Label etiquetaTitulo, etiquetaDireccion;
    
    private VBox orgVericalCentral, orgVertical;
    private StackPane orgNormal;
    private HBox orgHorizontal;
    
    private String direccionObtenida;
    
    private InterfazApp ria ;

    public InterfazLectura()
    {
        
        
        System.out.println("Interfaz De lectura creado");
        
    }
    

    
    
    
    
    
    @Override
    public void start(Stage stage) 
    {
       etiquetaTitulo = new Label("Porfavor Introduzca la direccion del archivo");
       etiquetaDireccion = new Label("Direccion");
       
       btnAtras = new Button("Atras");
       btnlocalizar = new Button("Localizar Archivo");
       
       direccion = new TextField("C:\\Users\\USER\\Desktop\\TXT\\pruebaDeEscritorio.txt");
       
       orgVertical = new VBox(15);
       orgVertical.setAlignment(Pos.CENTER);
       
       orgVericalCentral = new VBox(15);
       orgVericalCentral.setAlignment(Pos.CENTER);
       
       orgNormal = new StackPane();
       orgNormal.setAlignment(Pos.CENTER);
       
       orgHorizontal = new  HBox(25);
       orgHorizontal.setAlignment(Pos.CENTER);
       
       btnAtras.setOnAction((t) ->
       {
           
           ria = new InterfazApp(new Controlador(direccion.getText()));
           
           this.ria.start(stage);
           
           
       });
       
       
       btnlocalizar.setOnAction((t) ->
       {
           direccionObtenida = direccion.getText();
           ListaTempo relaLista = new ListaTempo();
           try
           {
               LecturaTXT lectura = new LecturaTXT();
               int temporal =0;
               
               String solicitudes[] = lectura.leerDireccion(direccionObtenida);
               for (int i = 0; i < solicitudes.length; i++) {
            

            Nodo nuevo = lectura.separar(solicitudes[i]);
            relaLista.añadir(nuevo);
                temporal++;
            
                }
               relaLista.verLista();
               System.out.println("Variables encontradas en txt:"+temporal);
               
               InterfazApp interfaz = new InterfazApp(relaLista);
               ventanaLectura.close();
               
               interfaz.start(stage);
               
 
               /*System.out.println("La direccion del archivo es:"+direccionObtenida);
               String lista ="";
                FileReader busqueda = new FileReader(direccionObtenida);
                BufferedReader br = new BufferedReader(busqueda);
                int contador = 0;
                contador = busqueda.read();
                while(br.readLine() != null){
                lista = lista+br.readLine();
                }
                System.out.println("Los elementos encontramos son:"+lista);
               */ 
               
           } catch (Exception e){
               
               System.out.println("No se encontro el documento");
               
               Alert alertaPeligro = new Alert(Alert.AlertType.WARNING);
               alertaPeligro.setContentText("No se encontro el documento, porfavor verifique la direccion.");
               alertaPeligro.show();
           }
           
           
           
           
       });
       
       
       orgVertical.getChildren().addAll(etiquetaDireccion,direccion);
       orgHorizontal.getChildren().addAll(btnAtras,btnlocalizar);
       orgVericalCentral.getChildren().addAll(etiquetaTitulo,orgVertical,orgHorizontal);
       
       orgNormal.getChildren().addAll(orgVericalCentral);
       
       escenario = new Scene(orgNormal, 400, 200);
       
       ventanaLectura = new Stage();
       ventanaLectura.setTitle("Busqueda de archivo");
       ventanaLectura.setResizable(false);
       ventanaLectura.setScene(escenario);
       ventanaLectura.show();
       
       
       
       
        
        
        
        
    }
    
    
    
    
    
}
