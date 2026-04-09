package lab1.pkg1;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class NewThread extends Thread {

    private final RecIntegral rec;
    private final int rowIndex;
    private final DefaultTableModel model;

    public NewThread(RecIntegral rec, int rowIndex, DefaultTableModel model) {
        this.rec = rec;
        this.rowIndex = rowIndex;
        this.model = model;
    }

    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "Поток открылся");

        double low = rec.getLowLim();
        double up = rec.getUpLim();
        double step = rec.getStep();

        int parts = 5;


        int totalSteps = (int) Math.ceil((up - low) / step);

        Thread[] threads = new Thread[parts];
        double[] results = new double[parts];

        int startStep = 0;

 
        for (int i = 0; i < parts; i++) {

            int count = totalSteps / parts + (i < totalSteps % parts ? 1 : 0);

            int threadStart = startStep;
            int threadEnd = startStep + count;
            startStep += count;

            final int index = i;

       
            threads[i] = new Thread(() -> {
                double sum = 0;

                for (int s = threadStart; s < threadEnd; s++) {
                    double x1 = low + s * step;
                    double x2 = Math.min(x1 + step, up);

                    sum += (x2 - x1) * (1 / x1 + 1 / x2) / 2;
                }

                results[index] = sum;
            });

            threads[i].start();
        }

       
        double total = 0;
        try {
            for (int i = 0; i < parts; i++) {
                threads[i].join();
                total += results[i];
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rec.setResult(total);


        final double finalTotal = total;

        javax.swing.SwingUtilities.invokeLater(() -> {
            model.setValueAt(finalTotal, rowIndex, 3);
        });
    }
}





