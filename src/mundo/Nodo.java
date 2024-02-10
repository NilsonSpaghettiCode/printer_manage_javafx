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
public class Nodo
{
    //Atributos de la clase Nodo
    
    private Nodo siguiente;
    private Solicitud dato;
    private Nodo atras;
    
    //Contructores 
    public Nodo()
    {
        siguiente = null;
        dato = null;
        atras = null;
    }
    public Nodo(Solicitud dato)
    {
        siguiente = null;
        this.dato = dato;
        atras = null;
    }

    
    //Metodos get y set 
    
    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Solicitud getDato() {
        return dato;
    }

    public void setDato(Solicitud dato) {
        this.dato = dato;
    }

    public Nodo getAtras() {
        return atras;
    }

    public void setAtras(Nodo atras) {
        this.atras = atras;
    }
    
    
    
    
}
