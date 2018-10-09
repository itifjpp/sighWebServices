/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author felipe de jesus
 */
public class ctrlConections {
    /* Funcion que lee un archivo de configuracion de conexion */
    public static String[] leerarchivo(String archivo) {
        try {
            String valores[] = new String[]{"", "", "", "", "","",""};
            FileReader leerarchivo = new FileReader(archivo);
            BufferedReader br = new BufferedReader(leerarchivo);
            String leeli = br.readLine();
            while (leeli != null) {
                int inicio = leeli.indexOf("=");
                if (inicio > 0) {
                    String izqierda = leeli.substring(0, inicio);
                    String valor = leeli.substring(inicio + 1);
                    if (izqierda.equalsIgnoreCase("ip")) {
                        valores[0] = valor;
                    }else if (izqierda.equalsIgnoreCase("puerto")) {
                        valores[1] = valor;
                    }else if (izqierda.equalsIgnoreCase("database")) {
                        valores[2] = valor;
                    }else if (izqierda.equalsIgnoreCase("usuario")) {
                        valores[3] = valor;
                    }else if (izqierda.equalsIgnoreCase("password")) {
                        valores[4] = valor;
                    }else if (izqierda.equalsIgnoreCase("hostname")) {
                        valores[5] = valor;
                    }else if (izqierda.equalsIgnoreCase("instancia")) {
                        valores[6] = valor;
                    }
                    leeli = br.readLine();
                }
            }
            return valores;
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
    public static Connection ConectionMySQL(){
        String x[] = leerarchivo("C:/xampp/htdocs/sigh/assets/conections/mysql.ini");
        System.out.println("Iniciando Conexión ala Base de datos(MySQL)...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://"+x[0]+":"+x[1]+"/"+x[2],x[3], x[4]);
            System.err.println("Conexión ala base de datos(MySQL) exitosa :)");
            return con;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.print("ERROR EN LA CONEXIÓN A LA BASE DE DATOS MySQL");
            return null;
        }
    }
    public static Connection ConectionInformix() {
        String x[] = leerarchivo("C:/xampp/htdocs/sigh/assets/conections/informix.ini");
        System.out.println("Iniciando Conexión ala Base de datos(Informix)...");
        try {
            Class.forName("com.informix.jdbc.IfxDriver");
            //Connection con=DriverManager.getConnection("jdbc:informix-sqli://192.168.51.88:1605/bdsuim","uhzarago","#$45My$ql2018");
            //jdbc:informix-sqli://123.45.67.89:1533/testDB:INFORMIXSERVER=myserver;user=rdtest;password=test
            Connection con=DriverManager.getConnection("jdbc:informix-sqli://"+x[0]+":"+x[1]+"/"+x[2]+":INFORMIXSERVER="+x[6]+";user="+x[3]+";password="+x[4]);
            System.err.println("Conexion ala base de datos(Informix) exitosa");
            return con;
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.err.println("ERROR EN LA CONEXION ALA BASE DE DATOS INFORMIX");
            return null; 
        }
    }
}
