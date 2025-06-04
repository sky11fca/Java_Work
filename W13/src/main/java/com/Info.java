package com;


import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info implements Command{
    private final String languageTag;

    public Info(String languageTag) {
        this.languageTag = languageTag;
    }

    @Override
    public void execute(Locale currentLocale, ResourceBundle messages) {
        Locale locale = languageTag == null ? currentLocale : Locale.forLanguageTag(languageTag);

        String countryCode = locale.getCountry();
        String countryName = locale.getDisplayCountry(locale);
        String countryNameEnglish = locale.getDisplayCountry(Locale.ENGLISH);

        System.out.println(MessageFormat.format(
                messages.getString("country"),
                countryNameEnglish,
                countryName
        ));

        // Language information
        String languageCode = locale.getLanguage();
        String languageName = locale.getDisplayLanguage(locale);
        String languageNameEnglish = locale.getDisplayLanguage(Locale.ENGLISH);

        System.out.println(MessageFormat.format(messages.getString("language"), languageNameEnglish, languageName));


        // Currency information
        try {
            Currency currency = Currency.getInstance(locale);
            String currencyCode = currency.getCurrencyCode();
            String currencyName = currency.getDisplayName(locale);
            String currencyNameEnglish = currency.getDisplayName(Locale.ENGLISH);
            //System.out.println(messages.getString("currency") +" "+ currencyNameEnglish +" "+ currencyName);
            System.out.println(MessageFormat.format(messages.getString("currency"), currencyNameEnglish, currencyName));
        } catch (IllegalArgumentException e) {
            System.out.println("Currency information not available for this locale");
        }

        // Week days
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] weekdays = dfs.getWeekdays();
        StringBuilder weekDaysStr = new StringBuilder();
        for (int i = 1; i < weekdays.length; i++) {
            weekDaysStr.append(weekdays[i]);
            if (i < weekdays.length - 1) {
                weekDaysStr.append(", ");
            }
        }
        System.out.println(MessageFormat.format(messages.getString("weekDays"), weekDaysStr.toString()));

        // Months
        String[] months = dfs.getMonths();
        StringBuilder monthsStr = new StringBuilder();
        for (int i = 0; i < months.length; i++) {
            if (!months[i].isEmpty()) {
                monthsStr.append(months[i]);
                if (i < months.length - 1) {
                    monthsStr.append(", ");
                }
            }
        }
        System.out.println(MessageFormat.format(messages.getString("months"), monthsStr.toString()));

        // Today's date
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        DateFormat englishDateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
        Date today = new Date();
        String formattedDate = dateFormat.format(today);
        String englishFormattedDate = englishDateFormat.format(today);
        System.out.println(MessageFormat.format(messages.getString("today"), englishFormattedDate, formattedDate));
    }
}
