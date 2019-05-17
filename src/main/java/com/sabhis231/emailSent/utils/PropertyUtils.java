/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis231.emailSent.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Sabhis231
 */
public class PropertyUtils {

    public String GetPropertySec(String Property) {
        String file = "constraint.properties";
        Properties prop;
        InputStream inputStream;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(file);
            if (inputStream != null) {
                prop = new Properties();
                prop.load(inputStream);
                return prop.getProperty(Property);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
