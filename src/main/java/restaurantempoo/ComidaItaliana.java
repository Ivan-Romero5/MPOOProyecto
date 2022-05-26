/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantempoo;

import com.mycompany.mpooproyecto.Proyecto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Comida Italiana
 * @author Barrillas González, Hernandez Nieves, Romero Velázquez    
 */
public class ComidaItaliana extends Menu{
    private int idPlatillo;
    private String nombrePlatillo;
    private int cantidadPlatillo;
    private float precio;
    private String ingredientes;

    /**
    * Constructor Vacio
    */   
    public ComidaItaliana() {
    }
    
    /**
    * Metodo para saber cuantos platillos hay en el menu
    * @return Numero de platillos
    * @throws FileNotFoundException Excepción
    */
    public int numPlatillos() throws FileNotFoundException{
        int cont = 0;
        FileReader frA = new FileReader("menuItaliano.csv");
        BufferedReader brA = new BufferedReader(frA);
        Scanner sc = new Scanner(brA);
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        return cont;
    }
    
    /**
     * Metodo para obtener el menu de comida Italiana
     * @return Menu de ocmida italiana
     * @throws FileNotFoundException Excepción
     * @throws IOException Excepción
     */   
    public ComidaItaliana[] menuItaliana() throws FileNotFoundException, IOException{
        int aux;
        aux = numPlatillos();
        String textoTemporal;
        ComidaItaliana listaItaliana[] = new ComidaItaliana[aux];
        FileReader frA = new FileReader("menuItaliano.csv");
        BufferedReader brA = new BufferedReader(frA);
        String linea1 = brA.readLine();
        Scanner sc = new Scanner(brA);
        for (int i = 0; i < listaItaliana.length; i++){
            StringTokenizer tokenizador1 = new StringTokenizer(linea1, ",");
            int temp = 0;
            listaItaliana[i] = new ComidaItaliana();
            while (tokenizador1.hasMoreTokens()) {
                textoTemporal = tokenizador1.nextToken();
                if (temp == 0)
                    listaItaliana[i].setIdPlatillo(Integer.parseInt(textoTemporal));
                else if (temp == 1)
                    listaItaliana[i].setNombrePlatillo(textoTemporal);
                else if (temp == 2)
                    listaItaliana[i].setCantidadPlatillo(Integer.parseInt(textoTemporal));
                else if (temp == 3)
                    listaItaliana[i].setPrecio(Float.parseFloat(textoTemporal));
                else if (temp == 4)
                    listaItaliana[i].setIngredientes(textoTemporal);
                temp++;
            }
            try {
                linea1 = sc.nextLine();
            }catch (java.util.NoSuchElementException ex){}       
        }
        sc.close();
        return listaItaliana;
    }
   
