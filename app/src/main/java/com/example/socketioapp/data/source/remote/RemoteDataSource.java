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

package com.example.socketioapp.data.source.remote;

import com.example.socketioapp.data.ChatMessage;
import com.example.socketioapp.data.source.DataSource;
import com.example.socketioapp.eventservice.EventListener;
import com.example.socketioapp.eventservice.EventService;
import com.example.socketioapp.eventservice.EventServiceImpl;

import java.net.URISyntaxException;

import io.reactivex.Flowable;

/**
 * Remote data source.
 *
 */
public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;
    private static EventService mEventService = EventServiceImpl.getInstance();
    private EventListener mRepoEventListener;

    private RemoteDataSource() {
        mEventService.setEventListener(this);
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void onConnect(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onDisconnect(args);
    }

    @Override
    public void onConnectError(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnectError(args);
    }

    @Override
    public void onConnectTimeout(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnectTimeout(args);
    }

    @Override
    public void onNewMessage(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onNewMessage(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onUserJoined(args);
    }

    @Override
    public void onUserLeft(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onUserLeft(args);
    }

    @Override
    public void onTyping(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onTyping(args);
    }

    @Override
    public void onStopTyping(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onStopTyping(args);
    }

    @Override
    public void setEventListener(EventListener eventListener) {
        mRepoEventListener = eventListener;
    }

    @Override
    public Flowable<ChatMessage> sendMessage(ChatMessage chatMessage) {
        return mEventService.sendMessage(chatMessage);
    }

    @Override
    public void onTyping() {
        mEventService.onTyping();
    }

    @Override
    public void onStopTyping() {
        mEventService.onStopTyping();
    }

    @Override
    public void connect(String username) throws URISyntaxException {
        mEventService.connect(username);
    }

    @Override
    public void disconnect() {
        mEventService.disconnect();
    }
}
