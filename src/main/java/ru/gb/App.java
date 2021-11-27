package ru.gb;

public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) throws InterruptedException {


        System.out.println(ANSI_CYAN +"--------------------------------------------------------------------------------------------\n" +
                                      "            Задание №2. Перенесите сценарии для своего проекта. Добавьте ассерты. \n" +
                                      "                                 Добавте пару новых тест-кейсов\n" +
                                      "--------------------------------------------------------------------------------------------\n" + ANSI_RESET);

    }
}
