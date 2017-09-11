package util;

import org.junit.Assert;
import org.junit.Test;

public class TestUtils {

    @Test
    public void testIsClockSharp() {
        Assert.assertTrue(Utils.isClockSharp("14:00"));
        Assert.assertFalse(Utils.isClockSharp("11:11"));
    }

    @Test
    public void testParseHour() {
        Assert.assertEquals(Utils.parseHour("11:11"), -1);
        Assert.assertEquals(Utils.parseHour("11:00"), 11);
        Assert.assertEquals(Utils.parseHour("11"), -1);
    }

    @Test
    public void testParseLocalDate() {
        Assert.assertNull(Utils.parseLocalDate("2017-2-29"));
        Assert.assertNotNull(Utils.parseLocalDate("2017-2-2"));
        Assert.assertNull(Utils.parseLocalDate("fdsae"));
    }
}
