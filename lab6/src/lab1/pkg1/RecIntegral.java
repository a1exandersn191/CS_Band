/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1.pkg1;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class RecIntegral implements Externalizable {

    private double lowLim;
    private double upLim;
    private double step;
    private double result;

    public RecIntegral() {
    }

    public RecIntegral(double lowLim, double upLim, double step) throws InvalidRec {
        if (!isValid(lowLim) || !isValid(upLim) || !isValid(step)) {
            String err = "";
            err = (!isValid(lowLim)) ? err + "Нижний предел: " + lowLim + "," : err;
            err = (!isValid(upLim)) ? err + " Верхний предел: " + upLim + "," : err;
            err = (!isValid(step)) ? err + " Шаг: " + step : err;
            throw new InvalidRec("Значения должны быть в диапазоне от 0.000001 до 1 000 000. Вы ввели: " + err);
        }
        if (upLim < lowLim) throw new InvalidRec("Нижний предел больше верхнего");

        this.lowLim = lowLim;
        this.upLim = upLim;
        this.step = step;
        this.result = 0.0;
    }

    public RecIntegral(double lowLim, double upLim, double step, double result) throws InvalidRec {
        if (!isValid(lowLim) || !isValid(upLim) || !isValid(step)) {
            String err = "";
            err = (!isValid(lowLim)) ? err + "Нижний предел: " + lowLim + "," : err;
            err = (!isValid(upLim)) ? err + " Верхний предел: " + upLim + "," : err;
            err = (!isValid(step)) ? err + " Шаг: " + step : err;
            throw new InvalidRec("Значения должны быть в диапазоне от 0.000001 до 1 000 000. Вы ввели: " + err);
        }
        if (upLim < lowLim) throw new InvalidRec("Нижний предел больше верхнего");

        this.lowLim = lowLim;
        this.upLim = upLim;
        this.step = step;
        this.result = result;
    }

    private boolean isValid(double value) {
        return value >= 0.000001 && value <= 1_000_000;
    }

    public double getLowLim() { return lowLim; }
    public double getUpLim() { return upLim; }
    public double getStep() { return step; }
    public double geResult() { return result; }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(lowLim);
        out.writeDouble(upLim);
        out.writeDouble(step);
        out.writeDouble(result);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        lowLim = in.readDouble();
        upLim = in.readDouble();
        step = in.readDouble();
        result = in.readDouble();
    }

    public double CalcIntegral(double lowLim, double upLim, double step) {
        double start = lowLim;
        double sumS = 0;

        do {
            double h = Math.min(step, (upLim - start));
            sumS += h * (1 / start + 1 / (start + h)) / 2;
            start += h;
        } while (start < upLim);

        return sumS;
    }
}


