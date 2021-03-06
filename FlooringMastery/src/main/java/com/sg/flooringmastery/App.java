/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringController;
import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderFileDao;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringProductFileDao;
import com.sg.flooringmastery.dao.FlooringTaxesDao;
import com.sg.flooringmastery.dao.FlooringTaxesFileDao;
import com.sg.flooringmastery.service.FlooringServiceLayer;
import com.sg.flooringmastery.view.ConsoleIO;
import com.sg.flooringmastery.view.FlooringView;
import com.sg.flooringmastery.view.UserIO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author board
 */
public class App {
    public static void main(String[] args) {
        
          ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller
                = ctx.getBean("controller", FlooringController.class);
        controller.run();
        
    }
}
