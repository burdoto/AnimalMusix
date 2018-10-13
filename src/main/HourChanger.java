package main;

import com.sun.deploy.util.WinRegistry;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class HourChanger {
    private MediaPlayer player;
    private int prevHour = -1;
    private AtomicBoolean playing = new AtomicBoolean(true);
    private AtomicBoolean fading = new AtomicBoolean(false);
    private String gameName = "wild_world";
    private double volume = 0.2;

    public HourChanger(MediaPlayer player) {
        this.player = player;

        start();
    }

    private void start() {
        CompletableFuture.supplyAsync(() -> null)
                .thenAcceptAsync(a -> {
                    while (true) {
                        if (player != null) {
                            volume = player.getVolume();
                        }

                        if (playing.get()) {
                            Util.sleep(1000L);

                            LocalDateTime ldt = LocalDateTime.now();
                            System.out.println("test: " + ldt);
                            URI uri = null;
                            Media hit = null;
                            long sleep = 0;

 /*
                            if (ldt.getMinute() == 59 && ldt.getSecond() >= 30) {
                                // fade out async here
                                if (!fading.get()) {
                                    fading.set(true);

                                    CompletableFuture.supplyAsync(() -> null)
                                            .thenAcceptAsync(b -> {
                                                    long amountPerStep = (long) volume / 300;

                                                    for (int i = 0; i < 300; i++) {
                                                        player.setVolume(player.getVolume() - amountPerStep);
                                                        System.out.println("lowered to "+player.getVolume());
                                                        System.out.println(amountPerStep);

                                                        Util.sleep(999L);
                                                    }
                                            });
                                }
                            }
  */

                            if (prevHour != ldt.getHour()) {
                                prevHour = ldt.getHour();
                                try {
                                    uri = Main.class.getClassLoader().getResource("songs/" + gameName + "/hourly.mp3").toURI();
                                    hit = new Media(uri.toString());
                                    fading.set(false);
                                    // sleep = (long) hit.getDuration().toMillis();
                                    sleep = 12000;
                                } catch (URISyntaxException | NullPointerException e) {
                                    e.printStackTrace();
                                }
                            } else if (prevHour == ldt.getHour()) {
                                String source = player.getMedia().getSource();
                                if (!source.substring(source.indexOf("songs/")).contains(ldt.getHour() + "")) {
                                    try {
                                        String path = "songs/" + gameName + "/" + (ldt.getHour() < 10 ? "0" : "") + ldt.getHour() + ".mp3";
                                        uri = Main.class.getClassLoader().getResource(path).toURI();
                                        hit = new Media(uri.toString());
                                    } catch (URISyntaxException | NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            if (uri != null && hit != null) {
                                if (player != null)
                                    player.stop();
                                player = new MediaPlayer(hit);
                                player.play();
                                player.setCycleCount(MediaPlayer.INDEFINITE);
                                player.setAutoPlay(true);
                                player.setOnEndOfMedia(() -> this.player.seek(new Duration(1L)));
                                if (!fading.get())
                                    player.setVolume(volume);

                                Util.sleep(sleep);
                            }
                        } else {
                            if (player != null)
                                player.stop();
                        }
                    }
                }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
        this.player.stop();
        this.player = null;
        this.prevHour = -1;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void togglePause() {
        playing.set(!playing.get());

        if (playing.get()) {
            this.player.play();
        }
    }
}
