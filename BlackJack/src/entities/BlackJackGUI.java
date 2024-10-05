package entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import entities.BlackJackGame;
import entities.Card;  // assuming you have these classes

public class BlackJackGUI extends JFrame {
	
    private BlackJackGame game;
    private JPanel dealerPanel;
    private JPanel playersPanel;
    private JPanel controlPanel;
    private JLabel statusLabel;
    private JButton hitButton;
    private JButton standButton;
    private JButton startGameButton;

    public BlackJackGUI() {
        super("Blackjack Game");

        // Initialize Panels
        dealerPanel = new JPanel();
        playersPanel = new JPanel();
        controlPanel = new JPanel();
        statusLabel = new JLabel("Welcome to Blackjack!");

        // Set Layouts
        setLayout(new BorderLayout());
        dealerPanel.setLayout(new FlowLayout());
        playersPanel.setLayout(new FlowLayout());
        controlPanel.setLayout(new FlowLayout());

        // Add Panels to Frame
        add(dealerPanel, BorderLayout.NORTH);
        add(playersPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.PAGE_END);

        // Set Frame Settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);

        // Add Control Buttons
        setupControlButtons();
    }

    private void setupControlButtons() {
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        startGameButton = new JButton("Start Game");

        controlPanel.add(hitButton);
        controlPanel.add(standButton);
        controlPanel.add(startGameButton);

        // Add Action Listeners
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerHit();
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerStand();
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        // Start a new game with predefined number of players
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player 1");
        game = new BlackJackGame(playerNames);

        game.startGame();
        displayDealerHand();
        displayPlayerHands();
        statusLabel.setText("Game started! Player 1's turn.");
    }

    private void playerHit() {
        // Add card to player hand and update UI
        game.playerHit();
        displayPlayerHands();
        checkPlayerStatus();
    }

    private void playerStand() {
        game.playerStand();
        checkGameStatus();
    }

    private void checkPlayerStatus() {
        if (game.isPlayerBusted()) {
            statusLabel.setText("Player busted!");
            disableButtons();
        }
    }

    private void checkGameStatus() {
        if (game.isGameOver()) {
            statusLabel.setText(game.getWinner());
            disableButtons();
        }
    }

    private void disableButtons() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    private void displayDealerHand() {
        dealerPanel.removeAll();
        for (Card card : game.getDealerHand()) {
            dealerPanel.add(new JLabel(card.toString())); // Customize as needed
        }
        dealerPanel.revalidate();
        dealerPanel.repaint();
    }

    private void displayPlayerHands() {
        playersPanel.removeAll();
        for (Card card : game.getCurrentPlayerHand()) {
            playersPanel.add(new JLabel(card.toString())); // Customize as needed
        }
        playersPanel.revalidate();
        playersPanel.repaint();
    }

    public static void main(String[] args) {
        new BlackJackGUI();
    }
}
