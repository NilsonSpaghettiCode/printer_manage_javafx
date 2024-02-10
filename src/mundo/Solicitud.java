/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

/**
 *
 * @author USER
 */
public class Solicitud 
{
    
    //Atributos de la clase
    private String areaDeTrabajo;
    private String tipoDeTrabajo; //Podria ser booleano

    
    //Constructores 
    public Solicitud(String areaDeTrabajo, String tipoDeTrabajo)
    {
        this.areaDeTrabajo = areaDeTrabajo;
        this.tipoDeTrabajo = tipoDeTrabajo;
    }

    public Solicitud()
    {
        
    }

    //Metodos get y set de la clase
    
    public String getAreaDeTrabajo() 
    {
        return areaDeTrabajo;
    }

    public void setAreaDeTrabajo(String areaDeTrabajo) 
    {
        this.areaDeTrabajo = areaDeTrabajo;
    }

    public String getTipoDeTrabajo() 
    {
        return tipoDeTrabajo;
    }

    public void setTipoDeTrabajo(String tipoDeTrabajo) 
    {
        this.tipoDeTrabajo = tipoDeTrabajo;
    }  
}
