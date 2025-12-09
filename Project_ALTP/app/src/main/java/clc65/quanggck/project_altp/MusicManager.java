package clc65.quanggck.project_altp;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {

    private static MediaPlayer mediaPlayer;
    private static boolean isMuted = false;

    public static void play(Context context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.intro);
        }
        if (!isMuted) mediaPlayer.start();
    }

    public static void mute() {
        isMuted = true;
        if (mediaPlayer != null) mediaPlayer.setVolume(0, 0);
    }

    public static void unmute() {
        isMuted = false;
        if (mediaPlayer != null) mediaPlayer.setVolume(1, 1);
    }

    public static boolean isMuted() {
        return isMuted;
    }
}
