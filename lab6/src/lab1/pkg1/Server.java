/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(5000);
        List<InetSocketAddress> clients = new ArrayList<>();

        System.out.println("Сервер запущен. Жду клиентов...");

        // ЖДЁМ 2 КЛИЕНТА 
        while (clients.size() < 2) {
            byte[] buf = new byte[1024];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            socket.receive(p);

            String msg = new String(p.getData(), 0, p.getLength());
            if (msg.equals("HELLO")) {
                clients.add(new InetSocketAddress(p.getAddress(), p.getPort()));
                System.out.println("Клиент подключён: " + p.getAddress());
            }
        }

        // ПАРАМЕТРЫ ИНТЕГРАЛА
        double low = 1;
        double up = 10;
        double step = 0.001;

        double length = (up - low) / clients.size();

        // РАЗДАЁМ ЗАДАНИЯ
        for (int i = 0; i < clients.size(); i++) {
            double a = low + i * length;
            double b = (i == clients.size() - 1) ? up : a + length;

            String task = "TASK " + a + " " + b + " " + step;
            byte[] data = task.getBytes();

            DatagramPacket out = new DatagramPacket(
                    data, data.length,
                    clients.get(i).getAddress(),
                    clients.get(i).getPort()
            );
            socket.send(out);
        }

        // ПРИНИМАЕМ РЕЗУЛЬТАТЫ
        double total = 0;
        for (int i = 0; i < clients.size(); i++) {
            byte[] buf = new byte[1024];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            socket.receive(p);

            String msg = new String(p.getData(), 0, p.getLength());
            String[] parts = msg.split(" ");
            total += Double.parseDouble(parts[1]);
        }

        System.out.println("Итоговый интеграл = " + total);
    }
}

