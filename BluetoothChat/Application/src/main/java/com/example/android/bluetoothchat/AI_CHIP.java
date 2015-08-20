package com.example.android.bluetoothchat;

/**
 * Created by nakagawayuki on 15/08/19.
 */

import java.lang.String;
import java.net.ConnectException;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class AI_CHIP extends BluetoothChatService{
    // Debugging
    private static final String TAG = "AI_CHIP";
    private int mGetData;

    /**
     * Constructor. Prepares a new BluetoothChat session.
     *
     * @param context The UI Activity Context
     * @param handler A Handler to send messages back to the UI Activity
     */
    public AI_CHIP(Context context, Handler handler) {
        super(context, handler);
    }

    public float get_ACC_x(){
        float ACC_x = 0;
        String data = get_Data();
        ACC_x = (float)Integer.parseInt(data.substring(17,20),16);
        System.out.println(data.substring(16,20));
        ACC_x = ACC_x / 2048;
        return ACC_x;
    }

    public float get_ACC_y(){
        float ACC_y = 0;
        String data = get_Data();
        ACC_y = (float)Integer.parseInt(data.substring(17,20),16);
        System.out.println(data.substring(16,20));
        ACC_y = ACC_y / 2048;
        return ACC_y;
    }

    public float get_ACC_z(){
        float ACC_z = 0;
        String data = get_Data();
        ACC_z = (float)Integer.parseInt(data.substring(17,20),16);
        System.out.println(data.substring(16,20));
        ACC_z = ACC_z / 2048;
        return ACC_z;
    }

    public String get_Data(){
        Boolean loop = Boolean.TRUE;

        while(Boolean.TRUE) {
            byte[] bytes = super.getBuffer();
            //buffer = buffer << 64;
            //System.out.println(buffer[32]);
            //int data = 1;
            //return data;
            StringBuffer strbuf = new StringBuffer(bytes.length * 2);

            // バイト配列の要素数分、処理を繰り返す。
            for (int index = 0; index < bytes.length; index++) {
                // バイト値を自然数に変換。
                int bt = bytes[index] & 0xff;

                // バイト値が0x10以下か判定。
                if (bt < 0x10) {
                    // 0x10以下の場合、文字列バッファに0を追加。
                    strbuf.append("0");
                }

                // バイト値を16進数の文字列に変換して、文字列バッファに追加。
                mGetData = bt;
                strbuf.append(Integer.toHexString(mGetData));
            }
            int result = strbuf.toString().indexOf("ffff5254345700");
            if(result == -1){

            }else {
                System.out.println(strbuf.toString());
                /// 16進数の文字列を返す。
                //loop = Boolean.FALSE;
                return strbuf.toString();
            }
        }
        return "0";
    }

    public void motor_run(int duty){
        byte id = 0;
        byte[] power = {0,0};
        //duty = duty -1;
        if(duty >= 100) {
            duty = 99;
        }else if(duty <= -100){
            duty = -99;
        }
        if(duty >= 0){
            int input_data = (int)(32767 * (float)duty/100 );//32767
            //Log.d(TAG, String.valueOf("seekbar -> "+input_data));
            String data_string = Integer.toBinaryString(input_data);
            //Log.d("AI_CHIP", String.valueOf("data_string -> "+data_string));

            power[0] = (byte)((input_data & 0xff00)>>>8);
            power[1] = (byte)(input_data  & 0x00ff);
        }else{
            short input_data = (short) ((float)32766 * (float) duty /100);//32767
            //input_data += 65535;
            //Log.d(TAG, String.valueOf("seekbar ->"+input_data));
            power[0] = (byte)(input_data & 0x00ff);
            power[1] = (byte)((input_data & 0xff00) >>> 8);
        }
        //System.out.println("power[0] -> "+ power[0]);//Hi
        //System.out.println("power[1] -> "+ power[1]);//Low

        byte[] command = {99,109,100,id,power[0],power[1],0,0,0,0};
        super.write(command);
    }

    public void led_g_switch(Boolean flag){
        byte id = 1;
        byte led = 0;
        //byte[] command = makeCommand("test");

        if(flag == Boolean.TRUE){
            led  = 1;
        }else{
            led = 0;
        }
        byte[] command = {99,109,100,id,led,0,0,0,0,0};
        super.write(command);
    }

    public void led_r_switch(Boolean flag){
        byte id = 2;
        byte led = 0;
        //byte[] command = makeCommand("test");

        if(flag == Boolean.TRUE){
            led  = 1;
        }else{
            led = 0;
        }
        byte[] command = {99,109,100,id,led,0,0,0,0,0};
        super.write(command);
    }

    public void led_g_flash(short on_time, short off_time){
        byte id = 3;
        byte[] time = {0,0,0,0};
        //Log.d("AI_CHIP", String.valueOf("on -> "+on_time));
        time[0] = (byte)(on_time  & 0x00ff);
        time[1] = (byte)((on_time & 0xff00)>>>8);
        time[2] = (byte)(off_time  & 0x00ff);
        time[3] = (byte)((off_time & 0xff00)>>>8);
        //byte[] command = makeCommand("test");
        byte[] command =  {99,109,100,id,time[0],time[1],time[2],time[3],0,0};
        super.write(command);
    }

    public void led_r_flash(short on_time, short off_time){
        byte id = 4;
        byte[] time = {0,0,0,0};
        //Log.d("AI_CHIP", String.valueOf("on -> "+on_time));
        time[0] = (byte)(on_time  & 0x00ff);
        time[1] = (byte)((on_time & 0xff00)>>>8);
        time[2] = (byte)(off_time  & 0x00ff);
        time[3] = (byte)((off_time & 0xff00)>>>8);
        //byte[] command = makeCommand("test");
        byte[] command =  {99,109,100,id,time[0],time[1],time[2],time[3],0,0};
        super.write(command);
    }

    public void angle_set(short angle){
        byte id = 5;
        byte[] set_angle = {0,0};
        if(angle >= 0){
            int input_data = (int)(32767 * (float)angle/180 );//32767
            //Log.d(TAG, String.valueOf("seekbar -> "+input_data));
            String data_string = Integer.toBinaryString(input_data);
            //Log.d("AI_CHIP", String.valueOf("data_string -> "+data_string));

            set_angle[0] = (byte)(input_data  & 0x00ff);
            set_angle[1] = (byte)((input_data & 0xff00)>>>8);

        }else{
            short input_data = (short) ((float)32767 * (float) angle /180);//32767
            //input_data += 65535;
            //Log.d(TAG, String.valueOf("seekbar ->"+input_data));
            set_angle[0] = (byte)(input_data & 0x00ff);
            set_angle[1] = (byte)((input_data & 0xff00) >>> 8);

        }

        byte[] command = {99,109,100,id,set_angle[0],set_angle[1],0,0,0,0};
        super.write(command);
    }
}
