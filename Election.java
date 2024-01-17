/**
 * Manages the Antarctica election process. 
 *
 * @author Lyndon While
 * @version 1.0 2020
 */
import java.util.ArrayList;

public class Election
{
    // the list of candidates
    private ArrayList<Candidate> candidates;
    // the list of voting papers
    private ArrayList<VotingPaper> papers;
    // the file of election information
    private FileIO file;

    /**
     * Constructor for objects of class Election.
     * Creates the three field objects.
     */
    public Election(String filename)
    {
        candidates = new ArrayList<>();
        papers = new ArrayList<>();
        file = new FileIO(filename);
    }

    /**
     * Constructor for objects of class Election with default files.
     * It uses k to select from the sample input files.
     */
    public Election(int k)
    {
        this("election" + k + ".txt");
    }

    /**
     * Returns the candidates list.
     */
    public ArrayList<Candidate> getCandidates()
    {
        // TODO 14
        return candidates;
    }

    /**
     * Returns the papers list.
     */
    public ArrayList<VotingPaper> getPapers()
    {
        // TODO 15
        return papers;
    }

    /**
     * Returns the read-in file contents.
     */
    public ArrayList<String> getFile()
    {
        // TODO 16
        return file.getLines();
    }

    /**
     * Use the file information to initialise the other two fields.
     * Reads the candidates, then discards exactly one blank line, then reads the voting papers.
     * See the sample input files for examples of the format.
     */
    public void processFile() 
    {
        int x = file.getLines().indexOf(""); 

        for (int i=0 ; i<x; i++){
            Candidate candidate = new Candidate(file.getLines().get(i));
            candidates.add(candidate);
        }

        for (int i=x+1; i>x && i<file.getLines().size(); i++){
            VotingPaper paper = new VotingPaper(file.getLines().get(i));
            papers.add(paper);
        }   
    }

    /**
     * Adds each formal vote to each candidate, both numbers of votes and numbers of wins.
     * Returns the number of informal votes.
     */
    public int conductCount()
    {
        // TODO 21
        int informalvotes = 0;
        for (VotingPaper vote: papers){
            if (vote.isFormal(candidates.size())){
                vote.updateVoteCounts(candidates);
                vote.updateWinCounts(candidates);
            }
            if (!(vote.isFormal(candidates.size()))){
                informalvotes+=1; 
            }      
        }
        return informalvotes;
    }

    /**
     * Returns and prints a summary of the election result. 
     * See the sample output files for the required format. 
     */
    public String getStandings()
    {
        // TODO 22
        String print = "";
        for (Candidate candidate: candidates){
            print+=candidate.getStanding() + "\n";
        }
        System.out.println (print);
        return print;       
    }

    /**
     * Returns the winner of the election. 
     * Selects the candidate with the highest number of votes; if multiple 
     * candidates are equal, selects the one with the highest number of wins. 
     */
    public Candidate winner()
    {
        // TODO 23
        int v = candidates.get(0).getNoOfVotes();
        double w = candidates.get(0).getNoOfWins(); 
        ArrayList<Candidate> winners = new ArrayList<>();
        for (int i=0; i<=candidates.size(); i++){
            if (candidates.get(i).getNoOfVotes()>v) {
                v= candidates.get(i).getNoOfVotes(); 
            }
            else {
                if (candidates.get(i).getNoOfVotes()==v){
                    winners.add(candidates.get(i));
                }                
            }
        }
        return winners.get(0);
    }
              
        

}
