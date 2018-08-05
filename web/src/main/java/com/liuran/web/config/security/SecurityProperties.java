package com.liuran.web.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="security.cas") //接收application.yml中的myProps下面的属性
public class SecurityProperties {
    private String server;
    private String client;
    private String disable;
    private String ignore;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String[] getIgnore() {
        String[] array;
        if ("true".equals(disable)){
            array = new String[]{"/**/**"};
        } else {
            if (ignore == null){
                array = new String[]{"/assets/**"};
            } else {
                array = ignore.split(",");
            }
        }
        return array;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    public String getClientSecurity(){
        return client + "/login/cas";
    }

    public String getServerLogin(){
        return server + "/login";
    }

    public String getServerLogout(){
        return server + "/logout";
    }
}
