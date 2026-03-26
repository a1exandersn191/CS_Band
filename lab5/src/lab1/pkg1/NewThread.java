/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        double result = rec.CalcIntegral(
                rec.getLowLim(),
                rec.getUpLim(),
                rec.getStep()
        );
        rec.setResult(result);

        javax.swing.SwingUtilities.invokeLater(() -> {
            model.setValueAt(result, rowIndex, 3);
        });
    }
}


