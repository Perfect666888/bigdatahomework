package Day7_5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Demo02udpsend {
    public static void main(String[] args) throws IOException {
        //创建发送对象
        DatagramSocket client =new DatagramSocket();

        //创建封装数据包
        byte[] buf ="hello IDEA".getBytes();
        DatagramPacket dgp =new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.1.24"),8888);

        //发送数据包
        client.send(dgp);

        //释放资源
        client.close();
    }
}
