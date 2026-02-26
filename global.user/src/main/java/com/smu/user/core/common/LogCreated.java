package com.smu.user.core.common;

import java.util.Date;

public class LogCreated {
    public static String AT() { return String.format("%tY-%tm-%td", new Date(),new Date(),new Date()); }
    public static String DATE() { return String.format("%tY-%tm-%td %tH:%tM:%tS", new Date(), new Date(), new Date(), new Date(), new Date(), new Date()); }
    public static String TIME() { return String.format("%tH:%tM", new Date(), new Date()); }
    public static String MONTH() { return String.format("%tm", new Date()); }
    public static String YEAR() { return String.format("%tY", new Date()); }
}

