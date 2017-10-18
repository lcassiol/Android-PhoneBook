package com.example.lcassiol.android_phonebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsMessage;

/**
 * Created by lcass on 10/17/2017.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Object[] messages = (Object[]) bundle.get("pdus");

        byte[] message = (byte[]) messages[0];
        SmsMessage msg = SmsMessage.createFromPdu(message);
        String phone = msg.getDisplayOriginatingAddress();

        ContactDAO dao = new ContactDAO(context);

        if(dao.isContact(phone)){
            //MediaPlayer player = MediaPlayer.create(context, R.raw.msg);
            //player.start();
        }


    }
}
