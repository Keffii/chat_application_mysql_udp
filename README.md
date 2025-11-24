# GUI Chat Application - With MySQL Integration

A real-time chat application where multiple users can talk to each other. Messages are saved to a database and sent instantly to everyone on the network.

<img width="546" height="393" alt="Preview" src="https://github.com/user-attachments/assets/4c13753c-c2ef-4457-b217-4f248bf10566" />

## What does it do?

- Send and receive messages in real-time
- See who's online
- All chat history is saved automatically
- Secure database handling prevents SQL injection

## Requirements

- Java 8 or newer
- MySQL database
- MySQL JDBC Driver

## Setup

1. Create a MySQL database:
```sql
CREATE DATABASE chat_db;
```

2. Update your database password in `src/Chat/ChatConfig.java`:
```java
public static final String DB_PASS = "your_password";
```

3. Run the application from your IDE or command line:

```powershell
java -cp "mysql-connector-java.jar;src" Chat.ChatMain
```

## How to use

1. Launch the app and enter your username
2. Start chatting - your messages appear for everyone connected
3. Run multiple instances to test with different users

**What you'll see:**
```
[System] Welcome to the chat!
[System] Alice has joined the chat
[Alice] Hey everyone!
[System] Bob has joined the chat
[Bob] Hi Alice!
```

## Troubleshooting

- Make sure MySQL is running
- Check your password in `ChatConfig.java`
- Ensure all users are on the same network

## Known issues
- Multiple entries in the database for chat messages
