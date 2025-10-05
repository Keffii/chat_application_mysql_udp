package Chat;

public class ChatConfig {
    // Networksettings
    public static final String MULTICAST_IP = "234.235.236.237";
    public static final int PORT = 12540;

    // Prefix
    public static final String JOIN_PREFIX = "Join:";
    public static final String LEAVE_PREFIX = "Leave:";
    public static final String MESSAGE_PREFIX = "Message:";
    public static final String USERLIST_PREFIX = "UserList:";
    public static final String REQUEST_USERLIST = "RequestUserList";

    // Database configuration (MySQL)
    // Update these values to match your MySQL server and credentials.
    public static final String DB_URL = "jdbc:mysql://localhost:3306/chat_db?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "mysql";
}