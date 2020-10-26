import java.awt.*;

public class Insomniac
{
    public static void main( String[] args ) throws AWTException, InterruptedException {
        Arguments parsedArguments = parseArgs(args);

        java.awt.Robot robot = new java.awt.Robot();

        while (true){
            Point startPosition = MouseInfo.getPointerInfo().getLocation();
            int x = startPosition.x < parsedArguments.movementDistance ? startPosition.x + parsedArguments.movementDistance : startPosition.x - parsedArguments.movementDistance;
            int y = startPosition.y < parsedArguments.movementDistance ? startPosition.y + parsedArguments.movementDistance : startPosition.y - parsedArguments.movementDistance;
            robot.mouseMove(x, y);
            Thread.sleep(300);
            robot.mouseMove(startPosition.x, startPosition.y);
            Thread.sleep(parsedArguments.waitTime);
        }
    }

    private static Arguments parseArgs(String[] args){
        Arguments result = new Arguments();

        int index = 0;
        while(index < args.length){
            index++;
            String nextArg = args[index];

            if (nextArg == "-w"){
                result.waitTime = Integer.parseInt(args[index]);
                index++;
            }
            else if (nextArg == "-d"){
                result.movementDistance = Integer.parseInt(args[index]);
                index++;
            }
        }

        return result;
    }

    private static enum Argument{
        WAIT_TIME, MOVEMENT_DISTANCE;
    }

    private static class Arguments{
        int waitTime = 90000;

        int movementDistance = 10;
    }
}
