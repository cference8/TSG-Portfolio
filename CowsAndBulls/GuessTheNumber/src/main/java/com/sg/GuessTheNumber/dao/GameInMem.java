/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.dto.Game;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author board
 */
public class GameInMem implements GtnGameDao {

    List<Game> games = new ArrayList<>();

    public GameInMem() {
        Game toAdd = new Game();

        toAdd.setId(1);
        toAdd.setTargetNumber(1234);
        toAdd.setComplete(false);
        
        Game toAdd2 = new Game();
        
        toAdd2.setId(2);
        toAdd2.setTargetNumber(5678);
        toAdd2.setComplete(false);
        
        games.add(toAdd);
        games.add(toAdd2);

    }

    @Override
    public List<Game> getAllGames() {
        return games;
    }

    @Override
    public Game getGameById(Integer id) {
        Game toReturn = games.get(id-1);
        return toReturn;
    }

    @Override
    public Game addGame(Integer target) {
        Game toAdd = new Game();
        
        int nextId = findNextId();

        toAdd.setId(nextId);
        toAdd.setTargetNumber(target);

        games.add(toAdd);

        return toAdd;
    }

    @Override
    public void endGame(Integer gameId) {
        for(Game game : games){
            if(gameId == game.getId().intValue()){
                game.setComplete(true);
            }
        }
                
    }

    @Override
    public void deleteGameById(Integer id) {
        
        games.removeIf(g -> g.getId().intValue() == id);
        
    }

    private int findNextId() {
        //compute the max id
        int id = 0;

        for (Game toCheck : games) {
            if (toCheck.getId() > id) {
                id = toCheck.getId();
            }
        }
        //go up by one for the new id
        id++;

        return id;
    }

}
