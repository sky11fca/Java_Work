package app;
import com.Command;
import com.DisplayLocale;
import com.Info;
import com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocalExplore {
    public static void main(String[] args) {
        Locale defaultLocale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("Messages", defaultLocale);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(messages.getString("prompt"));
            String commandLine = scanner.nextLine().trim();
            if (commandLine.isEmpty()) {
                continue;
            }

            String[] parts = commandLine.split("\\s+", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;

            try {
                switch (command.toLowerCase()) {
                    case "displaylocales":
                        new DisplayLocale().execute(defaultLocale, messages);
                        break;
                    case "setlocale":
                        if (argument == null) {
                            System.out.println("Please specify a locale (e.g., 'en-US')");
                            break;
                        }
                        new SetLocale(argument).execute(defaultLocale, messages);
                        // Update the locale and messages bundle
                        defaultLocale = Locale.getDefault();
                        messages = ResourceBundle.getBundle("Messages", defaultLocale);
                        break;
                    case "info":
                        new Info(argument).execute(defaultLocale, messages);
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println(messages.getString("invalid"));
                }
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
    }
}
