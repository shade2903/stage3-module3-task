package com.mjc.school.controller.constants;

public class MenuConstants {
    private MenuConstants(){

    }
    public static final String START_MENU_TEXT =
            """
                    Enter the number of operation:
                    1 - Get all news.
                    2 - Get all authors.
                    3 - Get news by id.
                    4 - Get author by id.
                    5 - Create news.
                    6 - Create author.
                    7 - Update news.
                    8 - Update author.
                    9 - Remove news by id.
                    10 - Remove author by id.
                    0 - Exit.
                    """;

    public static final String AUTHOR = "Author";
    public static final String NEWS = "News";

    public static final String ENTER_ID = "Enter news id:";
    public static final String ENTER_TITLE = "Enter title:";
    public static final String ENTER_CONTENT = "Enter content:";
    public static final String ENTER_AUTHOR_ID = "Enter author id:";
    public static final String ENTER_AUTHOR_NAME = "Enter author name:";

    public static final String OPERATION_GET_ALL = "Operation: Get all %s.";
    public static final String OPERATION_GET_BY_ID = "Operation: Get %s by id.";
    public static final String OPERATION_CREATE = "Operation: Create %s.";
    public static final String OPERATION_UPDATE = "Operation: Update %s.";
    public static final String OPERATION_DELETE = "Operation: Delete %s.";
    public static final String OPERATION_EXIT = "Bye!";
    public static final String OPERATION_ERROR = "Incorrect command!";
}
