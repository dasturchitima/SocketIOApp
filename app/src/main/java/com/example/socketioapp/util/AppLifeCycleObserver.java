/*
 * Copyright 2018 Mayur Rokade
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package com.example.socketioapp.util;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.widget.Toast;


import com.example.socketioapp.eventservice.EventServiceImpl;

import java.net.URISyntaxException;

/**
 * Closes the socket connection when app is in background and
 * connects to socket when the app is in foreground.
 */
public class AppLifeCycleObserver implements LifecycleObserver {

    private Context mContext;

    /**
     * Use this constructor to create a new AppLifeCycleObserver
     *
     * @param context
     */
    public AppLifeCycleObserver(Context context) {
        mContext = context;
    }

    /**
     * When app enters foreground
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        try {
            EventServiceImpl.getInstance().connect(User.getUsername());
        } catch (URISyntaxException e) {
            Toast.makeText(mContext, "Failed to connect to chat server.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * When app enters background
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        EventServiceImpl.getInstance().disconnect();
    }
}
