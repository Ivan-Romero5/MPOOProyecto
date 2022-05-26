/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantempoo;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase Menu
 * @author Barrillas González, Hernandez Nieves, Romero Velázquez
 */
public class Menu extends Cliente {
    private int idPlatillo;
    private String nombrePlatillo;
    private int cantidadPlatillo;
    float precio;
    private String ingredientes;
    
    /**
     * Constructor vacio
     */
    public Menu() {
    }
    
    /**
     * Metodo que muestra los datos de un Menú
     * @return Menú
     */ 
    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public int getCantidadPlatillo() {
        return cantidadPlatillo;
    }

    public void setCantidadPlatillo(int cantidadPlatillo) {
        this.cantidadPlatillo = cantidadPlatillo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    /**
     * Metodo para mostrar un menu
     */
    public void mostrarMenu(){
    }
    
    /**
     * Metodo para envolver platillos
     */
    public void envolverPlatillo(){
        int opcion;
        try {
        Scanner ingresar = new Scanner(System.in);
       
        System.out.println("¿En que deseas envolver tu platillo?\n1. Plastico\n2. Papel\nIngresa tu opcion: ");
        opcion = ingresar.nextInt();
        
        if (opcion == 1){
            System.out.println("Tu pedido se envolvera en Plastico");
        }
        else{
            System.out.println("Tu pedido se envolvera en Papel");
        } 
        }catch(InputMismatchException ex){
        }
    }
    
    /**
     * Metodo que da el numero de la compra
     * @return El numero de la compra
     * @throws IOException Excepción
     */   
    public int numeroCompra() throws IOException{
        int numCompra = 1;
        
        File archivo = new File("orden" + String.valueOf(numCompra) +".csv");
        
        while (archivo.exists()){
            numCompra++;
            archivo = new File("orden" + String.valueOf(numCompra) +".csv");
        }
        return numCompra;
    }
   
    /**
     * Metodo para agregar cubiertos
     */
    public void agregarCubiertos(){
        int opcion;
        try{
            Scanner ingresar = new Scanner(System.in);

            System.out.println("¿Deseas agregar cubiertos?\n1. Si\n2. No\nIngresa tu opcion: ");
            opcion = ingresar.nextInt();

            if (opcion == 1){
                System.out.println("Cubiertos agregados");
            }
            else{
                System.out.println("No se han agregado cubiertos");
            }   
        }catch(InputMismatchException ex){
        }    
    }
  
    @Override
    public String toString() {
        return "Menu{" + "idPlatillo=" + idPlatillo + ", nombrePlatillo=" 
                + nombrePlatillo + ", cantidadPlatillo=" + cantidadPlatillo 
                + ", precio=" + precio + ", ingredientes=" + ingredientes + '}';
    }
    
}
