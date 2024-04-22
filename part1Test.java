public class part1Test {
    public static void main(String[] args){
        Horse testHorse = new Horse('T', "subject", 0.5);
        int testsPassed = 0;
        System.out.println("Testing Horse Class");
        System.out.println("");
        System.out.println("Testing Symbol");
        System.out.println("Expected: T");
        System.out.println("Got: " + testHorse.getSymbol());
        if(testHorse.getSymbol() == 'T'){
            System.out.println("Symbol Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Symbol Test Failed");
        }
        System.out.println("Testing Name");
        System.out.println("Expected: subject");
        System.out.println("Got: " + testHorse.getName());
        if(testHorse.getName().equals("subject")){
            System.out.println("Name Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Name Test Failed");
        }
        System.out.println("Testing Confidence");
        System.out.println("Expected: 0.5");
        System.out.println("Got: " + testHorse.getConfidence());
        if(testHorse.getConfidence() == 0.5){
            System.out.println("Confidence Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Confidence Test Failed");
        }
        System.out.println("Testing Distance Travelled");
        System.out.println("Expected: 0");
        System.out.println("Got: " + testHorse.getDistanceTravelled());
        if(testHorse.getDistanceTravelled() == 0){
            System.out.println("Distance Travelled Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Distance Travelled Failed");
        }
        System.out.println("Testing Has Fallen");
        System.out.println("Expected: false");
        System.out.println("Got: " + testHorse.hasFallen());
        if(!testHorse.hasFallen()){
            System.out.println("Has fallen test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Has fallen test Failed");
        }
        System.out.println("Testing Move Forward");
        System.out.println("Expected: 1");
        testHorse.moveForward();
        System.out.println("Got: " + testHorse.getDistanceTravelled());
        if(testHorse.getDistanceTravelled() == 1){
            System.out.println("Moving Forward Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Moving Foward Test Failed");
        }
        System.out.println("Testing Go Back To Start");
        System.out.println("Expected: 0");
        testHorse.goBackToStart();
        System.out.println("Got: " + testHorse.getDistanceTravelled());
        if(testHorse.getDistanceTravelled() == 0){
            System.out.println("Go Back To Start Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Go Back To Start Test Failed");
        }
        System.out.println("Testing Fall");
        System.out.println("Expected: true");
        testHorse.fall();
        System.out.println("Got: " + testHorse.hasFallen());
        if(testHorse.hasFallen()){
            System.out.println("Fall Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Fall Test Failed");
        }
        System.out.println("Testing Set Confidence");
        System.out.println("Expected: 0.6");
        testHorse.setConfidence(0.6);
        System.out.println("Got: " + testHorse.getConfidence());
        if(testHorse.getConfidence() == 0.6){
            System.out.println("Set Confidence Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Set Confidence Test Failed");
        }
        testHorse.setSymbol('Z');
        System.out.println("Testing Set Symbol");
        System.out.println("Expected: Z");
        System.out.println("Got: " + testHorse.getSymbol());
        if(testHorse.getSymbol() == 'Z'){
            System.out.println("Set Symbol Test Passed");
            System.out.println("");
            testsPassed++;
        } else {
            System.out.println("Set Symbol Test Failed");
        }
        System.out.println("Tests Passed: " + testsPassed + "/10");

    }
}
