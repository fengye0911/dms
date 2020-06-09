package com.bzdgs.dms.util;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.client.subsystem.sftp.SftpClient;
import org.apache.sshd.client.subsystem.sftp.SftpClientFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.EnumSet;

public class SshUtil {

    public void uploadSFTP(String localPath, String remotePath) throws IOException {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        ClientSession session = client.connect("root", "10.10.20.20", 22).verify().getSession();
        session.addPasswordIdentity("root");
        session.auth().verify();

        SftpClientFactory factory = SftpClientFactory.instance();
        SftpClient sftp = factory.createSftpClient(session);

        SftpClient.CloseableHandle handle = sftp.open(remotePath,
                EnumSet.of(SftpClient.OpenMode.Write, SftpClient.OpenMode.Create));
        FileInputStream in = new FileInputStream(localPath);
        int buffSize = 1024 * 1024;
        byte[] src = new byte[buffSize];
        int len;
        long fileOffset = 0L;
        while ((len = in.read(src)) != -1) {
            sftp.write(handle, fileOffset, src, 0, len);
            fileOffset += len;
        }

        in.close();
        sftp.close(handle);

        session.close(false);
        client.stop();
    }
    public void getSftp(String localPath, String remotePath) throws IOException {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        ClientSession session = client.connect("root", "10.10.20.20", 22).verify().getSession();
        session.addPasswordIdentity("root");
        session.auth().verify();

        SftpClientFactory factory = SftpClientFactory.instance();
        SftpClient sftp = factory.createSftpClient(session);

        SftpClient.CloseableHandle handle = sftp.open(remotePath,
                EnumSet.of(SftpClient.OpenMode.Write, SftpClient.OpenMode.Create));
        FileInputStream in = new FileInputStream(localPath);
        int buffSize = 1024 * 1024;
        byte[] src = new byte[buffSize];
        int len;
        long fileOffset = 0L;
        while ((len = in.read(src)) != -1) {
            sftp.write(handle, fileOffset, src, 0, len);
            fileOffset += len;
        }

        in.close();
        sftp.close(handle);

        session.close(false);
        client.stop();
    }
}
