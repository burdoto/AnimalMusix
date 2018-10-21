package de.kaleidox.animalmusix;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlaySong extends AppCompatActivity {
    private MediaPlayer player;
    private ScheduledExecutorService scheduler;
    private String game = "wildworld", playingGame = "wildworld";
    private int hour = -1, playingHour = -2;
    private boolean playing = true, currState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Date date = new Date();   // given date
                    Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                    calendar.setTime(date);   // assigns calendar to given date
                    PlaySong.this.hour = calendar.get(Calendar.HOUR_OF_DAY);
                    if (playing) {
                        if (!currState || (!game.equals(playingGame) || hour != playingHour)) {
                            playingGame = game;
                            playingHour = hour;
                            player = MediaPlayer.create(getApplicationContext(), R.raw.hourly);
                            player.start();
                            Thread.sleep(13000);
                            player = MediaPlayer.create(getApplicationContext(), Util.getMusicId(playingGame, hour));
                            player.setLooping(true);
                            player.start();
                        }
                    } else {
                        player.pause();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void onPlayPause(View view) {
        playing = !playing;
    }

    public void changetoWildWorld(View view) {
        game = "wildworld";
    }

    public void changetoNewLeaf(View view) {
        game = "newleaf";
    }
}
