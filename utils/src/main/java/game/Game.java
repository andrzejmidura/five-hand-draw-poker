package game;

import deck.Deck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Game {
    private int startingPlayer = 0;
    private final Queue<Player> playerQueue = new LinkedList<>();
    private final Queue<Player> auxiliaryQueue = new LinkedList<>();
    protected int maxPlayers;
    protected final List<Player> players = new ArrayList<>(maxPlayers);
    private Player currentPlayer;
    private boolean running = false;
    private int roundNumber;
    private final List<Player.Action> allowedActions = new ArrayList<>(6);
    private final Deck deck = new Deck();
    private int mainStake = 0;


    public Game() {
    }

    public void addPlayer(Player p) {
        if (!this.running && !this.players.contains(p)) {
            this.players.add(p);
            if (this.players.size() == this.maxPlayers)
                this.running = true;
        }
    }

    public void start() {

    }

    public String getRespond(Player p, int action) {
        StringBuilder msgBuilder = new StringBuilder();
        if (p==this.currentPlayer) {
            // handle action
        } else {

        }

        return msgBuilder.toString();
    }

    public void makeAction(Player p, int action) {
        switch (action) {
            case 1: {
                p.setCurrentAction(Player.Action.BET);
                this.handleBet();
                break;
            }
            case 2: {
                p.setCurrentAction(Player.Action.FOLD);
                this.handleFold();
                break;
            }
            case 3: {
                p.setCurrentAction(Player.Action.CHECK);
                this.handleCheck();
                break;
            }
            case 4: {
                p.setCurrentAction(Player.Action.CALL);
                this.handleCall();
                break;
            }
            case 5: {
                p.setCurrentAction(Player.Action.RAISE);
                this.handleRaise();
                break;
            }
            case 6: {
                p.setCurrentAction(Player.Action.ALLIN);
                this.handleAllin();
                break;
            }
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    protected void setupDeal() {
        this.roundNumber = 0;
        this.dealCards();
        this.setupRound();
    }

    protected void setupRound() {
        this.roundNumber++;
        this.updateAllowedActions();
        this.addPlayersToQueue();
    }

    protected void updateGameStatus() {
        // after every player action game has to check if any players in queue left
        if (playerQueue.isEmpty() && this.roundNumber==1) {
            this.handleExchange();
            this.startingPlayer = (this.startingPlayer+1) % this.maxPlayers;
            this.setupRound();
        }
        else if (playerQueue.isEmpty() && this.roundNumber==2) {
            this.startingPlayer = (this.startingPlayer+1) % this.maxPlayers;
            this.setupDeal();
        }
    }

    protected void handleExchange() {}

    protected void handleBet() {
        this.updateAllowedActions(Player.Action.BET);
    }

    protected void handleFold() {
    }

    protected void handleCheck() {
    }

    protected void handleCall() {
        this.updateAllowedActions(Player.Action.CALL);
    }

    protected void handleRaise() {
        this.updateAllowedActions(Player.Action.RAISE);
    }

    protected void handleAllin() {
        this.updateAllowedActions(Player.Action.ALLIN);
    }

    protected void updateAllowedActions(Player.Action a) {
        this.allowedActions.clear();
        this.allowedActions.add(Player.Action.FOLD);
        this.allowedActions.add(Player.Action.CALL);
        this.allowedActions.add(Player.Action.ALLIN);

        if (a == Player.Action.BET || a == Player.Action.RAISE)
            this.allowedActions.add(Player.Action.RAISE);
    }

    protected void updateAllowedActions() {
        this.allowedActions.clear();
        this.allowedActions.add(Player.Action.BET);
        this.allowedActions.add(Player.Action.FOLD);
        this.allowedActions.add(Player.Action.CALL);
        this.allowedActions.add(Player.Action.ALLIN);
    }

    protected void addPlayersToQueue() {
        for (int i=this.startingPlayer; i<this.maxPlayers; i++)
            this.playerQueue.add(this.players.get(i));
        for (int i=0; i<this.startingPlayer; i++)
            this.playerQueue.add(this.players.get(i));
    }

    protected void dealCards() {
        this.deck.sort();
        this.deck.shuffle();

        for (int i=0; i<5; i++) {
            for (Player p : this.playerQueue)
                p.addCard(this.deck.getFromTop());
        }
    }

}
