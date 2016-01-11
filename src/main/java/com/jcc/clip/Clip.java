/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcc.clip;

import com.jcc.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zov
 */
public class Clip {

    public String name;
    public String info;
    public File screenList;
    public File file;
    static final Logger LOG = Logger.getLogger(Clip.class.getName());
    String videoCodec;
    String videoBitrate;
    String lenght;
    String videoHeight;
    String videoWidth;
    String videoFramerate;
    String size;
    String audioCodec;
    String audioBitrate;
    String audioQuality;
    String audioSRate;

    public String getVideoInfo() {
        return "Video: " + videoCodec + ", " + videoWidth + "x" + videoHeight + ", " + videoFramerate + " fps, " + videoBitrate;
    }

    public String getAudioInfo() {
        return "Audio: " + audioCodec + ", " + audioSRate + ", " + audioBitrate + ", " + audioQuality;
    }

    public void fillInfo() {
        List<String> command = new ArrayList<>();
        command.add("C:\\Program Files\\MediaInfo_CLI\\MediaInfo.exe");
        command.add("--Full");
        command.add("--Output=XML");
        command.add(file.getAbsolutePath());
        ProcessBuilder builder = new ProcessBuilder(command);

        try {
            LOG.info("Run command: " + command.toString());
            final Process process = builder.start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while (((line = br.readLine()) != null) && (!line.startsWith("<Format>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            while (((line = br.readLine()) != null) && (!line.startsWith("<File_size>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            size = line;

            while (((line = br.readLine()) != null) && (!line.startsWith("<Duration>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            lenght = line;

            while (((line = br.readLine()) != null) && (!line.startsWith("<track type=\"Video\">"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);

            while (((line = br.readLine()) != null) && (!line.startsWith("<Format>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            videoCodec = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null) && (!line.startsWith("<Bit_rate>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            videoBitrate = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null) && (!line.startsWith("<Width>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            videoWidth = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null) && (!line.startsWith("<Height>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            videoHeight = line.substring(line.indexOf(">") + 1, line.indexOf("</"));

            while (((line = br.readLine()) != null) && (!line.startsWith("<Frame_rate>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            videoFramerate = line.substring(line.indexOf(">") + 1, line.indexOf("</"));

            while (((line = br.readLine()) != null) && (!line.startsWith("<track type=\"Audio\">"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);

            while (((line = br.readLine()) != null) && (!line.startsWith("<Format>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            audioCodec = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null) && (!line.startsWith("<Bit_rate>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            audioBitrate = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null) && (!line.startsWith("<Channel_s_>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            audioQuality = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null) && (!line.startsWith("<Sampling_rate>"))) {
                LOG.log(Level.ALL, line);
            }
            LOG.log(Level.ALL, line);
            line = br.readLine();
            LOG.log(Level.ALL, line);
            audioSRate = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
            while (((line = br.readLine()) != null)) {
                LOG.log(Level.ALL, line);
            }
            LOG.info("Command compleeted!");
            LOG.info(getVideoInfo());
            LOG.info(getAudioInfo());

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error on runing MediaInfo", ex);
        }

    }
}
