/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.ConsoleIO;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author board
 */
public class DvdLibraryController {

    private DvdLibraryDao dao;
    private DvdLibraryView view;

    private UserIO io = new ConsoleIO();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {

            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        edit();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        searchDvd();
                        break;
                    case 6:
                        sortDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    public void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    public void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
    
    public void edit() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd toEdit = dao.getDvd(title);
        if ( toEdit != null ){  
            Dvd edited = view.editDvd(toEdit);
                dao.editDvd(title, edited);
            view.displayEditDvdSuccessBanner();
        }else {
            view.displayErrorMessage("Could not find " + title);
        }
        
    }
    

    public void searchDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void sortDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String director = view.getDirectorChoice();
        Map<String, List<Dvd>> map = dao.byDirectorRatingGroup(director);
        view.printGroupResults(map);
        
    }
 
}
