package utils;

import api.AuthApi;

public class AuthenticationUtil {

    public static String getToken() {
        return new AuthApi().getToken();
    }
}
