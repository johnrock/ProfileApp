package com.okapp.helpers;

import android.util.Log;

import com.okapp.data.helpers.LogHelper;


/**
 * @author John Piser johnpiser@yahoo.com
 */

public class LogHelperImpl implements LogHelper {


    private final boolean DEBUG_MODE;

    public LogHelperImpl(boolean debugMode) {
        this.DEBUG_MODE = debugMode;
    }

    private void log(int loglevel, String logtag, String message) {
        if(DEBUG_MODE){
            switch (loglevel){
                case Log.DEBUG:
                    Log.d(logtag, message);
                    break;
                case Log.ERROR:
                    Log.e(logtag, message);
                    break;
                case Log.INFO:
                    Log.i(logtag, message);
                    break;
                case Log.VERBOSE:
                    Log.v(logtag,message);
            }
        }
    }

    @Override
    public void debug(String logtag, String message) {
        log(Log.DEBUG, logtag, message);
    }

    @Override
    public void error(String logtag, String message) {
        log(Log.ERROR, logtag, message);
    }

    @Override
    public void info(String logtag, String message) {
        log(Log.INFO, logtag, message);
    }

    @Override
    public void verbose(String logtag, String message) {
        log(Log.VERBOSE, logtag, message);
    }

    @Override
    public boolean debugMode() {
        return DEBUG_MODE;
    }
}
