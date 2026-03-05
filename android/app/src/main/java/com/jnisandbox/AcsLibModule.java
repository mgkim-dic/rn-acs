package com.jnisandbox;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.novax.acslib.core.ACS;
import com.novax.acslib.api.geo.AcsCoordinateConverter;
import com.novax.acslib.api.geo.CoordinateType;
import com.novax.acslib.api.geo.Coordinate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class AcsLibModule extends ReactContextBaseJavaModule {

    private AcsCoordinateConverter acsCoordConverter = ACS.coordinateConverter().create();
    private static final String MODULE_NAME = "AcsLibModule";

    private Context mContext;

    public AcsLibModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void format(double lat, double lon, String coordTypeStr, Promise promise) {
        try {
            CoordinateType coordType = CoordinateType.from(coordTypeStr);
            String coord = acsCoordConverter.format(lat, lon, coordType);
            promise.resolve(coord);
        } catch (Exception e) {
            promise.reject("CONVERSION_ERROR", "Failed to convert coordinates: " + e.getMessage());
        }
    }

}