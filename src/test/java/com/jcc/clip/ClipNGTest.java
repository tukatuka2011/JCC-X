/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcc.clip;

import java.io.File;
import java.util.logging.Level;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author zov
 */
public class ClipNGTest {

    public ClipNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Тест метод fillInfo, класса Clip.
     */
    @Test
    public void testFillInfo() {

        System.out.println("fillInfo");
        Clip clip = new Clip();
        clip.LOG.setLevel(Level.ALL);
        clip.file = new File("C:\\Users\\zov\\Downloads\\Раздача\\Elizabeth Rollings\\BBW Hunter.wmv");
        clip.fillInfo();
        assertEquals(clip.getVideoInfo(), "Video: VC-1, 640x480, 30.000 fps, 1 900 Kbps");
        assertEquals(clip.getAudioInfo(), "Audio: WMA, 44.1 KHz, 160 Kbps, 2 channels");

        clip = new Clip();
        clip.file = new File("C:\\Users\\zov\\Downloads\\Раздача\\Elizabeth Rollings\\Broken_dishwasher.mpg");
        clip.fillInfo();
        assertEquals(clip.getVideoInfo(), "Video: MPEG Video, 352x240, 29.970 fps, 1 536 Kbps");
        assertEquals(clip.getAudioInfo(), "Audio: MPEG Audio, 44.1 KHz, 64.0 Kbps, 2 channels");

        clip = new Clip();
        clip.file = new File("C:\\Users\\zov\\Downloads\\Раздача\\Elizabeth Rollings\\Elizabeth Rollings 1.avi");
        clip.fillInfo();
        assertEquals(clip.getVideoInfo(), "Video: MPEG-4 Visual, 544x416, 29.970 fps, 1 354 Kbps");
        assertEquals(clip.getAudioInfo(), "Audio: MPEG Audio, 44.1 KHz, 89.7 Kbps, 2 channels");

    }

    /**
     * Тест метод getVideoInfo, класса Clip.
     */
    @Test
    public void testGetVideoInfo() {
        System.out.println("getVideoInfo");
        Clip clip = new Clip();
        clip.videoCodec = "MPEG-4 Visual";
        clip.videoWidth = "544";
        clip.videoHeight = "416";
        clip.videoFramerate = "29.970";
        clip.videoBitrate = "1 354 Kbps";
        assertEquals(clip.getVideoInfo(), "Video: MPEG-4 Visual, 544x416, 29.970 fps, 1 354 Kbps");
    }

    /**
     * Тест метод getAudioInfo, класса Clip.
     */
    @Test
    public void testGetAudioInfo() {
        System.out.println("getAudioInfo");
        Clip clip = new Clip();
        clip.audioCodec = "MPEG Audio";
        clip.audioSRate = "44.1 KHz";
        clip.audioBitrate = "89.7 Kbps";
        clip.audioQuality = "2 channels";
        assertEquals(clip.getAudioInfo(), "Audio: MPEG Audio, 44.1 KHz, 89.7 Kbps, 2 channels");
    }

}
