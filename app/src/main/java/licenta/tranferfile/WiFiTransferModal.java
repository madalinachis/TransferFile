package licenta.tranferfile;

import java.io.Serializable;

/**
 * Created by Madalina.Chis on 4/11/2016.
 */
public class WiFiTransferModal implements Serializable {

    private String FileName;
    private Long FileLength;
    private String InetAddress;

    public WiFiTransferModal(String inetaddress) {
        this.InetAddress = inetaddress;
    }

    public WiFiTransferModal(String name, Long filelength) {
        this.FileName = name;
        this.FileLength = filelength;
    }

    public String getInetAddress() {
        return InetAddress;
    }

    public Long getFileLength() {
        return FileLength;
    }

    public String getFileName() {
        return FileName;
    }
}