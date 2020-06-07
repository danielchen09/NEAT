package GUI;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class GUIMain {
    public void start() {
        JFrame frame = new JFrame("NEAT GUI");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setContentPane(new MainPage(frame).getMainPanel());
        frame.setContentPane(new Display().getMainPanel());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("menu");
        JMenuItem options = new JMenuItem("options");
        options.addActionListener(actionEvent -> {
            JDialog dialog = new JDialog(frame, "options", true);
            Thread thread = new Thread(() -> {
                dialog.add(new MainPage(frame).getMainPanel());
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setSize(500, 500);
                dialog.pack();
                dialog.setVisible(true);
            });
            thread.start();
        });
        menu.add(options);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
