
package com.exercise;

import javax.swing.JFrame;

public class Application {
  public static void main(String[] args) {

    Events events = new Events();
    events.setLocationRelativeTo(null);
    events.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    events.setSize(500, 500);
    events.setVisible(true);

  }
}
