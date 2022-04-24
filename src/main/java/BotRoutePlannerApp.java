import structures.Result;

public class BotRoutePlannerApp {
    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Arguments: grid-file.txt, job-file.txt");
            return;
        }
        Parser parser = new Parser(args[0], args[1]);

        BotRoutePlanner botRoutePlanner = new BotRoutePlanner(parser);
        Result result = botRoutePlanner.findFastestPath();

        result.printResult();
    }
}
