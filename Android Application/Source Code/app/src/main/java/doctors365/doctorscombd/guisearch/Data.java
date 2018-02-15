package doctors365.doctorscombd.guisearch;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HP on 27/5/2016.
 */
public class Data {

    String[] mostSearchedSpecialties={"Surgery","Feamle Doctor", "Heart", "Orthopady", "Dentist"};
    String[] allSpecialties={"Surgery","Feamle Doctor", "Heart", "Orthopady", "Dentist","Eye", "Skin"};
    String[] mostSearchedPlaces={"Weingarten","Ravensburg"};
    String[] allPlaces={"Weingarten","Ravensburg","Aulendorf","Friedrichshafen","Lindau"};
    ArrayList<String> dataList;

    public ArrayList<String> getDataList(Integer selector) {

        switch (selector) {
            case 0:
                dataList = new ArrayList<String>(Arrays.asList(mostSearchedSpecialties));
                break;
            case 1:
                dataList = new ArrayList<String>(Arrays.asList(allSpecialties));
                break;
            case 2:
                dataList = new ArrayList<String>(Arrays.asList(mostSearchedPlaces));
                break;
            case 3:
                dataList = new ArrayList<String>(Arrays.asList(allPlaces));
                break;
        }
        return dataList;
    }

}
