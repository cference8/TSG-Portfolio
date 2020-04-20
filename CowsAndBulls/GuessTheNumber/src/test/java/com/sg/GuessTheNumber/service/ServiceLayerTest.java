/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.service;

import com.sg.GuessTheNumber.TestApplicationConfiguration;
import com.sg.GuessTheNumber.dao.GameInMem;
import com.sg.GuessTheNumber.dao.GtnGameDao;
import com.sg.GuessTheNumber.dao.GtnRoundDao;
import com.sg.GuessTheNumber.dao.RoundInMem;
import com.sg.GuessTheNumber.dto.Game;
import com.sg.GuessTheNumber.dto.Round;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author board
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceLayerTest {

    ServiceLayer toTest;

    GtnGameDao gameDao;

    GtnRoundDao roundDao;

    public ServiceLayerTest() {
        gameDao = new GameInMem();

        roundDao = new RoundInMem();

        toTest = new ServiceLayer(gameDao, roundDao);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of startGame method, of class ServiceLayer.
     */
    @Test
    public void testStartGameGoldenPath() {

        List<Game> allGames = gameDao.getAllGames();

        Game game = toTest.startGame();

        assertEquals(3, allGames.size());
        assertTrue(allGames.contains(game));

    }

    /**
     * Test of getAllGames method, of class ServiceLayer.
     */
    @Test
    public void testGetAllGamesGoldenPath() {
        List<Game> allGames = toTest.getAllGames();

        assertEquals(2, allGames.size());
    }

    /**
     * Test of getGameById method, of class ServiceLayer.
     */
    @Test
    public void testGetGameByIdGoldenPath() {
        Game game = toTest.startGame();
        Game toCheck = toTest.getGameById(game.getId());

        assertEquals(3, toCheck.getId().intValue());
    }

    /**
     * Test of getAllRoundsById method, of class ServiceLayer.
     */
    @Test
    public void testGetAllRoundsByIdGoldenPath() {

        List<Round> test = toTest.getAllRoundsById(1);

        assertEquals(2, test.size());

    }

    /**
     * Test of makeGuess method, of class ServiceLayer.
     */
    @Test
    public void testMakeGuessGoldenPath() {
        try {
            Round toCheck = toTest.makeGuess(1, 1234);

            assertEquals(1, toCheck.getGameId().intValue());
            assertEquals(3, toCheck.getRoundId().intValue());
            assertEquals(4, toCheck.getExactOfGuess().intValue());
            assertEquals(0, toCheck.getPartialOfGuess().intValue());

        } catch (NotUniqueNumberException ex) {
            fail("hit NotUniqueNumberException on Golden Path");
        } catch (NumberOutOfBoundsException ex) {
            fail("hit NumberOutOfBoundsException on Golden Path");
        }
    }
    
        @Test
    public void testMakeGuessNotUniqueNumber() {
        try {
            Round toCheck = toTest.makeGuess(1, 1111);
            fail("did not hit exception on testMakeGuessNotUniqueNumber");
            assertEquals(1, toCheck.getGameId().intValue());
            assertEquals(3, toCheck.getRoundId().intValue());
            assertEquals(4, toCheck.getExactOfGuess().intValue());
            assertEquals(0, toCheck.getPartialOfGuess().intValue());

        } catch (NotUniqueNumberException ex) {

        } catch (NumberOutOfBoundsException ex) {
            fail("hit NumberOutOfBoundsException on Golden Path");
        }
    }
    
    

}
