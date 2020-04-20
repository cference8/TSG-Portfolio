/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFile;
import com.sg.dvdlibrary.ui.ConsoleIO;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;

/**
 *
 * @author board
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new ConsoleIO();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFile();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);

        controller.run();
    }

}
