package com.ejercicios.BS2.infraestructure.controler;

import com.ejercicios.BS2.aplication.services.ServiceP;
import com.ejercicios.BS2.domain.entity.Ciudad;
import com.ejercicios.BS2.domain.entity.Persona;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping(value = "/BS2")
public class ControladorBS2 {

    //@Autowired
    ServiceP serviceP = new ServiceP();
    /*
    //EJERCICIO1///////////

    @GetMapping("/metodoget")
    public Persona getUser(){
        Persona per = serviceP.getPersona();
        per = new Persona(per.getNombre(), per.getCiudad(), per.getEdad() * 2);
        return per;
    }

    @PostMapping("/metodopost")
    public Persona getUser1(@RequestBody Persona p){

        return  serviceP.createP(p);
    }
    //////////////////EJERCICIO1/////////////
    */


    //////////////////EJERCICIO2/////////////

    //@PostMapping("/addCiudad")
    //public ArrayList<Ciudad> agregarCiudad(@RequestBody ArrayList<Ciudad> c){
     //   return serviceP.cargarC(c);
    //}
    @PostMapping("/addCiudad")
    public ArrayList<Ciudad> agregarCiudad(@RequestBody Ciudad c){
      return serviceP.cargarC1(c);
      }
    @GetMapping("/getCiudad")
    public ArrayList<Ciudad> getCiudad(){
        return serviceP.getCiudades();
    }
    /////////////////EJERCICIO2//////////////



    /////////////////EJERCICIO3/////////////////
    Persona pe;
    /*@Autowired
    @Qualifier("personaBean1")
    Persona p1;
    @Autowired
    @Qualifier("personaBean2")
    Persona p2;
    @Autowired
    @Qualifier("personaBean3")
    Persona p3;

    @GetMapping(value = "bean")
    public Persona getBean(@RequestParam int idBean){
        if(idBean == 1){
            pe = p1;
            return pe;
        }else if(idBean == 2){
            pe = p2;
            return pe;
        }else if(idBean == 3){
            pe = p3;
            return pe;
        } switch (idBean){
            case 1:
                pe = p1;
                return  pe;
            case 2:
                pe = p2;
                return  pe;
            case 3:
                pe = p3;
                return  pe;
            default:
                throw new IllegalStateException("Unexpected value: " + idBean);
        }
    }
    ///////////////EJERCICIO3//////////////////

     */
}
