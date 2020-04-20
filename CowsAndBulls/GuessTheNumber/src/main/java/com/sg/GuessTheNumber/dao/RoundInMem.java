/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.dto.Round;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author board
 */
public class RoundInMem implements GtnRoundDao {

    List<Round> allRounds = new ArrayList<>();

    public RoundInMem() {
        Round round1 = new Round();

        round1.setRoundId(1);
        round1.setGameId(1);
        round1.setGuess(1234);
        round1.setTimeOfGuess(round1.getTimeOfGuess());
        round1.setPartialOfGuess(0);
        round1.setExactOfGuess(0);

        Round round2 = new Round();

        round2.setRoundId(2);
        round2.setGameId(1);
        round2.setGuess(5678);
        round2.setTimeOfGuess(round2.getTimeOfGuess());
        round2.setPartialOfGuess(0);
        round2.setExactOfGuess(0);
        
        allRounds.add(round2);
        allRounds.add(round1);
    }

    @Override
    public Round addRound(Round toAdd) {

        int nextId = findNextId();

        toAdd.setRoundId(nextId);
        allRounds.add(toAdd);
        return toAdd;
    }

    @Override
    public List<Round> getAllRoundsById(Integer gameId) {

        List<Round> toReturn = allRounds;

        for (Round round : toReturn) {
            if (gameId != round.getGameId().intValue()) {
                return null;
            }
        }
        return toReturn;
    }

    @Override
    public List<Round> getAllRounds() {

        return allRounds;
    }

    private int findNextId() {
        //compute the max id
        int id = 0;

        for (Round toCheck : allRounds) {
            if (toCheck.getRoundId() > id) {
                id = toCheck.getRoundId();
            }
        }
        //go up by one for the new id
        id++;

        return id;
    }

}
