package com.tfl.billing;

import com.oyster.OysterCard;
import com.oyster.ScanListener;

import java.util.UUID;

public interface CardReader {
    public void register(ScanListener scanListener);
    public void touch(OysterCard card);
    public UUID id();
}
