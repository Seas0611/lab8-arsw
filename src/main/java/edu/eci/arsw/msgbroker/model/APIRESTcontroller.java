/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergio
 */
@RestController
@RequestMapping(value="/puntos")
public class APIRESTcontroller {
    @Autowired
    ListaPuntos listdePT;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody Point pt){
        ResponseEntity c=null;
        try {
            listdePT.add(pt);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity<>("Error blabalbalabl",HttpStatus.FORBIDDEN);
        }
        return c;
                
    }
    
}
