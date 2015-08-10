package kz.e16training.htmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Line of parsed text.
 *
 */
public class Line {
    private boolean isPic;
    private int numbOfLine;
    private String data;
    private List<Integer> numbOfPics;
    private boolean isRefPrevPic;

    public Line(int numbOfLine, String data, boolean isPic) {
        this.numbOfLine = numbOfLine;
        this.data = data;
        this.numbOfPics = new ArrayList<Integer>();
        this.isPic = isPic;
    }

    public void addNumbOfPic(int numbOfPic) {
        this.numbOfPics.add(numbOfPic);
    }

    public String getData() {
        return data;
    }

    public List<Integer> getNumbOfPics() {
        return numbOfPics;
    }

    public int getNumbOfLine() {
        return numbOfLine;
    }

    public void setRefPrevPic() {
        isRefPrevPic = true;
    }

    public boolean isPic() {
        return isPic;
    }

    @Override
    public String toString() {
        return String.format("%4s : %3s : %4s : %-50s", numbOfLine, isPic ? "pic" : "ref", isPic ? "pic" : (isRefPrevPic ? "fail" : "ok"), numbOfPics.toString());
    }

}
