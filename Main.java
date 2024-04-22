public class Main
{
    public static void main(String[] args){
        Race race = new Race(6);
        Horse horse1 = new Horse('J', "John", 0.5);
        Horse horse2 = new Horse('R', "Rafs", 0.5);
        Horse horse3 = new Horse('Z', "Zans", 0.5);
        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);
        race.startRace();
    }
}
