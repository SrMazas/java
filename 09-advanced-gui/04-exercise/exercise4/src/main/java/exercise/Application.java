
package exercise;

import javax.swing.JFrame;

public class Application {
  public static void main(String[] args) {
    Events events = new Events();
	events.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	events.setLocationRelativeTo(null);
	events.setSize(500, 200);
	events.setVisible(true);
  }
}
