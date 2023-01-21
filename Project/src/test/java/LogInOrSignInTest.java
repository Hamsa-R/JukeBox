import com.jukebox.JukeBox;
import com.jukebox.Songs;
import com.userdetails.LogInOrSignIn;
import com.userdetails.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LogInOrSignInTest {
    LogInOrSignIn ls=null;
    JukeBox jb=null;
    @Before
    public void setup() throws SQLException {
        ls=new LogInOrSignIn();
        jb=new JukeBox();
    }
    @After
    public void teardown() {
        ls=null;
        jb=null;
    }
    @Test
    public void getUserDetailsInListSuccess() throws SQLException {
        List<User> users=ls.getUserDetailsInList();
        Iterator<User> iterator=users.iterator();
        assertEquals("Alia",iterator.next().getUserId());
    }
    @Test
    public void addingSongsInListSuccess() throws SQLException {
        List<Songs> songs=jb.addingSongsInList();
        Iterator<Songs> iterator=songs.iterator();
        assertEquals(1,iterator.next().getSongId());
    }
}
