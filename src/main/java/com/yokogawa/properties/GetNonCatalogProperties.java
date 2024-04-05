package com.yokogawa.properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class GetNonCatalogProperties {
    public void method() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\YokogawaAsiaPrivateLimited\\YEA");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        System.out.println(properties.getProperty("user.dir"));
    }
}
