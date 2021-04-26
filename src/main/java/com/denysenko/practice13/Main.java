package com.denysenko.practice13;

import com.denysenko.practice13.controllers.Controller;
import com.denysenko.practice13.dao.OracleDao;
import com.denysenko.practice13.views.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(), new OracleDao());
        controller.mainMenu();
    }
}