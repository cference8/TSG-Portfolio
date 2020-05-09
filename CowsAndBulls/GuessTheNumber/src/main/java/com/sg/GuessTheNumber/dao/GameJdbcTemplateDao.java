/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.dto.Game;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author board
 */
@Repository
public class GameJdbcTemplateDao implements GtnGameDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Game> getAllGames() {
        List<Game> toReturn = template.query("SELECT * FROM Games", new GameMapper());
        return toReturn;
    }

    @Override
    public Game getGameById(Integer id) {
        Game toReturn = template.queryForObject("SELECT * FROM Games WHERE GamesId = ?", new GameMapper(), id);
        return toReturn;
    }

    @Override
    public Game addGame(Integer targetNumber) {
        Game toAdd = new Game();
        KeyHolder kh = new GeneratedKeyHolder();

        toAdd.setTargetNumber(targetNumber);
        toAdd.setComplete(false);

        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Games (targetNumber, isComplete) VALUES(?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, toAdd.getTargetNumber());
                    ps.setBoolean(2, toAdd.getComplete());

                    return ps;

                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setId(generatedId);

        return toAdd;

    }

    @Override
    public void endGame(Integer gameId) {
        template.update("UPDATE Games SET isComplete = 1 WHERE GamesId = ?", gameId);
    }

    @Override
    public void deleteGameById(Integer id) {
        template.update("DELETE FROM Rounds WHERE GamesId = ?", id);
        template.update("DELETE FROM Games WHERE GamesId = ?", id);
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet row, int i) throws SQLException {
            Game converted = new Game();

            converted.setId(row.getInt("GamesId"));
            converted.setTargetNumber(row.getInt("targetNumber"));
            converted.setComplete(row.getBoolean("isComplete"));

            return converted;
        }

    }

}
