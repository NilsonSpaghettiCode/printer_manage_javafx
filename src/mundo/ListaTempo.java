/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

/**
 *
 * @author 
 */
public class ListaTempo
{
    private Nodo inicio;
    private Nodo fin;
    int contador;
    int contadorStatic;


    
    public ListaTempo ()
    {
    inicio = null;
    fin = null;
    contador = 0; 
    contadorStatic =0;
    }
    public static void main(String[]args) //test Class
    {
        Solicitud solicitud = new Solicitud("Gerencia", "Color");
        Solicitud solicitud2 = new Solicitud("Mercadeo", "ByN");
        Solicitud solicitud3 = new Solicitud("Administraccion", "Color");
        Solicitud solicitud4 = new Solicitud("Produccion", "ByN");
        Nodo nodo1 = new Nodo(solicitud);
        Nodo nodo2 = new Nodo(solicitud2);
        Nodo nodo3 = new Nodo(solicitud3);
        Nodo nodo4 = new Nodo(solicitud4);
        
        
        ListaTempo lista = new ListaTempo();
        
        lista.añadir(nodo1);
        lista.añadir(nodo2);
        lista.añadir(nodo3);
        lista.verLista();
        lista.añadir(nodo4);
        lista.verLista();
        
        
        
        
        
    }
    
    
    
    
    
    /**
     * 
     * @return Retorna verdadero si esta vacia 
     */
    public boolean vacia()
    {
        return inicio == null;
    }
    
    public void añadir(Nodo dato)
    {
        
        if (vacia()) {
            
            inicio = dato;
            fin = dato;
            contador++;
            contadorStatic++;
        } else
        {
            fin.setSiguiente(dato);
            dato.setAtras(fin);
            fin = dato;
            contador++;   
            contadorStatic++;
        }   
    }
    
    public void verLista()
        {
            Nodo tempo = inicio;
            System.out.println("Elementos en la lista");
            System.out.println("---------------------");
            if(vacia()){
                System.out.println("La lista esta vacia"); 
            }else{
            for(int x = 0;x<getContador();x++)
            {
                System.out.println("Area:"+tempo.getDato().getAreaDeTrabajo());
                System.out.println("Tipo de trabajo:"+tempo.getDato().getTipoDeTrabajo());
                System.out.println("");
                tempo = tempo.getSiguiente();
            }
            
            }
            System.out.println("---------------------");
        }
    
     public Nodo desListar()
    {
        if(vacia()){
            System.out.println("La lista esta vacia");
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
    
    
    public int getContadorStatic() {
        return this.contadorStatic;
    }

    public void setContadorStatic(int contadorStatic) {
        this.contadorStatic = contadorStatic;
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
