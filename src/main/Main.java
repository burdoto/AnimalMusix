package main;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    static JFrame frame;
    static MediaPlayer player = null;
    static HourChanger changer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initAndShowGUI);

        changer = new HourChanger(player);
    }

    private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        frame = new JFrame("Swing and JavaFX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(300, 200);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                frame.setVisible(false);
            }
        });

        Platform.runLater(() -> initFX(fxPanel));
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene(fxPanel);
        fxPanel.setScene(scene);
    }

    private static Scene createScene(JFXPanel fxPanel) {
        TrayIcon icon = new TrayIcon(createImage("icon.png"), "Animal Crossing Music Player");
        icon.setImageAutoSize(true);
        SystemTray tray = SystemTray.getSystemTray();
        PopupMenu popup = new PopupMenu();

        popup.add(newMenuItem("Play / Pause", "playpause", event -> {
            changer.togglePause();
            System.out.println("pause");
        }));
        popup.addSeparator();
        popup.add(newMenuItem("Animal Crossing: Wild World", "wild_world", event -> changer.setGameName("wild_world")));
        popup.add(newMenuItem("Animal Crossing: New Leaf", "new_leaf", event -> changer.setGameName("new_leaf")));
        popup.addSeparator();
        popup.add(newMenuItem("Volume: 100%", "100", event -> changer.getPlayer().setVolume(1.0)));
        popup.add(newMenuItem("Volume: 75%", "75", event -> changer.getPlayer().setVolume(0.75)));
        popup.add(newMenuItem("Volume: 50%", "50", event -> changer.getPlayer().setVolume(0.50)));
        popup.add(newMenuItem("Volume: 25%", "25", event -> changer.getPlayer().setVolume(0.25)));
        popup.addSeparator();
        popup.add(newMenuItem("Close", "exit", event -> System.exit(0)));

        icon.setPopupMenu(popup);

        try {
            tray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);

        icon.addActionListener(event -> changer.togglePause());

        return (scene);
    }

    private static MenuItem newMenuItem(String label, String actionCommand, ActionListener event) {
        MenuItem item = new MenuItem(label);
        item.setActionCommand(actionCommand);
        item.addActionListener(event);

        return item;
    }

    private static Image createImage(String s) {
        Image image = null;
        try {
            image = ImageIO.read(Main.class.getClassLoader().getResource(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
