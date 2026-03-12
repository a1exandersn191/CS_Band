/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1.pkg1;

public class RecIntegral {
    
    private  double lowLim;
    private  double upLim;
    private  double step;
    private double result;
    
    public RecIntegral(double lowLim, double upLim, double step){
        this.lowLim = lowLim;
        this.upLim = upLim;
        this.step = step;
        this.result = 0.0;
    }
        public RecIntegral(double lowLim, double upLim, double step, double result){
        this.lowLim = lowLim;
        this.upLim = upLim;
        this.step = step;
        this.result = result;
    }
        public double getLowLim(){return lowLim;}
        public double getUpLim(){return upLim;}
        public double getStep() {return step;}
        public double geResult() {return result;}
        public void setResult(double result) {this.result = result;}
        
        public double CalcIntegral(double lowLim, double upLim, double step) {
        double start, h, sumS = 0;
        start = lowLim;
        do{
        h = Math.min(step, (upLim - start));
        sumS += h * (1 / start + 1/(start + h)) / 2; 
        start += h;
                } while ((start) < upLim);
        return sumS;
    }
}

