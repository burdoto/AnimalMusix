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
    private MediaPlayer player = null;
    private ScheduledExecutorService scheduler;
    private String game = "wildworld", playingGame = "wildworld";
    private int hour = -1, playingHour = -2;
    private boolean playing = true, currState = true;
    private float vol = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        if (player == null) {
            scheduler = Executors.newSingleThreadScheduledExecutor();

            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        Date date = new Date();   // given date
                        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                        calendar.setTime(date);   // assigns calendar to given date
                        PlaySong.this.hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int currMin = calendar.get(Calendar.MINUTE);
                        int currSec = calendar.get(Calendar.SECOND);
                        if (playing) {
                            if (!currState || (!game.equals(playingGame) || hour != playingHour)) {
                                if (player != null) player.stop();
                                //PlaySong.this.vol = 1.0f;
                                //player.setVolume(vol, vol);
                                playingGame = game;
                                playingHour = hour;
                                player = MediaPlayer.create(getApplicationContext(), R.raw.hourly);
                                player.start();
                                Thread.sleep(13000);
                                player = MediaPlayer.create(getApplicationContext(), Util.getMusicId(playingGame, hour));
                                player.setLooping(true);
                                player.start();
                                if (!currState) currState = true;
                            }/* else if (currMin >= 59 && currSec >= 40) {
                                vol -= 0.02857142857f;
                                player.setVolume(vol, vol);
                            }*/
                        } else {
                            player.pause();
                            currState = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    public void onPlayPause(View view) {
        playing = !playing;
    }

    public void changetoWildWorld(View view) {
        game = "wildworld";
        if (!currState) onPlayPause(view);
    }

    public void changetoNewLeaf(View view) {
        game = "newleaf";
        if (!currState) onPlayPause(view);
    }
}
