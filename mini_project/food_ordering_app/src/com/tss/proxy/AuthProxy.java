package com.tss.proxy;

import com.tss.exception.AppException;

public interface AuthProxy<Proxies> {
    Proxies authenticate(String username, String password) throws AppException;
}