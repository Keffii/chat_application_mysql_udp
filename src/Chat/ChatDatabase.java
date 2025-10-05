package Chat;

import java.sql.*;
import java.time.LocalDateTime;

// Simple JDBC helper for MySQL to persist users and messages
public class ChatDatabase implements AutoCloseable {
    private Connection conn;

    public ChatDatabase() throws SQLException {
        conn = DriverManager.getConnection(ChatConfig.DB_URL, ChatConfig.DB_USER, ChatConfig.DB_PASS);
        ensureSchema();
    }

    private void ensureSchema() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(100) UNIQUE NOT NULL, " +
                    "joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS messages (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "sender VARCHAR(100) NOT NULL, " +
                    "message TEXT NOT NULL, " +
                    "sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
        }
    }

    public void addUser(String username) {
        String sql = "INSERT IGNORE INTO users (username) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user to DB: " + e.getMessage());
        }
    }

    public void removeUser(String username) {
        // optional: we keep user history, so we won't delete the row; instead we could mark offline
        // For now, no-op or we can delete
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing user from DB: " + e.getMessage());
        }
    }

    public void saveMessage(String sender, String message) {
        String sql = "INSERT INTO messages (sender, message, sent_at) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sender);
            ps.setString(2, message);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving message to DB: " + e.getMessage());
        }
    }

    // Optional helper to query last N messages
    public ResultSet getLastMessages(int limit) throws SQLException {
        String sql = "SELECT sender, message, sent_at FROM messages ORDER BY sent_at DESC LIMIT ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, limit);
        return ps.executeQuery();
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }
}
