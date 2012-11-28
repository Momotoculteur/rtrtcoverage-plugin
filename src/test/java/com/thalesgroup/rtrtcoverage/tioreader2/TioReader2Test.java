package com.thalesgroup.rtrtcoverage.tioreader2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class TioReader2Test {

    @Test
    public void testReadingFile() throws Exception {

        File file = null;
        InputStream ips = null;
        try {
            file = new File(this.getClass()
                    .getResource("CLASS_TOSMANAGERCOMPONENT.TIO").getPath());
            ips = new FileInputStream(file);
        } catch (final FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (final IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        final TioReader2 tioReader2 = new TioReader2(ips);
        Exception exception = null;
        TestSuiteTrace testSuiteTrace = null;
        try {
            testSuiteTrace = tioReader2.readTio();
            testSuiteTrace.setName(file.getName());
        } catch (final TioException e) {
            exception = e;
        } finally {
            Assert.assertNotNull(testSuiteTrace);
        }
        Assert.assertNotNull(testSuiteTrace.getTestTraces());
        Assert.assertEquals(14, testSuiteTrace.getTestTraces().size());
        Assert.assertEquals("13", testSuiteTrace.getTestTraces().get(2)
                .getFileTraces("41566919", "1e207343").get(0).getTraces().get(7).getId());
        Assert.assertEquals("TP", testSuiteTrace
                .getTestTraces().get(1).getFileTraces("41566919", "1e207343")
                .get(0).getTraces().get(0).getMark());

    }

}
