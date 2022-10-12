package apiumhub.kata.over.infraestructure;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RemoteTask implements Runnable {

    Logger logger = LoggerFactory.getLogger(RemoteConnection.class);

    private String user;
    private String pass;
    private String ip;

    public RemoteTask(String user, String pass, String ip) {
        this.ip = ip;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void run() {
        logger.info("Connecting to: " + ip);

        JSch jSch = new JSch();
        Session session = null;
        try {
            session = jSch.getSession(user, ip, 22);
            session.setPassword(pass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            channel.setCommand(
                    "for((i=1;i<=10000;i+=2)); do echo \"Long output - $i\"; done ; " +
                            "echo error output >&2");

            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
            ByteArrayOutputStream errorBuffer = new ByteArrayOutputStream();

            InputStream in = channel.getInputStream();
            InputStream err = channel.getExtInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    outputBuffer.write(tmp, 0, i);
                }
                while (err.available() > 0) {
                    int i = err.read(tmp, 0, 1024);
                    if (i < 0) break;
                    errorBuffer.write(tmp, 0, i);
                }
                if (channel.isClosed()) {
                    if ((in.available() > 0) || (err.available() > 0)) continue;
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }

            System.out.println("output: " + outputBuffer.toString(StandardCharsets.UTF_8));

            channel.disconnect();

        } catch (Exception e) {
            logger.info("Error when connectiong to: " + ip);
        } finally {
            session.disconnect();
            logger.info("Disconnecting from: " + ip);
        }

    }
}
