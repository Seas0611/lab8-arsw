/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author sergio
 */
@Controller
public class STOMPMessagesHandler {
    
    @Autowired
    ListaPuntos listdePt;
    
    @MessageMapping("/newpoint")
    public void getLine(Point pt) throws Exception{
        //System.out.println("Nuevo punto en el servidor"+pt.x+" "+pt.y);
        listdePt.add(pt); 
    }
}
