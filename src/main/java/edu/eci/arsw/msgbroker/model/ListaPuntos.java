/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class ListaPuntos {
    @Autowired
    SimpMessagingTemplate msgt;
    
    LinkedList<Point> listdePt=new LinkedList();
    void add(Point punto) {
        msgt.convertAndSend("/topic/newpoint",punto);
        if (listdePt.size()<4) {
            listdePt.add(punto);
            if (listdePt.size()==4) {
                msgt.convertAndSend("/topic/newpolygon",listdePt);
                listdePt.clear();
            }
        }
    }
    
}
