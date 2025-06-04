package com;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale implements Command{
    private final String languageTag;

    public SetLocale(String languageTag) {
        this.languageTag = languageTag;
    }

    @Override
    public void execute(Locale locale, ResourceBundle messages) {
        Locale newLocale;

        if(languageTag.equalsIgnoreCase("ro") || languageTag.equalsIgnoreCase("ro-RO")){
            newLocale = new Locale("ro", "RO");
        }
        else{
            newLocale = Locale.forLanguageTag(languageTag.replace("_", "-"));
        }

        Locale.setDefault(newLocale);
        ResourceBundle newMessage = ResourceBundle.getBundle("Messages", newLocale);
        System.out.println(MessageFormat.format(
                newMessage.getString("locale.set"),
                newLocale.getDisplayName(newLocale)
        ));
    }
}
