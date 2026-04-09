/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testclient;

import java.net.*;

public class Testclient {

    public static void main(String[] args) throws Exception {

        String serverIp = "127.0.0.1";
        DatagramSocket socket = new DatagramSocket();

        // Регистрируемся один раз
        socket.send(new DatagramPacket(
                "HELLO".getBytes(), 5,
                InetAddress.getByName(serverIp), 5000
        ));
        System.out.println("Клиент зарегистрирован");

    
        while (true) {

        
            byte[] buf = new byte[1024];
            DatagramPacket task = new DatagramPacket(buf, buf.length);
            socket.receive(task);

            String msg = new String(task.getData(), 0, task.getLength());
            String[] t = msg.split(" ");

            if (!t[0].equals("TASK")) {
                System.out.println("Неизвестная команда: " + msg);
                continue;
            }

            double low = Double.parseDouble(t[1]);
            double up = Double.parseDouble(t[2]);
            double step = Double.parseDouble(t[3]);

            System.out.println("Получена задача: " + low + " " + up + " " + step);

            // Считаем
            double result = calc(low, up, step);

            // Отправляем результат на порт 5001
            String resp = "RESULT " + result;
            socket.send(new DatagramPacket(
                    resp.getBytes(), resp.length(),
                    InetAddress.getByName(serverIp), 5001
            ));

            System.out.println("Результат отправлен: " + result);
        }
    }

    public static double calc(double low, double up, double step) {
        double sum = 0;
        for (double x = low; x < up; x += step) {
            double x2 = Math.min(x + step, up);
            sum += (x2 - x) * (1 / x + 1 / x2) / 2;
        }
        return sum;
    }
}

