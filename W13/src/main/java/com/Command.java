package com;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public interface Command {
    void execute(Locale locale, ResourceBundle messages);
}
