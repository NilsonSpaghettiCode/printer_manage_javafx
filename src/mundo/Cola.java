/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

/**
 *
 * @author Nilson
 */
public class Cola
{
    private Nodo inicio;
    private Nodo fin;
    private int contador;
    private int staticoInt;

    
    
    public Cola()
    {
        inicio = null;
        fin = null;
        contador = 0;
        staticoInt=0;  
    }
    
    public static void  main (String []args) //Clase de testeo, no tomar en cuenta.[main]
    {
        Solicitud soli1 = new Solicitud("Administracion", "Color");
        Solicitud soli2 = new Solicitud("Gerencia", "ByN"); 
        
        Nodo nodo1 = new Nodo(soli1);
        Nodo nodo2 = new Nodo(soli2);
        
        Cola cola = new Cola();
        
        cola.encolar(nodo1);
        cola.encolar(nodo2);
        
        cola.imprimirCola();
        
        
        System.out.println(cola.getInicio().getDato().getTipoDeTrabajo());
        
    }
    
    
    
    /**
     * 
     * @return Retorna verdadero si la cola esta vacia
     */
    public boolean colaVacio()
    {
        return inicio == null; 
    }
    
    public void encolar(Nodo dato)
    {
        if(colaVacio())
        {
            inicio = dato;
            fin = dato;
            contador++;
            staticoInt++;
        }else
        {
            dato.setAtras(fin);
            fin.setSiguiente(dato);
            fin = dato;
            contador++;
            staticoInt++; 
        }
    }
    
    public Nodo desEncolar()
    {
        if(colaVacio()){
            System.out.println("La cola esta vacia");
            return null;
        }else if(getContador() == 1)
        {
            Nodo temNodo = inicio;
            inicio = null;
            fin = null;
            contador--;
            

            return temNodo;
        }else{
            Nodo temporaNodo = inicio;
            Nodo nodoRetornado = inicio;
                    
            temporaNodo = temporaNodo.getSiguiente();
            temporaNodo.setAtras(null);
            contador--;
            inicio = temporaNodo;

            nodoRetornado.setSiguiente(null);
            nodoRetornado.setAtras(null);
            return nodoRetornado;
        }
        
        
    
    
    }
    
    
    
    
    public void imprimirCola()
    {
        Nodo recorridoTemporal = inicio;
        
        if(colaVacio())
        {              
            System.out.println("La cola esta Vacia!!!!");
        }else
        {
            System.out.println("Los elementos en la cola son");
            System.out.println("----------------------------");
        for(int i = 0;i < getContador();i++)
        {
            System.out.println("########################");
            System.out.println("Area: "+recorridoTemporal.getDato().getAreaDeTrabajo());
            System.out.println("Tipo de trabajo: "+recorridoTemporal.getDato().getTipoDeTrabajo());
            System.out.println("########################");
            System.out.println("");
            recorridoTemporal = recorridoTemporal.getSiguiente();   
        }
        
        
            
            
            
        }
        
        
        

    }
    public void primeroEnCola()
    {
        if(colaVacio())
        {
            System.out.println("La cola esta vacia");
        }else{     
        System.out.println("#####################");
        System.out.println("Primer elemento en la cola");
        System.out.println("Area: "+inicio.getDato().getAreaDeTrabajo());
        System.out.println("Tipo de trabajo es:"+inicio.getDato().getTipoDeTrabajo()); 
        System.out.println("#####################");
        System.out.println("");
        }  
    }

    
    public int getStaticoInt() {
        return this.staticoInt;
    }

    public void setStaticoInt(int staticoInt) {
        this.staticoInt = staticoInt;
    }






    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    
    
    
    
    
    
}
