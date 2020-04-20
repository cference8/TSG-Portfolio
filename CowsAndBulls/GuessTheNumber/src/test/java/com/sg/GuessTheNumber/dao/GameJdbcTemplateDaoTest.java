/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.TestApplicationConfiguration;
import com.sg.GuessTheNumber.dto.Game;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author board
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameJdbcTemplateDaoTest {

    @Autowired
    GtnGameDao gameDao;

    public GameJdbcTemplateDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        List<Game> allGames = gameDao.getAllGames();

        for (Game game : allGames) {
            gameDao.deleteGameById(game.getId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllGames method, of class GameJdbcTemplateDao.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setTargetNumber(1234);
        game.setId(1);
        game.setComplete(false);
        gameDao.addGame(game.getTargetNumber());

        Game game2 = new Game();
        game2.setTargetNumber(5678);
        game2.setId(2);
        game2.setComplete(false);
        gameDao.addGame(game2.getTargetNumber());

        List<Game> games = gameDao.getAllGames();

        assertEquals(3, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));

    }

    /**
     * Test of getGameById method, of class GameJdbcTemplateDao.
     */
    @Test
    public void testGetGameById() {
    }

    /**
     * Test of addGame method, of class GameJdbcTemplateDao.
     */
    @Test
    public void testAddGame() {
    }

    /**
     * Test of endGame method, of class GameJdbcTemplateDao.
     */
    @Test
    public void testEndGame() {
    }

}
