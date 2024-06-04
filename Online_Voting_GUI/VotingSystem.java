
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Queue;

// Voter.java
class Voter {
    private String voterId;
    private String name;

    public Voter(String voterId, String name) {
        this.voterId = voterId;
        this.name = name;
    }

    public String getVoterId() {
        return voterId;
    }

    public String getName() {
        return name;
    }
}

// Vote.java
class Vote {
    private String voterId;
    private int candidate;
    private String party;

    public Vote(String voterId, int candidate, String party) {
        this.voterId = voterId;
        this.candidate = candidate;
        this.party = party;
    }

    public String getVoterId() {
        return voterId;
    }

    public int getCandidate() {
        return candidate;
    }

    public String getParty() {
        return party;
    }
}

// VotingSystem.java
public class VotingSystem {
    private Queue<Vote> voteQueue;
    private Set<String> voterIds;
    private HashMap<Integer, String> candilist;
    private JTextArea resultArea;
    private HashMap<String, Integer> voteCounts;

    public VotingSystem(HashMap<Integer, String> candilist, JTextArea resultArea) {
        this.voteQueue = new LinkedList<>();
        this.voterIds = new HashSet<>();
        this.candilist = candilist;
        this.resultArea = resultArea;
        this.voteCounts = new HashMap<>();
    }

    public boolean isValidVoterId(String voterId) {
        return voterId.matches("\\d{6}");
    }

    public void castVote(Vote vote) {
        if (voterIds.contains(vote.getVoterId())) {
            resultArea.append("Voter ID: " + vote.getVoterId() + " has already cast a vote.\n");
        } else {
            voteQueue.add(vote);
            voterIds.add(vote.getVoterId());
            resultArea.append("Vote cast successfully!\nVoter ID: " + vote.getVoterId() + "\n");
            voteCounts.put(vote.getParty(), voteCounts.getOrDefault(vote.getParty(), 0) + 1);
        }
    }

    public void processVotes() {
        resultArea.append("\nProcessing votes...\n");

        // Display the vote counts for each party
        for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
            resultArea.append("Party: " + entry.getKey() + " - Votes: " + entry.getValue() + "\n");
        }
    }

    public void displayCandidates(JComboBox<String> candidateComboBox) {
        for (Map.Entry<Integer, String> entry : candilist.entrySet()) {
            candidateComboBox.addItem(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HashMap<Integer, String> candidates = new HashMap<>();
            candidates.put(101, "UDF");
            candidates.put(102, "LDF");
            candidates.put(103, "AAP");
            candidates.put(104, "TSR");
            candidates.put(105, "DMK");
            candidates.put(106, "NOTA");

            JFrame frame = new JFrame("Voting System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            JLabel voterIdLabel = new JLabel("Enter Voter ID:");
            JTextField voterIdField = new JTextField();
            panel.add(voterIdLabel);
            panel.add(voterIdField);

            JLabel candidateLabel = new JLabel("Select Candidate:");
            JComboBox<String> candidateComboBox = new JComboBox<>();
            panel.add(candidateLabel);
            panel.add(candidateComboBox);

            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);

            JButton castVoteButton = new JButton("Cast Vote");
            castVoteButton.setBackground(new Color(0, 123, 255));
            castVoteButton.setForeground(Color.WHITE);
            castVoteButton.setFocusPainted(false);

            JButton processVotesButton = new JButton("Process Votes");
            processVotesButton.setBackground(new Color(40, 167, 69));
            processVotesButton.setForeground(Color.WHITE);
            processVotesButton.setFocusPainted(false);

            VotingSystem votingSystem = new VotingSystem(candidates, resultArea);
            votingSystem.displayCandidates(candidateComboBox);

            castVoteButton.addActionListener(e -> {
                String voterId = voterIdField.getText();
                if (!votingSystem.isValidVoterId(voterId)) {
                    resultArea.append("Invalid Voter ID. It should be exactly 6 digits. Please try again.\n");
                    return;
                }

                String selectedCandidate = (String) candidateComboBox.getSelectedItem();
                if (selectedCandidate == null) {
                    resultArea.append("No candidate selected. Please try again.\n");
                    return;
                }

                int candidate = Integer.parseInt(selectedCandidate.split(" - ")[0]);
                String party = candidates.get(candidate);

                Vote vote = new Vote(voterId, candidate, party);
                votingSystem.castVote(vote);
            });

            processVotesButton.addActionListener(e -> votingSystem.processVotes());

            panel.add(castVoteButton);
            panel.add(processVotesButton);

            frame.getContentPane().add(panel, BorderLayout.NORTH);
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
