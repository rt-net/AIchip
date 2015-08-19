package com.example.android.bluetoothchat;

/**
 * Created by nakagawayuki on 15/08/19.
 */

import java.lang.String;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;
import android.speech.RecognizerIntent;
import android.content.ActivityNotFoundException;

import com.example.android.common.logger.Log;

public class AI_CHIP extends BluetoothChatService{


    /**
     * Constructor. Prepares a new BluetoothChat session.
     *
     * @param context The UI Activity Context
     * @param handler A Handler to send messages back to the UI Activity
     */
    public AI_CHIP(Context context, Handler handler) {
        super(context, handler);
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
            Log.d("AI_CHIP", String.valueOf("seekbar -> "+input_data));
            String data_string = Integer.toBinaryString(input_data);
            //Log.d("AI_CHIP", String.valueOf("data_string -> "+data_string));

            power[0] = (byte)((input_data & 0xff00)>>>8);
            power[1] = (byte)(input_data  & 0x00ff);
        }else{
            short input_data = (short) ((float)32766 * (float) duty /100);//32767
            //input_data += 65535;
            Log.d("AI_CHIP", String.valueOf("seekbar ->"+input_data));
            power[0] = (byte)(input_data & 0x00ff);
            power[1] = (byte)((input_data & 0xff00) >>> 8);
        }
        System.out.println("power[0] -> "+ power[0]);//Hi
        System.out.println("power[1] -> "+ power[1]);//Low

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
            Log.d("AI_CHIP", String.valueOf("seekbar -> "+input_data));
            String data_string = Integer.toBinaryString(input_data);
            //Log.d("AI_CHIP", String.valueOf("data_string -> "+data_string));

            set_angle[0] = (byte)(input_data  & 0x00ff);
            set_angle[1] = (byte)((input_data & 0xff00)>>>8);

        }else{
            short input_data = (short) ((float)32767 * (float) angle /180);//32767
            //input_data += 65535;
            Log.d("AI_CHIP", String.valueOf("seekbar ->"+input_data));
            set_angle[0] = (byte)(input_data & 0x00ff);
            set_angle[1] = (byte)((input_data & 0xff00) >>> 8);

        }

        byte[] command = {99,109,100,id,set_angle[0],set_angle[1],0,0,0,0};
        super.write(command);
    }
}
