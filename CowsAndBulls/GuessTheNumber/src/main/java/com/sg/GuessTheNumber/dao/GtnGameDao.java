/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.dto.Game;
import com.sg.GuessTheNumber.dto.Round;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author board
 */
public interface GtnGameDao {

    public List<Game> getAllGames();

    public Game getGameById(Integer id);

    public Game addGame(Integer target);
    
    public void endGame(Integer gameId);
    
    public void deleteGameById(Integer id);
    
}
