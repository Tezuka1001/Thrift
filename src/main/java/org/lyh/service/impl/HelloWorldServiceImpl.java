package org.lyh.service.impl;

import org.lyh.service.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {

    @Override
    public String say(String username) {
        return "hello " + username;
    }
}