    @Override
    public void mostrarMenu(){
        try {
            int aux;
            aux = numPlatillos();
            ComidaItaliana menuItaliano = new ComidaItaliana();
            ComidaItaliana menu[] = new ComidaItaliana[aux];
            menu = menuItaliano.menuItaliana();
            System.out.println("Menu de comida Mexicana");
            for (int i = 0; i < aux; i++) {
                System.out.println("Platillo " + menu[i].getIdPlatillo() + ": " + menu[i].getNombrePlatillo());
                System.out.println("Ingredientes: " + menu[i].getIngredientes());
                System.out.println("Precio: " + menu[i].getPrecio());
                System.out.println("Disponibles: " + menu[i].getCantidadPlatillo() + "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComidaMexicana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComidaMexicana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para guardar cambios en el menu
     * @param menu Menu de comida Italiana
     */
    public void guardarCambios(ComidaItaliana menu[]){
        try {
            int aux = numPlatillos();
            try {
                FileWriter fw = new FileWriter("menuItaliano.csv");
                BufferedWriter bw = new BufferedWriter(fw);
                try (PrintWriter salida = new PrintWriter(bw)) {
                    for (int i = 0; i < aux; i++) {
                        salida.println(menu[i].getIdPlatillo() + "," + menu[i].getNombrePlatillo() + "," + menu[i].getCantidadPlatillo() + "," + menu[i].getPrecio() + "," + menu[i].getIngredientes());
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComidaItaliana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para cambiar un platillo y sus ingredientes
     * @param numPlatillo Numero del platillo a cambiar
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void cambiarPlatilloEIngredientes(int numPlatillo) throws FileNotFoundException, IOException{
        numPlatillo--;
        Scanner ingresar = new Scanner(System.in);
        ingresar.useDelimiter("\n");
        int aux = numPlatillos();
        String texto;
        ComidaItaliana menuItalia = new ComidaItaliana();
        ComidaItaliana menu[] = new ComidaItaliana[aux];
        menu = menuItalia.menuItaliana();
        System.out.println("Ingresa el nuevo nombre del platillo: ");
        texto = ingresar.next();
        menu[numPlatillo].setNombrePlatillo(texto);
        System.out.println("Ingresa los ingredientes del nuevo platillo: ");
        texto = ingresar.next();
        menu[numPlatillo].setIngredientes(texto);
        guardarCambios(menu);
    }

    /**
     * Metodo para cambiar la cantidad de un platillo
     * @param numPlatillo Numero del platillo a cambiar
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void cambiarCantidadDePlatillo(int numPlatillo) throws FileNotFoundException, IOException{
        numPlatillo--;
        Scanner ingresar = new Scanner(System.in);
        int aux = numPlatillos(), cantidad, op;
        ComidaItaliana menuItalia = new ComidaItaliana();
        ComidaItaliana menu[] = new ComidaItaliana[aux];
        menu = menuItalia.menuItaliana();
        System.out.println("¿En cuantas unidades deseas disminuir o aumentar la cantidad de platillos?");
        cantidad = ingresar.nextInt();
        System.out.println("¿Que deseas hacer?\n1. Disminuir\n2. Aumentar\nIngresa tu opcion: ");
        op = ingresar.nextInt();
        if (op == 1){
            cantidad = menu[numPlatillo].getCantidadPlatillo() - cantidad;
            menu[numPlatillo].setCantidadPlatillo(cantidad);
        } 
        else if (op == 2){
            cantidad = menu[numPlatillo].getCantidadPlatillo() + cantidad;
            menu[numPlatillo].setCantidadPlatillo(cantidad);
        }
        guardarCambios(menu);
    }

    /**
     * Metodo para cambiar el precio de un platillo
     * @param numPlatillo Numero de Platillo 
     * @throws FileNotFoundException Excepción
     * @throws IOException Excepción
     */   
        public void cambiarPrecio(int numPlatillo) throws FileNotFoundException, IOException{
        numPlatillo--;
        Scanner ingresar = new Scanner(System.in);
        int aux = numPlatillos();
        float precioNuevo;
        ComidaItaliana menuItalia = new ComidaItaliana();
        ComidaItaliana menu[] = new ComidaItaliana[aux];
        menu = menuItalia.menuItaliana();
        System.out.println("Ingresa el nuevo precio del platillo: ");
        precioNuevo = ingresar.nextFloat();
        menu[numPlatillo].setPrecio(precioNuevo);
        guardarCambios(menu);
    }
  
    @Override
    public int getIdPlatillo() {
        return idPlatillo;
    }

    @Override
    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    @Override
    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    @Override
    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    @Override
    public int getCantidadPlatillo() {
        return cantidadPlatillo;
    }

    @Override
    public void setCantidadPlatillo(int cantidadPlatillo) {
        this.cantidadPlatillo = cantidadPlatillo;
    }

    @Override
    public float getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "ComidaItaliana{" + "idPlatillo=" + idPlatillo 
                + ", nombrePlatillo=" + nombrePlatillo + ", cantidadPlatillo=" 
                + cantidadPlatillo + ", precio=" + precio + ", ingredientes=" 
                + ingredientes + '}';
    }
    
}
