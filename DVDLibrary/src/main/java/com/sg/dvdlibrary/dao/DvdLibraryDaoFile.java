/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 *
 * @author board
 */
public class DvdLibraryDaoFile implements DvdLibraryDao {

    Map<String, Dvd> dvds = new HashMap<>();

    public static final String ROSTER_FILE = "dvd.txt";
    public static final String DELIMITER = "::";
    
    

    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadDvd();
        Dvd edit = dvds.put(title, dvd);
        writeDvd();
        
        return edit;
        
    }
    

    @Override
    public Dvd addDvd(String title, Dvd dvd)
            throws DvdLibraryDaoException {
        loadDvd();
        Dvd newDvd = dvds.put(title, dvd);
        writeDvd();
        return newDvd;
    }
    
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        
          
        loadDvd();
        return new ArrayList(dvds.values());
    }
    
    @Override
    public Dvd getDvd(String title) {
        return dvds.get(title);
    }
    
    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        loadDvd();
        Dvd removedDvd = dvds.remove(title);
        writeDvd();
        return removedDvd;
    }
    
    private Dvd unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        
        String title = dvdTokens[0];
        
        Dvd dvdFromFile = new Dvd(title);
        
        dvdFromFile.setReleaseDate(Integer.parseInt(dvdTokens[1]));
        
        dvdFromFile.setRating(dvdTokens[2]);
        
        dvdFromFile.setDirector(dvdTokens[3]);
        
        dvdFromFile.setStudio(dvdTokens[4]);
        
        dvdFromFile.setNote(dvdTokens[5]);
        
        
        
        return dvdFromFile;
    }
    
    private void loadDvd() throws DvdLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                "-_- Could not load DVD data into memory.", e);
        }
        
        String currentLine;
        
        Dvd currentDvd;
        
        while (scanner.hasNextLine()) {
            
            currentLine = scanner.nextLine();
            
            currentDvd = unmarshallDvd(currentLine);
            
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    private String marshallDvd(Dvd aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        
        dvdAsText += aDvd.getRating() + DELIMITER;
        
        dvdAsText += aDvd.getDirector() + DELIMITER;
        
        dvdAsText += aDvd.getStudio() + DELIMITER;
        
        dvdAsText += aDvd.getNote();
        
        return dvdAsText;
    }
    
    private void writeDvd() throws DvdLibraryDaoException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }catch (IOException e) {
            throw new DvdLibraryDaoException(
                "Could not save DVD data.", e);
        }
        
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
    
    public List<Dvd> byRating(String rating) {
        List<Dvd> listAllRatings = dvds.values().stream()
                .filter( d -> d.getRating().equals(rating))
                .collect(Collectors.toList());
        return listAllRatings;
    }
    
    public List<Dvd> byDirector(String director) {
        List<Dvd> listAllDirectors = dvds.values().stream()
                .filter( d -> d.getDirector().equals(director))
                .collect(Collectors.toList());
        return listAllDirectors;
    }
    
    public List<Dvd> byStudio(String studio) {
        List<Dvd> listAllStudios = dvds.values().stream()
                .filter( d -> d.getStudio().equals(studio))
                .collect(Collectors.toList());
        return listAllStudios;
    }
    
    public Dvd oldestMovie(int releaseDate) {
        Dvd oldest = dvds.values().stream()
                .min((a, b) -> a.getReleaseDate() - b.getReleaseDate())
                .orElse(null);
                
        return oldest;
    }
    
    public Dvd newestMovie(int releaseDate) {
        Dvd newest = dvds.values().stream()
                .max((a, b) -> a.getReleaseDate() - b.getReleaseDate())
                .orElse(null);
        return newest;
    }
    
    public double averageMovieYear() {
        double average = dvds.values().stream().mapToInt(d -> d.getReleaseDate())
                .average()
                .orElse(Double.NaN);
             
        return average;
    }
    
    public List<Dvd> byReleaseYear(int n) {
        List<Dvd> listAllByReleaseYear = dvds.values().stream()
                .sorted((a, b) -> a.getReleaseDate() - b.getReleaseDate())
                .collect(Collectors.toList());
        
        return listAllByReleaseYear;
    }
    @Override
    public Map<String, List<Dvd>> byDirectorRatingGroup(String director) throws DvdLibraryDaoException {
         loadDvd();
        Map<String, List<Dvd>> directorAndRating = dvds.values().stream()
                .filter(d -> d.getDirector().equals(director))               
                .collect(Collectors.groupingBy(d -> d.getRating()));
        return directorAndRating;
    }
    
    
            
}


