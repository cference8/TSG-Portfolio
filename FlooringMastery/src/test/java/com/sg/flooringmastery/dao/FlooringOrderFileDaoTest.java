


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
public class FlooringOrderFileDaoTest {

    FlooringOrderFileDao toTest = new FlooringOrderFileDao("Test");

    public FlooringOrderFileDaoTest() {

    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        //1. make a "File" object that points at our Test directory
        File testDir = new File("Test");
        //2. make a "File" object that points at our Seed directory
        File seedDir = new File("Seed");

        //3. Delete all files under testDir
        //3.1 Get all files under testDir
        //3.2 Loop through the files and delete
        File[] testFiles = testDir.listFiles();

        for (File testFile : testFiles) {
            testFile.delete();
        }

        //4. Copy all seed files to the test directory
        //4.1 Get all files under seedDir
        //4.2 Loop through files and copy
        File[] seedFiles = seedDir.listFiles();
        for (File seedFile : seedFiles) {
            Files.copy(seedFile.toPath(), Paths.get("Test", seedFile.getName()), StandardCopyOption.REPLACE_EXISTING);

        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrdersByDate method, of class FlooringOrderFileDao.
     */
    @Test
    public void testGetAllOrdersByDateGoldenPath() {
        try {
            List<Order> allOrders = toTest.getAllOrdersByDate(LocalDate.of(2013, 06, 02));
            //all orders list size 1
            assertEquals(2, allOrders.size());
            //assert that specific file
            Order one = allOrders.get(0);

            assertEquals(2, one.getOrderNumber());
            assertEquals("Doctor Who", one.getCustomerName());
            assertEquals("WA", one.getState());
            assertEquals(new BigDecimal("9.25"), one.getTaxRate());
            assertEquals("Wood", one.getProductType());
            assertEquals(new BigDecimal("243.00"), one.getArea());
            assertEquals(new BigDecimal("5.15"), one.getMatUnitCost());
            assertEquals(new BigDecimal("4.75"), one.getLaborUnitCost());
            assertEquals(new BigDecimal("1251.45"), one.calcMatCost());
            assertEquals(new BigDecimal("1154.25"), one.calcLaborCost());
            assertEquals(new BigDecimal("222.53"), one.calcTaxes());
            assertEquals(new BigDecimal("2628.23"), one.calcTotal());

            Order two = allOrders.get(1);

            assertEquals(3, two.getOrderNumber());
            assertEquals("Albert Einstein", two.getCustomerName());
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
            fail("hit FlooringDaoException on golden path test.");
        }
    }

    @Test
    public void testGetAllOrdersByDateNullDate() {
        try {
            LocalDate date = null;
            List<Order> allOrders = toTest.getAllOrdersByDate(date);
            fail("Didn't hit an exception during testGetAllOrdersByDateNullDate");
        } catch (FlooringDaoException ex) {

        }
    }

    /**
     * Test of createOrder method, of class FlooringOrderFileDao.
     */
    @Test
    public void testCreateOrderGoldenPath() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 02));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setTaxRate(new BigDecimal("25.00"));
            toAdd.setProductType("Tile");
            toAdd.setArea(new BigDecimal("249.00"));
            toAdd.setMatUnitCost(new BigDecimal("3.50"));
            toAdd.setLaborUnitCost(new BigDecimal("4.15"));
            toAdd.calcMatCost().add(new BigDecimal("871.50"));
            toAdd.calcLaborCost().add(new BigDecimal("1033.35"));
            toAdd.calcTaxes().add(new BigDecimal("476.21"));
            toAdd.calcTotal().add(new BigDecimal("2381.06"));
            
