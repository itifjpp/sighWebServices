/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ws.controllers.ctrlConections;

/**
 *
 * @author felipe de jesus
 */
public class mdlPaciente {

    private String strFolio;
    private String strAdmisionFecha;
    private String strAdmisionHora;
    private String strPacienteNombre;
    private String strRfc;
    private String strTipoDh;
    private String strCurp;
    private String strAction;

    public String getStrFolio() {
        return strFolio;
    }

    public void setStrFolio(String strFolio) {
        this.strFolio = strFolio;
    }

    public String getStrAdmisionFecha() {
        return strAdmisionFecha;
    }

    public void setStrAdmisionFecha(String strAdmisionFecha) {
        this.strAdmisionFecha = strAdmisionFecha;
    }

    public String getStrAdmisionHora() {
        return strAdmisionHora;
    }

    public void setStrAdmisionHora(String strAdmisionHora) {
        this.strAdmisionHora = strAdmisionHora;
    }

    public String getStrPacienteNombre() {
        return strPacienteNombre;
    }

    public void setStrPacienteNombre(String strPacienteNombre) {
        this.strPacienteNombre = strPacienteNombre;
    }

    public String getStrRfc() {
        return strRfc;
    }

    public void setStrRfc(String strRfc) {
        this.strRfc = strRfc;
    }

    public String getStrTipoDh() {
        return strTipoDh;
    }

    public void setStrTipoDh(String strTipoDh) {
        this.strTipoDh = strTipoDh;
    }

    public String getStrCurp() {
        return strCurp;
    }

    public void setStrCurp(String strCurp) {
        this.strCurp = strCurp;
    }

    public String getStrAction() {
        return strAction;
    }

    public void setStrAction(String strAction) {
        this.strAction = strAction;
    }
    
    /*PETICIONES Y CONEXIONES ALA BASE DE DATOS*/
    static Connection conInformix=(Connection)ctrlConections.ConectionInformix();
    //static Connection conMySQL=(Connection)ctrlConections.ConectionMySQL();
    static PreparedStatement ps=null;
    
    public static ResultSet getPaciente(String strFolio)throws SQLException{
        ps=conInformix.prepareStatement("SELECT * FROM sigh_admon_simef WHERE folio=?");
        ps.setString(1, strFolio);
        ResultSet rs=ps.executeQuery();
        return rs;
    }
}
