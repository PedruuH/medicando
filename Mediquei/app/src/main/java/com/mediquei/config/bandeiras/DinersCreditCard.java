package com.mediquei.config.bandeiras;

public class DinersCreditCard {

    public static boolean isBrandDiners(final String number) {
        return number != null && number.matches("3[0-9]{13}");
    }

}
