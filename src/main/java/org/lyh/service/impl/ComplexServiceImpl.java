package org.lyh.service.impl;

import org.apache.thrift.TException;
import org.lyh.service.ComplextService;

/**
 * @author lyh
 * @version 2019-10-25 10:52
 */
public class ComplexServiceImpl implements ComplextService.Iface {

    @Override
    public String getFullName(String firstName, String sceondName) throws TException {
        return firstName + " : " + sceondName;
    }
}
