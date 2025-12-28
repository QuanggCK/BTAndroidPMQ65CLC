package clc65.quanggck.project_altp;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {

    private static MediaPlayer mediaPlayer;
    private static boolean isMuted = false;

    public static void play(Context context, int songId, boolean isLooping) {
        // Nếu mediaPlayer chưa được khởi tạo thì khởi tạo mới
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release(); // Hàm giải phóng tài nguyên
            mediaPlayer = null;
        }

        // Khởi tạo lại mediaPlayer
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, songId);
            mediaPlayer.setLooping(isLooping);
        }

        if (isMuted) {
            mediaPlayer.setVolume(0, 0);
        } else {
            mediaPlayer.setVolume(1, 1);
        }

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public static void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
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