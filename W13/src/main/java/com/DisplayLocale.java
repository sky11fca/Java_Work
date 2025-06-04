package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocale implements Command{
    @Override
    public void execute(Locale locale, ResourceBundle messages) {
        System.out.println(messages.getString("locales"));
        Locale[] locales = Locale.getAvailableLocales();
        for(Locale local:locales){
            System.out.println(local.getDisplayName(locale));
        }
    }
}
