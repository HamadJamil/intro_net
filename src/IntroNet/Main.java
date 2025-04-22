package IntroNet;

import Backend.DataBase;
import View.LogIn;


public class Main {
    public static void main(String[] args) {

        new LogIn(new DataBase());
    }
}