/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1.pkg1;

public class RecIntegral {

    private double lowLim;
    private double upLim;
    private double step;
    private double result;

    public RecIntegral(double lowLim, double upLim, double step) throws InvalidRec {
        if (!isValid(lowLim) || !isValid(upLim) || !isValid(step)) {
            throw new InvalidRec("Значения должны быть в диапазоне от 0.000001 до 1 000 000");
        }

        this.lowLim = lowLim;
        this.upLim = upLim;
        this.step = step;
        this.result = 0.0;
    }

    public RecIntegral(double lowLim, double upLim, double step, double result) throws InvalidRec {
        if (!isValid(lowLim) || !isValid(upLim) || !isValid(step)) {
            throw new InvalidRec("Значения должны быть в диапазоне от 0.000001 до 1 000 000");
        }

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

