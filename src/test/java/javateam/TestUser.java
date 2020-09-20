package javateam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {

    @Test
    public void testUsername() {
        User.getInstance().setData("username", 13, true);
        assertEquals("username", User.getInstance().getUsername());
    }

    @Test
    public void testUserId() {
        User.getInstance().setData("username", 13, true);
        assertEquals(13, User.getInstance().getUserId());
    }

    @Test
    public void testIsAdmin() {
        User.getInstance().setData("username", 13, true);
        assertEquals(true, User.getInstance().getIsAdmin());
    }

    @Test
    public void testIdAsString() {
        User.getInstance().setData("username", 13, true);
        assertEquals("13", User.getInstance().getUserIdAsString());
    }

    @Test
    public void testIsAdminAsString() {
        User.getInstance().setData("username", 13, true);
        assertEquals("1", User.getInstance().getIsAdminAsString());
    }
}