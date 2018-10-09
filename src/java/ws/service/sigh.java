/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import ws.controllers.ctrlConections;
import ws.models.mdlPaciente;
import ws.models.mdlResponse;
import ws.models.mdlSignosVitales;

/**
 * REST Web Service
 *
 * @author felipe de jesus
 */
@Path("sigh")
public class sigh {

    @Context
    private UriInfo context;
    PreparedStatement ps=null;
    /**
     * Creates a new instance of ws
     */
    public sigh() {
    }

    /**
     * Retrieves representation of an instance of ws.service.ws
     * @return an instance of java.lang.String
     */ 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "SiGH-Sistema Integral de Gestión Hospitalaria -Web Services";
    }

    /**
     * PUT method for updating or creating an instance of ws
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {}
    
    @POST
    @Path("/SignosVitales")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public mdlResponse wsSignosVitales(
        @FormParam("sv_temp")String sv_temp,
        @FormParam("sv_fc")String sv_fc,
        @FormParam("sv_fr")String sv_fr,
        @FormParam("sv_sistolica")String sv_sistolica,
        @FormParam("sv_diastolica")String sv_diastolica,
        @FormParam("sv_oximetria")String sv_oximetria,
        @FormParam("sv_glicemia")String sv_glicemia,
        @FormParam("sv_per_abdominal")String sv_per_abdominal,
        @FormParam("sv_talla")String sv_talla,
        @FormParam("ingreso_id")int ingreso_id,
        @FormParam("simef_folio")String simef_folio,
        @FormParam("simef_clave_presupuestal")String simef_clave_presupuestal
    ){
        mdlResponse rs=new mdlResponse();
        mdlSignosVitales sv=new mdlSignosVitales();
        sv.setSv_temp(sv_temp);
        sv.setSv_fc(sv_fc);
        sv.setSv_fr(sv_fr);
        sv.setSv_sistolica(sv_sistolica);
        sv.setSv_diastolica(sv_diastolica);
        sv.setSv_oximetria(sv_oximetria);
        sv.setSv_glicemia(sv_glicemia);
        sv.setSv_per_abdominal(sv_per_abdominal);
        sv.setSv_talla(sv_talla);
        sv.setSimef_folio(simef_folio);
        sv.setSimef_clave_presupuestal(simef_clave_presupuestal);
        try {
            boolean bolUpdate=mdlSignosVitales.updateSignosVitales(sv);
            if(bolUpdate){
                rs.setStrAction("OK");
                rs.setStrMsj("SIGNOS VITALES ACTUALIZADOS");
            }else{
                rs.setStrAction(simef_folio);
                rs.setStrMsj("ERROR AL ACTUALIZAR LOS SIGNOS VITALES");
            } 
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }
    
    @POST
    @Path("/getPaciente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public mdlPaciente wsGetPaciente(@FormParam("folio_simef")String folio_simef){
        mdlPaciente paciente=new mdlPaciente();
        try {
            ResultSet rs=mdlPaciente.getPaciente(folio_simef);
            if(rs.next()){
                paciente.setStrAdmisionFecha(rs.getString("fec_adm"));
                paciente.setStrAdmisionHora(rs.getString("hora_adm"));
                paciente.setStrFolio(rs.getString("folio"));
                paciente.setStrPacienteNombre(rs.getString("nom_der_hab"));
                paciente.setStrRfc(rs.getString("exp_der_hab"));
                paciente.setStrTipoDh(rs.getString("cve_tip_der"));
                paciente.setStrCurp(rs.getString("curp_der_hab"));
                paciente.setStrAction("1");
            }else{
                paciente.setStrAction("2");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            paciente.setStrAction("3");
        }
        return paciente;
    }
            
}
