package com.lluengo.bci.utils;

@FunctionalInterface
public interface RegExInterface {
    public boolean match (String field, String regex);
}
