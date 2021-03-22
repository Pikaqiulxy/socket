import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class socket1 {
    public static void main(String[] args) {

        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = df.format(new Date());
        System.out.println(name);

        try {
            // 初始化服务端socket并且绑定9999端口
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("!");
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            System.out.println("!!");
            //获取输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("!!!");
            //读取一行数据
            String str = bufferedReader.readLine();

            //通过while循环不断读取信息，
            while ((str = bufferedReader.readLine())!=null){
                //将图片解析出来
                //对字节数组字符串进行Base64解码并生成图片
                if (str == null){
                    System.out.println("stop!"); //图像数据为空
                }
                else{
                    BASE64Decoder decoder = new BASE64Decoder();
                    //Base64解码
                    byte[] b = decoder.decodeBuffer(str);
                    for(int i=0;i<b.length;++i){
                        if(b[i]<0){
                            //调整异常数据
                            b[i]+=256;
                        }
                    }
                    //生成jpeg图片
                    //String imgFilePath = "/usr/local/img/" + name + ".jpg";//新生成的图片
                    String imgFilePath = "/Users/luoxinyue/Desktop" + name + ".jpg";//新生成的图片
                    OutputStream out = new FileOutputStream(imgFilePath);
                    out.write(b);
                    out.flush();
                    out.close();
                    System.out.println("Hello World!");
                }
                //输出打印
                // System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("stop!");
    }
}