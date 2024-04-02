package com.hiennv.flutter_callkit_incoming;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

public class RingtonePlayerService extends Service {

    private Ringtone ringtone;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if (ringtoneUri == null) {
            ringtoneUri = Settings.System.DEFAULT_RINGTONE_URI;
        }
        ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
        ringtone.setLooping(true);
        if (ringtone != null) {
            ringtone.play();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
    }
}
