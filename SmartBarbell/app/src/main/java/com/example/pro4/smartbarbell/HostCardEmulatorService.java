package com.example.pro4.smartbarbell;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.content.Intent;

import java.util.Arrays;

public class HostCardEmulatorService extends HostApduService {
    private boolean flag=false;

    private static final byte[] AID_SELECT_APDU = {
            (byte) 0x00, // CLA (class of command)
            (byte) 0xA4, // INS (instruction); A4 = select
            (byte) 0x04, // P1  (parameter 1)  (0x04: select by name)
            (byte) 0x00, // P2  (parameter 2)
            (byte) 0x07, // LC  (length of data)
            (byte) 0xA0, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x47, (byte) 0x10, (byte) 0x01
           // (byte) 0x00 // LE   (max length of expected result, 0 implies 256)
    };


    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        String inboundApduDescription;
        byte[] responseApdu;
        if(flag) {
            if (Arrays.equals(AID_SELECT_APDU, apdu)) {
                inboundApduDescription = "Application selected";
                Log.i("HCEDEMO", inboundApduDescription);
                byte[] answer = new byte[2];
                answer[0] = (byte) 0x90;
                answer[1] = (byte) 0x00;
                responseApdu = answer;
                return responseApdu;
            } else if (selectID(apdu)) {
                Log.i("HCEDEMO", "ADD selected");
                int length = apdu[4];
                System.out.println("length = " + length);
                byte[] answer = new byte[4];
                // USER ID !!
                answer[0] = (byte) 0x90;
                answer[1] = (byte) 0x00;
                answer[2] = (byte) 0x11;
                answer[3] = (byte) 0x14;
                responseApdu = answer;
                return responseApdu;

            } else if (logedin(apdu)) {
                Log.i("HCEDEMO", "Loged IN !");
                Toast.makeText(this, "Loged IN!", Toast.LENGTH_LONG).show();
                byte[] answer = new byte[2];
                answer[0] = (byte) 0x90;
                answer[1] = (byte) 0x00;
                responseApdu = answer;
                return responseApdu;
            } else if (logedout(apdu)) {
                Log.i("HCEDEMO", "Loged OUT !");
                Toast.makeText(this, "Loged OUT!", Toast.LENGTH_LONG).show();
                byte[] answer = new byte[2];
                answer[0] = (byte) 0x90;
                answer[1] = (byte) 0x00;
                responseApdu = answer;
                return responseApdu;

            } else {
                inboundApduDescription = "Unknown command";
                Log.i("HCEDEMO", inboundApduDescription);
                Toast.makeText(this, "Error -.- !", Toast.LENGTH_LONG).show();
                byte[] answer = new byte[2];
                answer[0] = (byte) 0x6F;
                answer[1] = (byte) 0x00;
                responseApdu = answer;
                return responseApdu;
            }
        }else{
            inboundApduDescription = "no nfc moder";
            Log.i("HCEDEMO", inboundApduDescription);
            Toast.makeText(this, "not in nfc mode!", Toast.LENGTH_LONG).show();
            byte[] answer = new byte[2];
            answer[0] = (byte) 0x6F;
            answer[1] = (byte) 0x00;
            responseApdu = answer;
            return responseApdu;
        }
    }



    public void onDeactivated(int reason) {
        Log.i("HCEDEMO", "Deactivated: " + reason);
    }
    private boolean selectID(byte[] apdu) {
//		(byte) 0x80,  // CLA
//		(byte) 0x01,  // INS
//		(byte) 0x00,  // P1
//		(byte) 0x00,  // P2
        return apdu.length >= 2 && apdu[0] == (byte) 0x80 && apdu[1] == (byte) 0x01
                && apdu[2] == (byte) 0x00 && apdu[3] == (byte) 0x00;
    }
    private boolean logedin(byte[] apdu) {
//		(byte) 0x80,  // CLA
//		(byte) 0x01,  // INS
//		(byte) 0x01,  // P1
//		(byte) 0x00,  // P2
        return apdu.length >= 2 && apdu[0] == (byte) 0x80 && apdu[1] == (byte) 0x01
                && apdu[2] == (byte) 0x01 && apdu[3] == (byte) 0x00;
    }
    private boolean logedout(byte[] apdu) {
//		(byte) 0x80,  // CLA
//		(byte) 0x01,  // INS
//		(byte) 0x01,  // P1
//		(byte) 0x01,  // P2
        return apdu.length >= 2 && apdu[0] == (byte) 0x80 && apdu[1] == (byte) 0x01
                && apdu[2] == (byte) 0x01 && apdu[3] == (byte) 0x01;
    }
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        flag=intent.getBooleanExtra("flag",false);
        int a=1;
        Toast.makeText(this, "command.. "+flag, Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
}
