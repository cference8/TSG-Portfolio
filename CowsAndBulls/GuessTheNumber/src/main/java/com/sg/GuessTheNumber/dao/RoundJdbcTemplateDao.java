/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.dto.Game;
import com.sg.GuessTheNumber.dto.Round;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author board
 */
@Repository
public class RoundJdbcTemplateDao implements GtnRoundDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Round addRound(Round toAdd) {

        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected;
        rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Rounds (TimeOfGuess, Guess, ExactGuess, PartialGuess, GamesId) "
                            + "VALUES( ?,?,?,?,? )",
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, toAdd.getTimeOfGuess().toString());
                    ps.setInt(2, toAdd.getGuess());
                    ps.setInt(3, toAdd.getExactOfGuess());
                    ps.setInt(4, toAdd.getPartialOfGuess());
                    ps.setInt(5, toAdd.getGameId());

                    return ps;

                },
                kh);

        int generatedId = kh.getKey().intValue();

        toAdd.setRoundId(generatedId);

        return toAdd;
    }

    @Override
    public List<Round> getAllRoundsById(Integer gameId) {
        List<Round> toReturn = template.query("SELECT * FROM Rounds "
                + "WHERE GamesId = ?", new RoundMapper(), gameId);

        return toReturn;
    }

    @Override
    public List<Round> getAllRounds() {
        List<Round> toReturn = template.query("SELECT * FROM Rounds", new RoundMapper());

        return toReturn;
    }

    private static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet row, int i) throws SQLException {

            Round converted = new Round();

            converted.setRoundId(row.getInt("RoundsId"));
            converted.setTimeOfGuess(row.getTimestamp("timeOfGuess").toLocalDateTime());
            converted.setGuess(row.getInt("Guess"));
            converted.setExactOfGuess(row.getInt("ExactGuess"));
            converted.setPartialOfGuess(row.getInt("PartialGuess"));
            converted.setGameId(row.getInt("GamesId"));

            return converted;
        }
    }
}
