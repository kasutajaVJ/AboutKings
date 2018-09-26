package com.example.keiti.aboutkings;

import java.util.ArrayList;
import java.util.List;

public class Kings {

    private List<King> list = new ArrayList<>();

    //    Our multidimensional array that has our data values
    private static String[][] data = {
            {"Henry VIII", "1509", "1547", "First king"},
            {"Edward VI", "1547", "1553", "Second king"},
            {"Mary I", "1553", "1558", "Third king"},
            {"Elizabeth I", "1558", "1603", "Fourth king"},
            {"James I", "1603", "1625", "Fifth king"},
            {"Charles I", "1625", "1649", "Sixth king"},
            {"Charles II", "1660", "1685", "Seventh king"},
            {"James II", "1685", "1688", "Eight king"},
            {"Mary II", "1689", "1694", "Ninth king"},
            {"William II", "1689", "1702", "Tenth king"},
            {"Anne", "1702", "1707", "Eleventh king"},
    };

    //    adding multidimensional array values into the list
    public Kings() {
        for (String[] arr : data)
            list.add(new King(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]));
    }

    public List<King> getKings() {
        return list;
    }
}
