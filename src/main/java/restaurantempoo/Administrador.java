/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantempoo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase Administrador
 * @author Barrillas González, Hernandez Nieves, Romero Velázquez
 */
public class Administrador implements Restaurante{
    private String usuario;
    private String contrasenia;
/**
 * Constructor vacio
 */
    public Administrador() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
     * Metodo para contar el numero de administradores
     * @return Numero de administradores
     * @throws FileNotFoundException 
     */
    public int numAdmins() throws FileNotFoundException{
        int cont = 0;
        FileReader frA = new FileReader("administradores.csv");
        BufferedReader brA = new BufferedReader(frA);
        Scanner sc = new Scanner(brA);
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        return cont;
    }
    
    /**
     * Metodo que lee los datos de un Administrador
     * @return Lista de administradores
     * @throws FileNotFoundException Excepción
     * @throws IOException Excepcion
     */  
    public Administrador[] leerAdministradores() throws FileNotFoundException, IOException{
        String textoTemporal;
        int cont = numAdmins();
        FileReader frA = new FileReader("administradores.csv");
        BufferedReader brA = new BufferedReader(frA);
        String linea1 = brA.readLine();
        Scanner sc = new Scanner(brA);
        Administrador listaAdministradores[] = new Administrador[cont];
        for (int i = 0; i < listaAdministradores.length; i++){
            StringTokenizer tokenizador1 = new StringTokenizer(linea1, ",");
            int temp = 0;
            listaAdministradores[i] = new Administrador();
            while (tokenizador1.hasMoreTokens()) {
                textoTemporal = tokenizador1.nextToken();
                if (temp == 0)
                    listaAdministradores[i].setUsuario(textoTemporal);
                else if (temp == 1)
                    listaAdministradores[i].setContrasenia(textoTemporal);
                temp++;
            }
            try {
                linea1 = sc.nextLine();
            }catch (java.util.NoSuchElementException ex){}       
        }
        sc.close();
        return listaAdministradores;
    }
    
    @Override
    public void prepararOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void entregarOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void imprimirTicket() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Administrador{" + "usuario=" + usuario + '}';
    }
 
}
