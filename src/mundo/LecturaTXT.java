/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Nilson
 */
public class LecturaTXT
{
    private FileReader lector;
    private BufferedReader br;
    private String direccion;
    private int n;
    private String palabras[];
    

    public LecturaTXT() 
    {
        
        direccion = "";
        n =0;
    }
    
    public String[] leerDireccion(String direccion) throws FileNotFoundException, IOException
    {
        lector = new FileReader(direccion);
        br = new BufferedReader(lector);
        
        FileReader tempo2 = new FileReader(direccion);
        BufferedReader tempo = new BufferedReader(tempo2);
        
        String x = br.readLine();
        while(x != null){
        x = br.readLine();
             n++;
        }
        String texto[] = new String[n];
        
        for(int c =0;c<n;c++){
            System.out.println("n es"+n);
            System.out.println("contador en "+c);
            texto[c] = tempo.readLine();
            System.out.println(texto[c]);
            
                        
            
        }
        
    
    return texto;
    }

    public Nodo separar(String solicitud)
    {
        String area =""; //ADMON            //ADMON;Color;
        String tipoDeTrabajo=""; //Color
        
        area = solicitud.split(";")[0];
        tipoDeTrabajo = solicitud.split(";")[1];
        
        Nodo solicitudTras = new Nodo(new Solicitud(area, tipoDeTrabajo));
        
        return solicitudTras;
    }
    
    
    
    
    
    
    
    public String[] getPalabras() {
        return palabras;
    }

    public void setPalabras(String[] palabras) {
        this.palabras = palabras;
    }
    
    
    
    
    
    public static void main(String[]args) throws IOException //Test de la clase lectura, no tomar en cuenta.
    {
        String direccion = "C:\\Users\\USER\\Desktop\\TXT\\Test.txt";
        
        LecturaTXT lecturaTXT = new LecturaTXT();
   
        ListaTempo listaAdd = new  ListaTempo();
                
        
        String solicitudes[] =lecturaTXT.leerDireccion(direccion);
        
        for (int i = 0; i < solicitudes.length; i++) {
            
            
            
            Nodo nuevo = lecturaTXT.separar(solicitudes[i]);
            listaAdd.añadir(nuevo);
                    
            
        }
        listaAdd.verLista();
        
        
        Nodo olis = lecturaTXT.separar(solicitudes[0]);
        
        
        System.out.println(olis.getDato().getTipoDeTrabajo());
        for (int i = 0; i < solicitudes.length; i++) {
            
            
            System.out.println("hola");
        }
        
        
        
        
        
        
       /*
            
        try{
        FileReader ola = new FileReader(direccion);
           
            char l = (char)ola.read();
            System.out.println(l);
            int x = 0;
            x = ola.read();
            while(x!=-1){
                
             x = ola.read();
              System.out.println(x);
            }
            
            //n o m b r e
            
        BufferedReader br = new BufferedReader(ola);
        
        
        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        */
        
     /*
       
       
       FileReader lectorASCII = new FileReader(direccion);
       BufferedReader br = new BufferedReader(lectorASCII);
       
        
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        
        
         */ 
        
    }

    public FileReader getLector() {
        return lector;
    }

    public void setLector(FileReader lector) {
        this.lector = lector;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    
    
}
