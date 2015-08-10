package kz.e16training.htmlparser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser realisation.
 *
 */
public class Parser {
    private Pattern picPattern = Pattern.compile("pic([0-9]{1,2})[.]jpg");
    private Pattern refPattern = Pattern.compile("[Р|р](ис[.]|исунке)[ ]?([0-9]{1,2})");
    private static final String FILE_FOR_PARSE = "Java.SE.03.Information handling_task_attachment.html";

    public void parse(List<Line> bothLines) {
        try {
            InputStream fin = getClass().getResourceAsStream(FILE_FOR_PARSE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fin, "cp1251"));
            int numberOfLine = 0;
            String str;
            while ((str = in.readLine()) != null) {
                Matcher m = picPattern.matcher(str);
                Matcher p = refPattern.matcher(str);
                numberOfLine++;
                if (m.find()) {
                    bothLines.add(new Line(numberOfLine, str, true));
                } else if (p.find()) {
                    bothLines.add(new Line(numberOfLine, str, false));
                }
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setState(List<Line> bothLines) {
        for (Line line : getLines(bothLines, false)) {
            Matcher m = refPattern.matcher(line.getData());
            while (m.find()) {
                line.addNumbOfPic(Integer.valueOf(m.group(2)));
            }
        }
        for (Line line : getLines(bothLines, true)) {
            Matcher m = picPattern.matcher(line.getData());
            if (m.find()) {
                line.addNumbOfPic(Integer.valueOf(m.group(1)));
            }
        }
        for (Line line : getLines(bothLines, false)) {
            for (int i = 0; i < line.getNumbOfPics().size(); i++) {
                if (line.getNumbOfLine() < getNumbOfPicLine(line.getNumbOfPics().get(i), bothLines))
                    line.setRefPrevPic();
            }
        }
    }

    public List<Line> getLines(List<Line> bothLines, boolean isPic) {
        List<Line> result = new ArrayList<Line>();
        for (Line line : bothLines)
            if (line.isPic() == isPic) result.add(line);
        return result;
    }

    private int getNumbOfPicLine(int numbOfPic, List<Line> bothLines) {
        for (Line line : getLines(bothLines, true)) {
            if (line.getNumbOfPics().get(0) == numbOfPic) return line.getNumbOfLine();
        }
        return 0;
    }
}
