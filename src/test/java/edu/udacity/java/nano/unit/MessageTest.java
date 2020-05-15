package edu.udacity.java.nano.unit;

import edu.udacity.java.nano.chat.Message;
import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testCreateMessage(){

        Message message = new Message("username", "JOIN", 1);

        Assert.assertNotNull(message);
    }

    @Test
    public void testUpdateMsgType(){

        Message message = new Message("username", "JOIN", 1);

        message.setType("JOIN");
        message.setMsg("Hello!");

        Assert.assertEquals(message.getType(), "JOIN");
        Assert.assertEquals(message.getMsg(), "Hello!");
    }

    @Test
    public void testUpdateUsername(){

        Message message = new Message("username", "JOIN", 1);

        message.setUsername("Dan");

        Assert.assertEquals(message.getUsername(), "Dan");
    }


    @Test
    public void testUpdateOnlineCount(){

        Message message = new Message("username", "JOIN", 1);

        message.setOnlineCount(3);

        Assert.assertEquals(message.getOnlineCount(), 3);
    }
}
