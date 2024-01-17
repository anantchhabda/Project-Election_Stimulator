/**
 * Represents a voting paper in the Antarctica election process. 
 *
 * @author Lyndon While
 * @version 1.0 2020
 */
import java.util.ArrayList;

public class VotingPaper
{
    // the numbers marked on the paper 
    private ArrayList<Integer> marks;

    /**
     * Constructor for objects of class VotingPaper. 
     * s will be a (possibly empty) sequence of integers, separated by commas. 
     * e.g. if s is "1,22,-13,456", marks is set to <1,22,-13,456>. 
     */
    public VotingPaper(String s)
    {
        marks = new ArrayList<>();
        if (!s.isEmpty())
            for (String x : s.split(",")) 
                marks.add(Integer.parseInt(x));
    }

    /**
     * Returns the contents of the paper.
     */
    public ArrayList<Integer> getMarks()
    {
        return marks;       
    }

    /**
     * Returns true iff the paper has the correct number of marks, 
     * i.e. one for each candidate. 
     */
    public boolean isCorrectLength(int noOfCandidates)
    {
        return (marks.size()==noOfCandidates);
    }

    /**
     * Returns true iff the sum of the marks is legal, 
     * i.e. no more than total. 
     */
    public boolean isLegalTotal(int total)
    {
        int z = 0, index = 0;
        for (Integer mark: marks){
            z+=marks.get(index);
            index++;
        }
        return (z<=total);   
    }

    /**
     * Returns true iff there are negative marks. 
     */
    public boolean anyNegativeMarks()
    {
        int index = 0; boolean negativemark = false;
        while (index<marks.size()){
            if (marks.get(index)>=0){
                index++;
            }
            else {
                negativemark = true;
                index = marks.size();
            }
        }
        return negativemark;
    }

    /**
     * Returns true iff the paper is formal. 
     * It must be the right length with no negative marks and a legal total. 
     */
    public boolean isFormal(int noOfCandidates)
    {
        return (isCorrectLength(noOfCandidates) && isLegalTotal(noOfCandidates) && !(anyNegativeMarks()));
    }

    /**
     * Adds the appropriate number of votes to each candidate.
     * The kth number goes to the kth candidate.
     */
    public void updateVoteCounts(ArrayList<Candidate> cs)
    {
        for (int i=0; i<cs.size(); i++){
            cs.get(i).addToCount(marks.get(i));
        }
    }

    /**
     * Returns the indices in marks which have the highest number.
     * e.g. if marks = <4,4,1,5,2>, it returns <3> (because the highest number is at index 3).
     * e.g. if marks = <5,4,1,2,5>, it returns <0,4>.
     * e.g. if marks = <1,1,1,1,1>, it returns <0,1,2,3,4>.
     */
    public ArrayList<Integer> highestVote()
    {
        // TODO 19
        ArrayList<Integer> highestvote = new ArrayList();
        int z = 0;
        for (int i=0; i<marks.size(); i++){
            if (marks.get(i)>=z){
                z = marks.get(i);
            }
        }
        for (int i=0; i<marks.size(); i++){
            if (marks.get(i)==z){
                highestvote.add(i);
            }
        }
        return highestvote;
    }

     /**
     * Adds the appropriate number of wins to each candidate.
     * If there are n equal-highest numbers, each of those 
     * candidates receives 1/n wins. 
     */
    public void updateWinCounts(ArrayList<Candidate> cs)
    {  
        int candidateindex;
        for (int i=0; i<highestVote().size(); i++){
            candidateindex = highestVote().get(i);
            cs.get(candidateindex).addToWins(1.0/(highestVote().size()));
        }
    }
}
