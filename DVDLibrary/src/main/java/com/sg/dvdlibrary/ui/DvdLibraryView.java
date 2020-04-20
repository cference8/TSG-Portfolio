/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author board
 */
public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Create DVD title");
        io.print("2. Remove a DVD");
        io.print("3. Edit DVD info");
        io.print("4. List All DVDs");
        io.print("5. Search DVD by Title");
        io.print("6. Sort Group");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices: ", 1, 7);

    }

    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter DVD title: ");
        int releaseDate = io.readInt("Please enter Release Date: ");
        String rating = io.readString("Please enter MPAA rating: ");
        String director = io.readString("Please enter Director: ");
        String studio = io.readString("Please enter Studio: ");
        String note = io.readString("Please enter a personal rating/note: ");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setRating(rating);
        currentDvd.setDirector(director);
        currentDvd.setStudio(studio);
        currentDvd.setNote(note);

        return currentDvd;
    }

    public Dvd editDvd(Dvd originalDvd) {
        Dvd edited = new Dvd(originalDvd.getTitle());
        int releaseDate = io.editInt("Please enter Release Date: ", originalDvd.getReleaseDate());
        String rating = io.editString("Please enter MPAA rating: ", originalDvd.getRating());
        String director = io.editString("Please enter Director: ", originalDvd.getDirector());
        String studio = io.editString("Please enter Studio: ", originalDvd.getStudio());
        String note = io.editString("Please enter a personal rating/note: ", originalDvd.getNote());

        edited.setReleaseDate(releaseDate);
        edited.setRating(rating);
        edited.setDirector(director);
        edited.setStudio(studio);
        edited.setNote(note);

        return edited;

    }

    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditDvdSuccessBanner() {
        io.readString("DVD successfully Edited. Please hit enter to continue.");
    }

    public void displayCreateDvdBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            String dvdInfo = String.format("%s | %s | %s | %s | %s | %s",
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getRating(),
                    currentDvd.getDirector(),
                    currentDvd.getStudio(),
                    currentDvd.getNote());
            io.print(dvdInfo);

        }
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title: ");
    }

    public String getDirectorChoice() {
        return io.readString("Please enter Director name: ");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("DVD Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getRating());
            io.print("Director Name: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("Personal Note: " + dvd.getNote());
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void printGroupResults(Map<String, List<Dvd>> map) {
        
        map.keySet().stream()
                .forEach( r -> map.get(r).stream().forEach(d -> io.print(d.getRating())));
              
        
    }
}
