/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ws.controllers.ctrlConections;

/**
 *
 * @author felipe de jesus
 */
public class mdlSignosVitales {
    private int sv_id;
    private String sv_tipo;
    private String sv_fecha;
    private String sv_hora;
    private String sv_temp;
    private String sv_fc;
    private String sv_fr;
    private String sv_sistolica;
    private String sv_diastolica;
    private String sv_oximetria;
    private String sv_dextrostix;
    private String sv_glicemia;
    private String sv_glasgow;
    private String simef_folio;
    private int ingreso_id;
    private String simef_clave_presupuestal;
    private String sv_talla;
    private String sv_per_abdominal;

    public String getSv_talla() {
        return sv_talla;
    }

    public void setSv_talla(String sv_talla) {
        this.sv_talla = sv_talla;
    }

    public String getSv_per_abdominal() {
        return sv_per_abdominal;
    }

    public void setSv_per_abdominal(String sv_per_abdominal) {
        this.sv_per_abdominal = sv_per_abdominal;
    }
    
    public String getSimef_clave_presupuestal() {
        return simef_clave_presupuestal;
    }

    public void setSimef_clave_presupuestal(String simef_clave_presupuestal) {
        this.simef_clave_presupuestal = simef_clave_presupuestal;
    }
    public int getSv_id() {
        return sv_id;
    }

    public String getSimef_folio() {
        return simef_folio;
    }

    public void setSimef_folio(String simef_folio) {
        this.simef_folio = simef_folio;
    }

    public void setSv_id(int sv_id) {
        this.sv_id = sv_id;
    }

    public String getSv_tipo() {
        return sv_tipo;
    }

    public void setSv_tipo(String sv_tipo) {
        this.sv_tipo = sv_tipo;
    }

    public String getSv_fecha() {
        return sv_fecha;
    }

    public void setSv_fecha(String sv_fecha) {
        this.sv_fecha = sv_fecha;
    }

    public String getSv_hora() {
        return sv_hora;
    }

    public void setSv_hora(String sv_hora) {
        this.sv_hora = sv_hora;
    }

    public String getSv_temp() {
        return sv_temp;
    }

    public void setSv_temp(String sv_temp) {
        this.sv_temp = sv_temp;
    }

    public String getSv_fc() {
        return sv_fc;
    }

    public void setSv_fc(String sv_fc) {
        this.sv_fc = sv_fc;
    }

    public String getSv_fr() {
        return sv_fr;
    }

    public void setSv_fr(String sv_fr) {
        this.sv_fr = sv_fr;
    }

    public String getSv_sistolica() {
        return sv_sistolica;
    }

    public void setSv_sistolica(String sv_sistolica) {
        this.sv_sistolica = sv_sistolica;
    }

    public String getSv_diastolica() {
        return sv_diastolica;
    }

    public void setSv_diastolica(String sv_diastolica) {
        this.sv_diastolica = sv_diastolica;
    }

    public String getSv_oximetria() {
        return sv_oximetria;
    }

    public void setSv_oximetria(String sv_oximetria) {
        this.sv_oximetria = sv_oximetria;
    }

    public String getSv_dextrostix() {
        return sv_dextrostix;
    }

    public void setSv_dextrostix(String sv_dextrostix) {
        this.sv_dextrostix = sv_dextrostix;
    }

    public String getSv_glicemia() {
        return sv_glicemia;
    }

    public void setSv_glicemia(String sv_glicemia) {
        this.sv_glicemia = sv_glicemia;
    }

    public String getSv_glasgow() {
        return sv_glasgow;
    }

    public void setSv_glasgow(String sv_glasgow) {
        this.sv_glasgow = sv_glasgow;
    }

    public int getIngreso_id() {
        return ingreso_id;
    }

    public void setIngreso_id(int ingreso_id) {
        this.ingreso_id = ingreso_id;
    }
    /*PETICIONES Y CONEXIONES ALA BASE DE DATOS*/
    static Connection conInformix=(Connection)ctrlConections.ConectionInformix();
    //static Connection conMySQL=(Connection)ctrlConections.ConectionMySQL();
    static PreparedStatement ps=null;
    public static boolean updateSignosVitales(mdlSignosVitales sv)throws SQLException{
        ps=conInformix.prepareStatement("UPDATE tbltriage SET val_temperatura =?, frec_cardiaca =?, frec_respiratoria =?, ten_art_sistolica=?, ten_art_diastolica =?, sat_oxigeno=?, glu_capilar=?, val_per_abdominal =?, val_talla=? WHERE folio=? AND cve_pre_uni_med=?");
        ps.setString(1, sv.getSv_temp());
        ps.setString(2, sv.getSv_fc());
        ps.setString(3, sv.getSv_fr());
        ps.setString(4, sv.getSv_sistolica());
        ps.setString(5, sv.getSv_diastolica());
        ps.setString(6, sv.getSv_oximetria());
        ps.setString(7, sv.getSv_glicemia());
        ps.setString(8, sv.getSv_per_abdominal());
        ps.setString(9, sv.getSv_talla());
        ps.setString(10, sv.getSimef_folio());
        ps.setString(11, sv.getSimef_clave_presupuestal());
        int intSql=ps.executeUpdate();
        conInformix.close();
        ps.close();
        if(intSql>0){
            return true;
        }else{
            return false;
        }
    }
}
