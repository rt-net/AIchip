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

    /**
     * Write to the connected OutStream.
     *
     * @param buffer The bytes to write
     */
    public void write(byte[] buffer) {
        byte[] command = {99,109,100,0,100,100,0,0,0,0};
        super.write(command);
    }

}
