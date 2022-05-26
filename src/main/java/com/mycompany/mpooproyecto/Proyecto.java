/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mpooproyecto;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import restaurantempoo.Administrador;
import restaurantempoo.Cliente;
import restaurantempoo.ComidaItaliana;
import restaurantempoo.ComidaJaponesa;
import restaurantempoo.ComidaMexicana;
import restaurantempoo.Menu;

/**
 * Clase Principal
 * @author Barrillas González, Hernandez Nieves, Romero Velázquez
 */
public class Proyecto {
    public static void main(String[] args) throws IOException {
        
        String usuario, contrasenia;
        int opcion, aux, numPlatillo;
        
        Scanner ingresar = new Scanner(System.in);

        Cliente cliente = new Cliente();
        Cliente clientes[] = new Cliente[cliente.numClientes()];
        
        ComidaMexicana menuMexico = new ComidaMexicana();
        ComidaItaliana menuItalia = new ComidaItaliana();
        ComidaJaponesa menuJapon = new ComidaJaponesa();
        
        ComidaMexicana platillosMexicanos[] = new ComidaMexicana[menuMexico.numPlatillos()];
        ComidaItaliana platillosItalianos[] = new ComidaItaliana[menuItalia.numPlatillos()];
        ComidaJaponesa platillosJaponeses[] = new ComidaJaponesa[menuJapon.numPlatillos()];
        
        platillosMexicanos = menuMexico.menuMexicana();
        platillosItalianos = menuItalia.menuItaliana();
        platillosJaponeses = menuJapon.menuJaponesa();
        
        Menu menu = new Menu();
        
        do{
            System.out.println("Bienvenido a Restaurante MPOO");
            opcion = desplegarMenu("¿Eres un cliente o un administrador?\n1. Cliente\n2. Administrador\n3. Salir\nIngresa tu opcion: ", 3);
            switch(opcion){
                case 1:
                    do{
                        opcion = desplegarMenu("¿Que deseas hacer?\n1. Ingresar\n2. Registrarte\n3. Regresar\nIngresa tu opcion: ", 3);
                        switch(opcion){
                            case 1:
                                clientes = cliente.leerClientes();
                                System.out.println("Para ingresar verifica tu identidad.");
                                System.out.println("Ingresa tu nombre de usuario: ");
                                usuario = ingresar.next();
                                                                
                                for (int i = 0; i < cliente.numClientes(); i++) {                                    
                                    if (usuario.equals(clientes[i].getUsuario())){
                                        System.out.println("Hola " + usuario + "\nPor favor ingresa tu contraseña: ");
                                        String direccion = clientes[i].getDireccion();
                                        contrasenia = ingresar.next();
                                        while(contrasenia.equals(clientes[i].getContrasenia()) != true) {
                                            System.out.println("Ingresa tu contraseña nuevamente: ");
                                            contrasenia = ingresar.next();
                                        } 
                                    if (contrasenia.equals(clientes[i].getContrasenia())){
                                        int numCompra = menu.numeroCompra();
                                        File archivo = new File("orden" + String.valueOf(numCompra) +".csv");
                                        archivo.createNewFile();
                                        System.out.println("Bienvenido de nuevo " + usuario);
                                        do{
                                            opcion = desplegarMenu("¿Que deseas hacer?\n1. Ver menu\n2. Ordenar\n3. Pagar\n4. Terminar pedido\nIngresa tu opcion: ", 4);
                                            switch(opcion){
                                                case 1:
                                                    cliente.solicitarMenu();
                                                    break;
                                                        
                                                case 2:
                                                    cliente.elegirPlatillo();
                                                    break;
                                                        
                                                case 3:
                                                    float costoFinal = cliente.cantidadAPagar();
                                                    System.out.println("El costo final de tu compra es de $" + costoFinal);
                                                    break;
                                            }
                                        }while(opcion != 4);
                                    }
                                    menu.envolverPlatillo();
                                    menu.agregarCubiertos();
                                    cliente.generarTicket(usuario, direccion);
                                    }
                                    else{
                                        System.out.print("");
                                    }
                                }
                                break;
                            

                            case 2:
                                cliente.guardarCambios(cliente.registrarCliente());
                                System.out.println("Registro exitoso, por favor inicia sesion para continuar");
                                break;
                        }
                        
                    }while(opcion != 3);
                    break;

                
                case 2:
                    Administrador admin = new Administrador();
                    Administrador admins[] = new Administrador[admin.numAdmins()];
                    admins = admin.leerAdministradores();
                    String mensaje = "Ingresa ID el platillo a cambiar (numero de platillo): ";
                    String mensaje2 = "Cambio realizado";
                    
                    System.out.println("Para ingresar como administrador verifica tu identidad.");
                    System.out.println("Ingresa tu nombre de usuario de administrador: ");
                    usuario = ingresar.next();
                    for (int i = 0; i < admin.numAdmins(); i++) {
                        if (usuario.equals(admins[i].getUsuario())){
                            System.out.println("Hola " + usuario + "\nPor favor ingresa tu contraseña: ");
                            contrasenia = ingresar.next();
                            while(contrasenia.equals(admins[i].getContrasenia()) != true) {
                                System.out.println("Ingresa tu contraseña nuevamente: ");
                                contrasenia = ingresar.next();
                            } 
                            if (contrasenia.equals(admins[i].getContrasenia())){
                                System.out.println("Bienvenido de nuevo " + usuario + " ¿Que deseas hacer hoy?");
                                do{
                                    opcion = desplegarMenu("1. Cambiar platillo e ingredientes\n2. Cambiar cantidad del platillo\n3. Cambiar precio del platillo\n4. Cerrar sesion\nIngresa tu opcion: ", 4);
                                    switch(opcion){
                                        case 1:
                                            do{
                                                aux = desplegarMenu("¿De que menu deseas cambiar el platillo e ingredientes?\n1. Mexicano\n2. Italiano\n3. Japones\n4. Cancelar operacion\nIngresa tu opcion: ", 4);
                                                switch (aux) {
                                                    case 1:
                                                        menuMexico.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuMexico.cambiarPlatilloEIngredientes(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 2:
                                                        menuItalia.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuItalia.cambiarPlatilloEIngredientes(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 3:
                                                        menuJapon.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuJapon.cambiarPlatilloEIngredientes(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                            }
                                            }while (aux != 4);
                                            break;
                                            
                                        case 2:
                                            do{
                                                aux = desplegarMenu("¿De que menu deseas cambiar la cantidad del platillo?\n1. Mexicano\n2. Italiano\n3. Japones\n4. Cancelar operacion\nIngresa tu opcion: ", 4);
                                                switch (aux) {
                                                    case 1:
                                                        menuMexico.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuMexico.cambiarCantidadDePlatillo(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 2:
                                                        menuItalia.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuItalia.cambiarCantidadDePlatillo(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 3:
                                                        menuJapon.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuJapon.cambiarCantidadDePlatillo(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                }
                                            }while(aux != 4);
                                            break;
                                            
                                        case 3:
                                            do{
                                                aux = desplegarMenu("¿De que menu deseas cambiar el precio del platillo?\n1. Mexicano\n2. Italiano\n3. Japones\n4. Cancelar operacion\nIngresa tu opcion: ", 4);
                                                switch (aux) {
                                                    case 1:
                                                        menuMexico.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuMexico.cambiarPrecio(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 2:
                                                        menuItalia.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuItalia.cambiarPrecio(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 3:
                                                        menuJapon.mostrarMenu();
                                                        System.out.println(mensaje);
                                                        numPlatillo = ingresar.nextInt();
                                                        menuJapon.cambiarPrecio(numPlatillo);
                                                        System.out.println(mensaje2);
                                                        break;
                                                }
                                            } while(aux != 4);
                                            break;

                                    }
                                }while(opcion != 4);
                            }
                        }
                        else{
                            System.out.print("");
                        }
                    }
                break;
            }
        }while(opcion != 3);
        System.out.println("Gracias por usar nuestra aplicacion, recomiendenos");
    }
    
    /**
     * Metodo para desplegar un menu de opciones
     * @param texto texto del menu
     * @param n numero de opcion
     * @return 
     */
    private static int desplegarMenu(String texto, int n) {
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
    
}
