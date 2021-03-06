package com.example.book.Tools;

/**
 * Created by ljp on 2017/7/21.
 */

public class UrlHelper {

    private static final String BASE_URL = "http://119.29.143.189/Book/";
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
    public static final String GETSECONDBOOKBYTYPE = BASE_URL + "Home/SecondBook/getSecondBookByType";
    public static final String GETALLTYPE = BASE_URL + "Home/Type/getAllType";
    public static final String PUBLISHSECBOOK = BASE_URL + "Home/SecondBook/publishBook";
    public static final String GETSHARECOVER = BASE_URL + "Share/";
    public static final String GETALLSHARE = BASE_URL + "Home/Share/getAllShare";
    public static final String GETCOMMENT = BASE_URL + "Home/ShareComment/getShareComment";
    public static final String WRITECOMMENT = BASE_URL + "Home/ShareComment/addShareComment";
    public static final String GETSHARESTAR = BASE_URL + "Home/Star/getShareStar";
    public static final String ADDSTAR = BASE_URL + "Home/Star/addStar";
    public static final String REMOVESTAR = BASE_URL + "Home/Star/removeStar";
    public static final String DOUBANINTERFACE = "https://api.douban.com/v2/book/isbn/";
    public static final String PUBLISHSHARE = BASE_URL +"Home/Share/publishShare";
    
}