            Order valid = toTest.createOrder(toAdd);
            
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
            fail("hit FlooringDaoException on testCreateOrderGoldenPath");
        }

    }
    @Test
    public void testCreateOrderNullOrder() {
        try {
            Order order = null;
            toTest.createOrder(order);
            fail("did not hit exception");
        } catch (FlooringDaoException ex) {
        }
    }
    

    /**
     * Test of createOrder method, of class FlooringOrderFileDao.
     */
    @Test
    public void testCreateOrderNullArea() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 02));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setTaxRate(new BigDecimal("25.00"));
            toAdd.setProductType("Tile");
            toAdd.setArea(null);

            Order valid = toTest.createOrder(toAdd);
            assertEquals(null, valid.getArea());
            fail("Didn't hit an exception during testCreateOrderNullArea");

        } catch (FlooringDaoException ex) {

        }

    }

    @Test
    public void testCreateOrderNullDate() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(null);
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setTaxRate(new BigDecimal("25.00"));
            toAdd.setProductType("Tile");
            toAdd.setArea(new BigDecimal("249.00"));
            toAdd.setMatUnitCost(new BigDecimal("3.50"));
            toAdd.setLaborUnitCost(new BigDecimal("4.15"));
            toAdd.calcMatCost().add(new BigDecimal("871.50"));
            toAdd.calcLaborCost().add(new BigDecimal("1033.35"));
            toAdd.calcTaxes().add(new BigDecimal("476.21"));
            toAdd.calcTotal().add(new BigDecimal("2381.06"));

            Order valid = toTest.createOrder(toAdd);
            
            assertEquals( null, valid.getLocalDate());
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {
           
        }

    }
    
    @Test
    public void testCreateOrderNullState() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 05));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState(null);
            toAdd.setTaxRate(new BigDecimal("25.00"));
            toAdd.setProductType("Tile");
            toAdd.setArea(new BigDecimal("249.00"));
            toAdd.setMatUnitCost(new BigDecimal("3.50"));
            toAdd.setLaborUnitCost(new BigDecimal("4.15"));
            toAdd.calcMatCost().add(new BigDecimal("871.50"));
            toAdd.calcLaborCost().add(new BigDecimal("1033.35"));
            toAdd.calcTaxes().add(new BigDecimal("476.21"));
            toAdd.calcTotal().add(new BigDecimal("2381.06"));

            Order valid = toTest.createOrder(toAdd);
            
            assertEquals( null, valid.getState());
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {
          
        }

    }
    
    @Test
    public void testCreateOrderNullProductType() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 05));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setTaxRate(new BigDecimal("25.00"));
            toAdd.setProductType(null);
            toAdd.setArea(new BigDecimal("249.00"));
            toAdd.setMatUnitCost(new BigDecimal("3.50"));
            toAdd.setLaborUnitCost(new BigDecimal("4.15"));
            toAdd.calcMatCost().add(new BigDecimal("871.50"));
            toAdd.calcLaborCost().add(new BigDecimal("1033.35"));
            toAdd.calcTaxes().add(new BigDecimal("476.21"));
            toAdd.calcTotal().add(new BigDecimal("2381.06"));

            Order valid = toTest.createOrder(toAdd);
            
            assertEquals( null, valid.getProductType());
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {
          
        }

    }
    
        @Test
    public void testCreateOrderNullTaxRate() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 05));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setTaxRate(null);
            toAdd.setProductType("Tile");

            Order valid = toTest.createOrder(toAdd);
            
            assertEquals( null, valid.getTaxRate());
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {
          
        }

    }
    
    @Test
    public void testCreateOrderNullMatUnitCost() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 05));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setProductType("Tile");
            toAdd.setMatUnitCost(null);
            

            Order valid = toTest.createOrder(toAdd);
            
            assertEquals( null, valid.getMatUnitCost());
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {
            
        }

    }
    
    @Test
    public void testCreateOrderNullLaborUnitCost() {

        try {
            Order toAdd = new Order();
            toAdd.setLocalDate(LocalDate.of(2020, 06, 05));
            toAdd.setOrderNumber(1);
            toAdd.setCustomerName("John");
            toAdd.setState("CA");
            toAdd.setProductType("Tile");
            toAdd.setLaborUnitCost(null);
            

            Order valid = toTest.createOrder(toAdd);
            
            assertEquals( null, valid.getMatUnitCost());
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {
            
        }

    }
    
    /**
     * Test of getOrder method, of class FlooringOrderFileDao.
     */
    @Test
    public void testGetOrderGoldenPath() {
        try {

            Order valid = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            assertEquals(2, valid.getOrderNumber());
            assertEquals("Doctor Who", valid.getCustomerName());
            assertEquals("WA", valid.getState());
            assertEquals(new BigDecimal("9.25"), valid.getTaxRate());
            assertEquals("Wood", valid.getProductType());
            assertEquals(new BigDecimal("243.00"), valid.getArea());
            assertEquals(new BigDecimal("5.15"), valid.getMatUnitCost());
            assertEquals(new BigDecimal("4.75"), valid.getLaborUnitCost());
            assertEquals(new BigDecimal("1251.45"), valid.calcMatCost());
            assertEquals(new BigDecimal("1154.25"), valid.calcLaborCost());
            assertEquals(new BigDecimal("222.53"), valid.calcTaxes());
            assertEquals(new BigDecimal("2628.23"), valid.calcTotal());

        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException on testGetOrderGoldenPath");
        }
    }

    /**
     * Test of editOrder method, of class FlooringOrderFileDao.
     */
    @Test
    public void testEditOrderGoldenPath() {
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            toEdit.setCustomerName("Tom Thumb");
            toEdit.setState("CA");
            toEdit.setProductType("Tile");
            toEdit.setArea(new BigDecimal("249.00"));

            toTest.editOrder(toEdit);

            assertEquals("Tom Thumb", toEdit.getCustomerName());
            assertEquals("CA", toEdit.getState());
            assertEquals("Tile", toEdit.getProductType());
            assertEquals(new BigDecimal("249.00"), toEdit.getArea());

        } catch (FlooringDaoException ex) {
            fail("Hit FlooringDaoException on testEditOrderGoldenPath");
        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderGoldenPath");
        }

    }
    
    @Test
    public void testEditOrderNullArea(){
        
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            toEdit.setArea(null);

            toTest.editOrder(toEdit);
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {

        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullArea");
        }

    }
    
    @Test
    public void testEditOrderNullState() {
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            toEdit.setState(null);

            toTest.editOrder(toEdit);
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {

        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullState");
        }

    }
    
    @Test
    public void testEditOrderNullTaxRate() {
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            toEdit.setTaxRate(null);

            toTest.editOrder(toEdit);
            
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {

        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullTaxRate");
        }

    }
    
    @Test
    public void testEditOrderNullProductType() {
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            toEdit.setCustomerName("Tom Thumb");
            toEdit.setState("CA");
            toEdit.setProductType(null);
            toEdit.setArea(new BigDecimal("249.00"));

            toTest.editOrder(toEdit);
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {

        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullProductType");
        }

    }
    @Test
    public void testEditOrderNullMatUnitCost() {
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);
            
            toEdit.setMatUnitCost(null);

            toTest.editOrder(toEdit);
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {

        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullMatUnitCost");
        }

    }
    
    @Test
    public void testEditOrderNullLaborUnitCost() {
        try {
            Order toEdit = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);
            
            toEdit.setLaborUnitCost(null);

            toTest.editOrder(toEdit);
            fail("did not hit exception");

        } catch (FlooringDaoException ex) {

        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullLaborUnitCost");
        }

    }
    
    
    @Test
    public void testEditOrderNullDate(){
        try {
            Order toEdit = new Order();
            toEdit.setLocalDate(null);
          
            toTest.editOrder(toEdit);
             fail("did not hit exception");

        } catch (FlooringDaoException ex) {
            
        } catch (InvalidOrderNumber ex) {
            fail("hit InvalidOrderNumber on testEditOrderNullDate");
        }

    }
    /**
     * Test of deleteOrder method, of class FlooringOrderFileDao.
     */
    @Test
    public void testDeleteOrderGoldenPath() {

        try {

            Order valid = toTest.getOrder(LocalDate.of(2013, 06, 02), 2);

            assertEquals(2, valid.getOrderNumber());
            assertEquals("Doctor Who", valid.getCustomerName());
            assertEquals("WA", valid.getState());
            assertEquals(new BigDecimal("9.25"), valid.getTaxRate());
            assertEquals("Wood", valid.getProductType());
            assertEquals(new BigDecimal("243.00"), valid.getArea());
            assertEquals(new BigDecimal("5.15"), valid.getMatUnitCost());
            assertEquals(new BigDecimal("4.75"), valid.getLaborUnitCost());
            assertEquals(new BigDecimal("1251.45"), valid.calcMatCost());
            assertEquals(new BigDecimal("1154.25"), valid.calcLaborCost());
            assertEquals(new BigDecimal("222.53"), valid.calcTaxes());
            assertEquals(new BigDecimal("2628.23"), valid.calcTotal());

            toTest.deleteOrder(valid);

            List<Order> check = toTest.getAllOrdersByDate(LocalDate.of(2013, 06, 02));

            assertEquals(2, check.size());

        } catch (FlooringDaoException ex) {
            fail("hit FlooringDaoException testDeleteOrderGoldenPath ");
        }

    }

}
