
/**
 * This class represents a horse in the race. It's class variables iclude the horse's name, symbol, confidence, distance travelled and whether it has fallen or not. 
 * The class also includes methods to set and get the horse's confidence, distance travelled, name, symbol and whether it has fallen or not. 
 * The class also includes methods to make the horse fall, move forward, go back to the start, set the horse's confidence and symbol.
 * 
 * @author (Acil Djehiche) 
 * @version (Version 2 - 22/04/2024)
 */
public class Horse
{
    String horseName;
    char horseSymbol;
    double horseConfidence;
    int distanceTravelled;
    boolean hasFallen;

    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    
    public void fall()
    {
        this.hasFallen = true;
    }
    
    public double getConfidence()
    {
        return this.horseConfidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    public String getName()
    {
        return this.horseName;
    }
    
    public char getSymbol()
    {
        return this.horseSymbol;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    public void moveForward()
    {
        this.distanceTravelled += 4;
    }

    public void setConfidence(double newConfidence)
    {
        if(newConfidence>1.0){
            newConfidence = 1.0;
        }
        else if(newConfidence<0.1){
            newConfidence = 0.1;
        }
        newConfidence = Math.round(newConfidence * 100.0) / 100.0;
        this.horseConfidence = newConfidence;
    }
    
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
    }
    
}
