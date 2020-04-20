/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.dto.Game;
import com.sg.GuessTheNumber.dto.Round;
import java.util.List;

/**
 *
 * @author board
 */
public interface GtnRoundDao {

    public Round addRound(Round toAdd);

    public List<Round> getAllRoundsById(Integer gameId);
    
    public List<Round> getAllRounds();
    
}
