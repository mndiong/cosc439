
//Java program to run an application.

import java.util.*;

class openNotepad {
  public static void main(String[] args) {
    Runtime app = Runtime.getRuntime();
    try {
      //open notepad
      app.exec("notepad");
      //open calculator
      app.exec("calc");
    } catch (Exception Ex) {
      System.out.println("Error: " + Ex.toString());
    }
  }
}