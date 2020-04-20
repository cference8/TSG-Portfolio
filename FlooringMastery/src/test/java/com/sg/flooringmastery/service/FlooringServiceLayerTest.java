/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringDaoException;
import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderFileDao;
import com.sg.flooringmastery.dao.FlooringOrderInMemDao;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringProductFileDao;
import com.sg.flooringmastery.dao.FlooringTaxesDao;
import com.sg.flooringmastery.dao.FlooringTaxesFileDao;
import com.sg.flooringmastery.dao.InvalidOrderNumber;
import com.sg.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author board
 */
public class FlooringServiceLayerTest {
    
    FlooringServiceLayer toTest;
    FlooringOrderDao testOrderDao;
    
    public FlooringServiceLayerTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {

        testOrderDao = new FlooringOrderInMemDao();
        
        FlooringProductDao myProductDao = new FlooringProductFileDao("Products.txt");
        FlooringTaxesDao myTaxesDao = new FlooringTaxesFileDao("Taxes.txt");
        
        toTest = new FlooringServiceLayer(testOrderDao, myProductDao, myTaxesDao);
                       
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllOrdersByDate method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllOrdersByDateGoldenPath() {
        try {
            List<Order> allOrders = toTest.getAllOrdersByDate(LocalDate.of(2013, 06, 02));
            
            assertEquals( 2, allOrders.size());
            
            Order one = allOrders.get(0);

            assertEquals(1, one.getOrderNumber());
            assertEquals("John", one.getCustomerName());
            assertEquals("CA", one.getState());
            assertEquals(new BigDecimal("25.00"), one.getTaxRate());
            assertEquals("Tile", one.getProductType());
            assertEquals(new BigDecimal("249.00"), one.getArea());
            assertEquals(new BigDecimal("3.50"), one.getMatUnitCost());
            assertEquals(new BigDecimal("4.15"), one.getLaborUnitCost());
            assertEquals(new BigDecimal("871.50"), one.calcMatCost());
            assertEquals(new BigDecimal("1033.35"), one.calcLaborCost());
            assertEquals(new BigDecimal("476.21"), one.calcTaxes());
            assertEquals(new BigDecimal("2381.06"), one.calcTotal());
            
            Order two = allOrders.get(1);

            assertEquals(2, two.getOrderNumber());
            assertEquals("Albert EinStein", two.getCustomerName());
            assertEquals("KY", two.getState());
            assertEquals(new BigDecimal("6.00"), two.getTaxRate());
            assertEquals("Carpet", two.getProductType());
            assertEquals(new BigDecimal("217.00"), two.getArea());
            assertEquals(new BigDecimal("2.25"), two.getMatUnitCost());
            assertEquals(new BigDecimal("2.10"), two.getLaborUnitCost());
            assertEquals(new BigDecimal("488.25"), two.calcMatCost());
            assertEquals(new BigDecimal("455.70"), two.calcLaborCost());
            assertEquals(new BigDecimal("56.64"), two.calcTaxes());
            assertEquals(new BigDecimal("1000.59"), two.calcTotal());
            
        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException on testGetAllOrdersByDate");
        }
    }

    /**
     * Test of createOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testCreateOrderGoldenPath() {
        
        try {
             Order toAdd = new Order();
                                   
            toAdd.setLocalDate(LocalDate.of(2020, 06, 02));
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setProductType("Tile");
            toAdd.setArea(new BigDecimal("249.00"));
                     
            Order valid = toTest.createOrder(toAdd);
            
            assertEquals(3, valid.getOrderNumber());
            assertEquals("John", valid.getCustomerName());
            assertEquals("CA", valid.getState());
            assertEquals(new BigDecimal("25.00"), valid.getTaxRate());
            assertEquals("Tile", valid.getProductType());
            assertEquals(new BigDecimal("249.00"), valid.getArea());
            assertEquals(new BigDecimal("3.50"), valid.getMatUnitCost());
            assertEquals(new BigDecimal("4.15"), valid.getLaborUnitCost());
            assertEquals(new BigDecimal("871.50"), valid.calcMatCost());
            assertEquals(new BigDecimal("1033.35"), valid.calcLaborCost());
            assertEquals(new BigDecimal("476.21"), valid.calcTaxes());
            assertEquals(new BigDecimal("2381.06"), valid.calcTotal());
            
            toTest.getOrder(LocalDate.of(2013, 06, 02), 2);
            
            assertEquals(3, valid.getOrderNumber());
            assertEquals("John", valid.getCustomerName());
            assertEquals("CA", valid.getState());
            assertEquals(new BigDecimal("25.00"), valid.getTaxRate());
            assertEquals("Tile", valid.getProductType());
            assertEquals(new BigDecimal("249.00"), valid.getArea());
            assertEquals(new BigDecimal("3.50"), valid.getMatUnitCost());
            assertEquals(new BigDecimal("4.15"), valid.getLaborUnitCost());
            assertEquals(new BigDecimal("871.50"), valid.calcMatCost());
            assertEquals(new BigDecimal("1033.35"), valid.calcLaborCost());
            assertEquals(new BigDecimal("476.21"), valid.calcTaxes());
            assertEquals(new BigDecimal("2381.06"), valid.calcTotal());
        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException on testCreateOrderGoldenPath");
        } catch (FlooringProductTypeBadType ex) {
            fail("hit FlooringProductTypeBadType on testCreateOrderGoldenPath");
        }
        
    }
    
    @Test
    public void testGetOrderGoldenPath() {
        try {
            Order valid = toTest.getOrder(LocalDate.of(2013, 06, 02), 1);
            
            assertEquals(1, valid.getOrderNumber());
            assertEquals("John", valid.getCustomerName());
            assertEquals("CA", valid.getState());
            assertEquals(new BigDecimal("25.00"), valid.getTaxRate());
            assertEquals("Tile", valid.getProductType());
            assertEquals(new BigDecimal("249.00"), valid.getArea());
            assertEquals(new BigDecimal("3.50"), valid.getMatUnitCost());
            assertEquals(new BigDecimal("4.15"), valid.getLaborUnitCost());
            assertEquals(new BigDecimal("871.50"), valid.calcMatCost());
            assertEquals(new BigDecimal("1033.35"), valid.calcLaborCost());
            assertEquals(new BigDecimal("476.21"), valid.calcTaxes());
            assertEquals(new BigDecimal("2381.06"), valid.calcTotal());
            
            
        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException on testGetOrderGoldenPath");
        }
    }
    
    @Test
    public void testEditOrderGoldenPath() {
        try {
            Order edited = toTest.getOrder(LocalDate.of(2013, 06, 02), 1);
            
            edited.setCustomerName("Peter");
            edited.setState("CA");
            edited.setProductType("Wood");
            edited.setArea(new BigDecimal("222.00"));
            
            toTest.editOrder(edited);
            
            assertEquals( "Peter", edited.getCustomerName());
            assertEquals( "CA", edited.getState());
            assertEquals( "Wood", edited.getProductType());
            assertEquals( new BigDecimal("222.00"), edited.getArea());
                               
        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException on testEditOrderGoldenPath");
        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderGoldenPath");
        }
    }
    @Test
    public void testDeleteOrderGoldenPath() {
        try {
            Order orderToDelete = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);
            toTest.deleteOrder(orderToDelete);
            List<Order> orders = toTest.getAllOrdersByDate(LocalDate.of(2013, 06, 02));
            assertEquals( 1, orders.size());
            
        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException on testDeleteOrderGoldenPath");
        }
    }
    
    
}
