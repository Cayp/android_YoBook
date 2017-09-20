package com.example.book.Tools;

/**
 * Created by ljp on 2017/7/21.
 */

public class UrlHelper {

    private static final String BASE_URL = "http://139.199.165.150/Book/";
    public static final String LOGIN_URL = BASE_URL + "Home/User/login";
    public static final String REGISTER_URL = BASE_URL + "Home/User/register";
    public static final String CHECKCODE_URL = BASE_URL + "Home/User/getVerificationCode";
    public static final String RESET_BASEMSG_URL = BASE_URL + "Home/User/modifyUserInfo";
    public static final String LOGOUT_URL = BASE_URL + "Home/User/logout";
    public static final String RESET_PASWRD = BASE_URL + "Home/User/modifyPassword";
    public static final String UPLOADHEADBIMP = BASE_URL + "Home/User/uploadAvatar";
    public static final String GETFAN = BASE_URL + "Home/Fan/getFan";
    public static final String GETFOLLOW = BASE_URL + "Home/Fan/getFollow";
    public static final String GETSECONDBOOKCOVER = BASE_URL + "SecondBook/";
    public static final String GETALLSECONDBOOK = BASE_URL + "Home/SecondBook/getAllSecondBook";
    public static final String GETAVATAR = BASE_URL + "Avatar/";
    public static final String GETUSERINFO = BASE_URL + "Home/User/getUserInfo";

}
