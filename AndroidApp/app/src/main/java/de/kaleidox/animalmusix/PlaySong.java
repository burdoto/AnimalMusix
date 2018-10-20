package de.kaleidox.animalmusix;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlaySong extends AppCompatActivity {
    private MediaPlayer player;
    private ScheduledExecutorService scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        player = MediaPlayer.create(this, R.string.wildworld_hourly);
        scheduler = Executors.newSingleThreadScheduledExecutor();

        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        calendar.get(Calendar.HOUR_OF_DAY);
    }

    public void onPlayPause(View view) {
    }

    public void changetoWildWorld(View view) {
    }

    public void changetoNewLeaf(View view) {
    }
}
