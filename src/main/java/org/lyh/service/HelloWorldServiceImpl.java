package org.lyh.service;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {

    @Override
    public String say(String username) {
        return "hello " + username;
    }
}
