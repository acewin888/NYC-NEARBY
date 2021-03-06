package dev.android.kevin.project.util;

import android.content.Context;

import java.util.List;

import dev.android.kevin.project.model.DetailBean;

/**
 * Created by kevinsun on 2/24/18.
 */

public class Utility {

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public static String getPriceRange(int rating) {
        String price;
        switch (rating) {
            case 0:
                price = "$";
                break;
            case 1:
                price = "$$";
                break;
            case 2:
                price = "$$$";
                break;
            case 3:
                price = "$$$$";
                break;
            case 4:
                price = "$$$$$";
                break;
            default:
                price = "unknow";

        }
        return price;
    }


    public static String getTypeString(List<String> list) {
        String words = "";
        for (int i = 0; i < list.size() - 1; i++) {
            words += list.get(i) + ", ";
        }
        words = words + list.get(list.size() - 1);

        return words;

    }
}
