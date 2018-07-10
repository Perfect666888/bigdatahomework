package Day7_5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Demo01udpget {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(8888);

        //创建容器
        byte[] buf = new byte[1024];
        DatagramPacket dgp = new DatagramPacket(buf, buf.length);

        //接受数据
        server.receive(dgp);
        //读取数据
        System.out.println("from" + dgp.getAddress().getHostAddress() + "的消息:" + new String(dgp.getData(), 0, dgp.getLength()));


    }
}
