package com.coherentsolutions.store.http.server;

import com.sun.net.httpserver.BasicAuthenticator;

public class Auth extends BasicAuthenticator {
    public Auth(String get) {
        super(get);
    }

    @Override
    public boolean checkCredentials(String user, String password) {
        return user.equals("admin") && password.equals("admin");
    }
}
