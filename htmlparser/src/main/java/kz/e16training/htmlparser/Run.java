package kz.e16training.htmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DK
 * @version 1.0.0
 *
 */
public class Run {
    public static void main(String[] args) {
        List<Line> bothLines = new ArrayList<Line>();
        Parser parser = new Parser();
        parser.parse(bothLines);
        parser.setState(bothLines);
        printLines(bothLines);
    }

    public static void printLines(List<Line> lines) {
        for (Line line : lines) {
            System.out.println(line);
        }
    }
}
