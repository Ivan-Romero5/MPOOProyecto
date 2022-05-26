/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantempoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Cliente
 * @author Barrillas González, Hernandez Nieves, Romero Velázquez
 */
public class Cliente implements Restaurante {
    private int numeroCliente;
    private String usuario;
    private String contrasenia;
    private String direccion;
    
    /**
     * Constructor vacio
     */
    public Cliente() {
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public void setContrasenia(String contasenia) {
        this.contrasenia = contasenia;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
    
    /**
     * Metodo para obtener el numero de clientes
     * @return numero de clientes
     * @throws FileNotFoundException 
     */
    public int numClientes() throws FileNotFoundException{
        int cont = 0;
        FileReader frA = new FileReader("clientes.csv");
        BufferedReader brA = new BufferedReader(frA);
        Scanner sc = new Scanner(brA);
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        return cont;
    }
    
    /**
     * Metodo que lee los datos de los Cliente
     * @return Datos de los clientes
     * @throws FileNotFoundException Excepción
     * @throws IOException Excepción
     */  
    public Cliente[] leerClientes() throws FileNotFoundException, IOException{
        String textoTemporal;
        int aux = numClientes();
        Cliente listaClientes[] = new Cliente[aux];
        FileReader frA = new FileReader("clientes.csv");
        BufferedReader brA = new BufferedReader(frA);
        String linea1 = brA.readLine();
        try (Scanner sc = new Scanner(brA)) {
            for (int i = 0; i < listaClientes.length; i++){
                StringTokenizer tokenizador1 = new StringTokenizer(linea1, ",");
                int temp = 0;
                listaClientes[i] = new Cliente();
                while (tokenizador1.hasMoreTokens()) {
                    textoTemporal = tokenizador1.nextToken();
                    if (temp == 0)
                        listaClientes[i].setNumeroCliente(Integer.parseInt(textoTemporal));
                    else if (temp == 1)
                        listaClientes[i].setUsuario(textoTemporal);
                    else if (temp == 2)
                        listaClientes[i].setContrasenia(textoTemporal);
                    else if (temp == 3)
                        listaClientes[i].setDireccion(textoTemporal);
                    temp++;
                }
                try {
                    linea1 = sc.nextLine();
                }catch (java.util.NoSuchElementException ex){}
            }
        } catch (NullPointerException ex){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    /**
     * Metodo que registra un nuevo cliente
     * @return Nuevo cliente
     * @throws IOException Excepción
     */   
    public Cliente registrarCliente() throws IOException{
        int cont = numClientes();
        
        Cliente nvoCliente = new Cliente();
        
        Scanner ingresar = new Scanner(System.in);
        
        nvoCliente.setNumeroCliente(cont+1);
        
        System.out.println("Bienvenido, por favor ingresa los datos que se te "
                + "solicitan.");
        
        ingresar.useDelimiter("\n");
        System.out.println("Ingrese su nombre (de usuario): ");
        String nombre = ingresar.next();
        nvoCliente.setUsuario(nombre);
        System.out.println("Ingrese su contraseña: ");
        String nvaContrasenia = ingresar.next();
        nvoCliente.setContrasenia(nvaContrasenia);
        System.out.println("Ingresa tu direccion para envios: ");
        String direccion = ingresar.next();
        nvoCliente.setDireccion(direccion);
        return nvoCliente;
    }
    
    /**
     * Metodo que guarda los datos d eun nuevo cliente
     * @param cliente Nuevo cluiente a registrar
     * @throws IOException 
     */   
    public void guardarCambios(Cliente cliente) throws IOException{
        int aux = numClientes();
        Cliente clientes[] = new Cliente[aux];
        clientes = cliente.leerClientes();
        
        try (FileWriter fw = new FileWriter("clientes.csv")) {
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                try {
                    for (int i = 0; i < aux; i++) {
                        salida.println(clientes[i].getNumeroCliente() + "," + clientes[i].getUsuario() + "," + clientes[i].getContrasenia() + "," + clientes[i].getDireccion());
                    }
                } catch(NullPointerException ex){
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                salida.println(cliente.getNumeroCliente() + "," + cliente.getUsuario() + "," + cliente.getContrasenia() + "," + cliente.getDireccion());
                salida.close();
                }
        
    }
            
    /**
     * Metodo para solicitar los menus
     */
    public void solicitarMenu(){
        int opcion;
        ComidaMexicana menuMexico = new ComidaMexicana();
        ComidaItaliana menuItalia = new ComidaItaliana();
        ComidaJaponesa menuJapon = new ComidaJaponesa();
        do{
            opcion = desplegarMenu("Selecciona el menu que deseas ver\n1. Comida Mexicana\n2. Comida Italiana\n3. Comida Japonesa\n4. Salir\nIngresa tu opcion: ", 4);
            switch(opcion){
                case 1:
                menuMexico.mostrarMenu();
                break;
            
            case 2:
                menuItalia.mostrarMenu();
                break;
            
            case 3:
                menuJapon.mostrarMenu();
                break;
            }
        }while(opcion != 4);
    }
    
    /**
     * Metodo que indica el numero de platillos ordenados
     * @return Numero de platillos ordenados
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int numPlatillos() throws FileNotFoundException, IOException{
        int cont = 0, numOrden;
        Menu menu = new Menu();
        numOrden = menu.numeroCompra() - 1;
        FileReader frA = new FileReader("orden" + numOrden + ".csv");
        BufferedReader brA = new BufferedReader(frA);
        Scanner sc = new Scanner(brA);
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        return cont;
    }
    
    /**
     * Metodo para crear el archivo de una nueva orden
     * @return Platillos prdenados
     * @throws FileNotFoundException Excepcion
     * @throws IOException Excepcion
     */   
    public Menu[] ordenes() throws FileNotFoundException, IOException{
        String textoTemporal;
        Menu ordenado[] = new Menu[numPlatillos()];
        Menu menu = new Menu();
        int numOrden = menu.numeroCompra() - 1;
        FileReader frA = new FileReader("orden" + String.valueOf(numOrden) + ".csv");
        BufferedReader brA = new BufferedReader(frA);
        String linea1 = brA.readLine();
        Scanner sc = new Scanner(brA);
        for (int i = 0; i < ordenado.length; i++){
            StringTokenizer tokenizador1 = new StringTokenizer(linea1, ",");
            int temp = 0;
            ordenado[i] = new ComidaJaponesa();
            while (tokenizador1.hasMoreTokens()) {
                textoTemporal = tokenizador1.nextToken();
                if (temp == 0)
                    ordenado[i].setIdPlatillo(Integer.parseInt(textoTemporal));
                else if (temp == 1)
                    ordenado[i].setNombrePlatillo(textoTemporal);
                else if (temp == 2)
                    ordenado[i].setCantidadPlatillo(Integer.parseInt(textoTemporal));
                else if (temp == 3)
                    ordenado[i].setPrecio(Float.parseFloat(textoTemporal));
                else if (temp == 4)
                    ordenado[i].setIngredientes(textoTemporal);
                temp++;
            }
            try {
                linea1 = sc.nextLine();
            }catch (java.util.NoSuchElementException ex){}       
        }
        sc.close();
        return ordenado;
    }
    
    /**
     * Metodo que guarda los cambios de una orden
     * @param menu Menu de platillos ordenados
     * @throws IOException Excepcion
     */   
    public void guardarCambiosOrden(Menu menu) throws IOException{
        int aux = numPlatillos();
        Menu ordenado[] = new Menu[numPlatillos()];
        Cliente ordenes = new Cliente();
        ordenado = ordenes.ordenes();
        
        int numOrden = menu.numeroCompra() - 1;
        
        try (FileWriter fw = new FileWriter("orden" + String.valueOf(numOrden) + ".csv")){
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                try{
                    for (int i = 0; i < aux; i++) {
                        salida.println(ordenado[i].getIdPlatillo() + "," + ordenado[i].getNombrePlatillo() + "," + ordenado[i].getCantidadPlatillo() + "," + ordenado[i].getPrecio() + "," + ordenado[i].getIngredientes());
                    }
                    
                } catch (NullPointerException ex){
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                salida.println(menu.getIdPlatillo() + "," + menu.getNombrePlatillo() + "," + menu.getCantidadPlatillo() + "," + menu.getPrecio() + "," + menu.getIngredientes());
                salida.close();
        }
    }
    
    /**
     * Metodo para elegir platillos
     * @throws FileNotFoundException Excepción
     * @throws IOException Excepcion Excepción
     */   
    public void elegirPlatillo() throws FileNotFoundException, IOException{
        int opcion, numPlatillo, cantidad, cantPlat;
        Scanner ingresar = new Scanner(System.in);
       
        ComidaMexicana menuMexico = new ComidaMexicana();
        ComidaItaliana menuItalia = new ComidaItaliana();
        ComidaJaponesa menuJapon = new ComidaJaponesa();
        
        Menu orden = new Menu();
        
        ComidaMexicana platillosMexicanos[] = new ComidaMexicana[menuMexico.numPlatillos()];
        ComidaItaliana platillosItalianos[] = new ComidaItaliana[menuItalia.numPlatillos()];
        ComidaJaponesa platillosJaponeses[] = new ComidaJaponesa[menuJapon.numPlatillos()];
        
        platillosMexicanos = menuMexico.menuMexicana();
        platillosItalianos = menuItalia.menuItaliana();
        platillosJaponeses = menuJapon.menuJaponesa();
        
        do{
            opcion = desplegarMenu("¿De que menu deseas ordenar?\n1. Comida Mexicana\n2. Comida Italiana\n3. Comida Japonesa\n4. Terminar Orden\nIngresa tu opcion: ", 4);
            switch(opcion){
                case 1:
                    System.out.println("Ingresa el numero del platillo que deseas ordenar");
                    numPlatillo = ingresar.nextInt();
                    numPlatillo--;
                    System.out.println("Ingresa la cantidad de platillos que deseas: ");
                    cantidad = ingresar.nextInt();
                    cantPlat = platillosMexicanos[numPlatillo].getCantidadPlatillo();
                    if (cantidad <= cantPlat){
                        orden = platillosMexicanos[numPlatillo];
                        orden.setCantidadPlatillo(cantidad);
                        guardarCambiosOrden(orden);
                        platillosMexicanos[numPlatillo].setCantidadPlatillo(cantPlat - cantidad);
                        menuMexico.guardarCambios(platillosMexicanos);
                    }
                    else{
                        System.out.println("Rebasaste el limite de platillos disponibles, intenta nuevamemnte");
                    }
                break;
            
            case 2:
                System.out.println("Ingresa el numero del platillo que deseas ordenar");
                    numPlatillo = ingresar.nextInt();
                    numPlatillo--;
                    System.out.println("Ingresa la cantidad de platillos que deseas: ");
                    cantidad = ingresar.nextInt();
                    cantPlat = platillosItalianos[numPlatillo].getCantidadPlatillo();
                    if (cantidad <= cantPlat){
                        orden = platillosItalianos[numPlatillo];
                        orden.setCantidadPlatillo(cantidad);
                        guardarCambiosOrden(orden);
                        platillosItalianos[numPlatillo].setCantidadPlatillo(cantPlat - cantidad);
                        menuItalia.guardarCambios(platillosItalianos);
                    }
                    else{
                        System.out.println("Rebasaste el limite de platillos disponibles, intenta nuevamemnte");
                    }
                break;
            
            case 3:
                System.out.println("Ingresa el numero del platillo que deseas ordenar");
                    numPlatillo = ingresar.nextInt();
                    numPlatillo--;
                    System.out.println("Ingresa la cantidad de platillos que deseas: ");
                    cantidad = ingresar.nextInt();
                    cantPlat = platillosJaponeses[numPlatillo].getCantidadPlatillo();
                    if (cantidad <= cantPlat){
                        orden = platillosJaponeses[numPlatillo];
                        orden.setCantidadPlatillo(cantidad);
                        guardarCambiosOrden(orden);
                        platillosJaponeses[numPlatillo].setCantidadPlatillo(cantPlat - cantidad);
                        menuJapon.guardarCambios(platillosJaponeses);
                    }
                    else{
                        System.out.println("Rebasaste el limite de platillos disponibles, intenta nuevamemnte");
                    }
                break;
            }
        }while(opcion != 4);
    }
    
    /**
     * Metodo que muestra el total a pagar de la orden
     * @return Cantidad a pagar
     * @throws FileNotFoundException Exception
     * @throws IOException Exception
     */   
    public float cantidadAPagar() throws FileNotFoundException, IOException{
        Menu orden = new Menu();
        Menu costoOrden[] = new Menu[numPlatillos()];
        int cantidadPlatillo;
        float precioPlatillo, costoFinal = 0;
        
        costoOrden = orden.ordenes();
        for (int i = 0; i < numPlatillos(); i++) {
            cantidadPlatillo = costoOrden[i].getCantidadPlatillo();
            precioPlatillo = costoOrden[i].getPrecio();
            costoFinal = costoFinal + (cantidadPlatillo * precioPlatillo); 
        }
        
        return costoFinal;
    }
    
    /**
     * Metodo que genera un nuevo ticket de compra
     * @param usuario Usuario que compra
     * @param direccion Dirección del usuario
     * @throws FileNotFoundException Exception
     * @throws IOException Excepcion Exception
     */    
    public void generarTicket(String usuario, String direccion) throws FileNotFoundException, IOException{
        
        Menu menu = new Menu();
        int numOrden = menu.numeroCompra() - 1;
        File archivo = new File("TicketCompra" + String.valueOf(numOrden) + ".txt");
        archivo.createNewFile();
        
        int aux = numPlatillos();
        Menu ordenado[] = new Menu[numPlatillos()];
        Cliente ordenes = new Cliente();
        ordenado = ordenes.ordenes();
            
        try (FileWriter fw = new FileWriter("TicketCompra" + String.valueOf(numOrden) + ".txt")){
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                int cantidad;
                float precio, precioPorPlatillo;
                String espacios;
                
                Date hoy = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                
                salida.println("\t----Ticket de compra Restaurante MPOO----\n\n");
                salida.println("Compra realizada por " + usuario);
                salida.println(formatoFecha.format(hoy) + "\t" + hoy.getHours() + ":" + hoy.getMinutes() + ":" + hoy.getSeconds());
                salida.println("Direccion de entrega: " + direccion + "\n");
                salida.println("Resumen de la compra:");
                
                try{
                    for (int i = 0; i < aux; i++) {
                        cantidad = ordenado[i].getCantidadPlatillo();
                        precio = ordenado[i].getPrecio();
                        precioPorPlatillo = cantidad * precio;
                        salida.println(ordenado[i].getNombrePlatillo());
                        salida.println(cantidad + "\tx\t$" + precio + "\t=\t$" + precioPorPlatillo);
                    }
                } catch (NullPointerException ex){
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                salida.println("\n\n\nTotal a pagar: $" + cantidadAPagar());
                salida.close();
        }
        
    }
    
    /**
     * Metodo para desplegar Menus
     * @param texto Texto del menu
     * @param n Opcion elegida
     * @return Texto y n proporcionados al Cliente
     */
    private int desplegarMenu(String texto, int n) {
        int op;
        try{
        Scanner opcion = new Scanner(System.in);
        
        do{
            System.out.println(texto);
            op = opcion.nextInt();
            if (op < 1 || op > n)
                System.out.println("Opcion Invalida");
        } while(op < 1 || op > n);
                return op;
        }catch(InputMismatchException ex){
        }
        return 0;
    }
   
    @Override
    public void prepararOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void entregarOrden() {
        System.out.println("¡La orden esta en camino!");
    }

    @Override
    public void imprimirTicket() {
        System.out.println("Imprimiendo ticket...");
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "numeroCliente=" + numeroCliente + ", usuario=" + usuario + ", direccion=" + direccion + '}';
    }
    
}
