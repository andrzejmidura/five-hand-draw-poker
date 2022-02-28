package game;

import deck.Deck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Game {
    protected static final int ENTRY_PRICE = 200;
    private int startingPlayer = 0;
    private final Queue<Player> playerQueue = new LinkedList<>();
    private final Queue<Player> auxiliaryQueue = new LinkedList<>();
    protected int maxPlayers;
    protected final List<Player> players = new ArrayList<>(maxPlayers);
    private Player currentPlayer;
    private boolean running = false;
    private int roundNumber;
    private final List<Integer> allowedActions = new ArrayList<>(7);
    private final Deck deck = new Deck();
    private int mainStake = 0;
    private int onTable = 0;
    protected String wins = "Deal has not been finished yet...";


    public Game() {
    }

    public void addPlayer(Player p) {
        if (!this.running && !this.players.contains(p)) {
            this.players.add(p);
            p.setPocket(ENTRY_PRICE);
            if (this.players.size() == this.maxPlayers)
                this.running = true;
        }
    }

    public void start() {

    }

    public String getRespond(Player p, int action) {
        StringBuilder msgBuilder = new StringBuilder();
        if (p==this.currentPlayer) {
            if (allowedActions.contains(action)) {
                this.makeAction(p, action);
                if (action==Player.Action.EXIT.value) {
                    msgBuilder.append(2).append(";"); // you've left the game
                } else {
                    msgBuilder.append(0).append(";"); // you've already made an action
                }
            } else {
                msgBuilder.append(1).append(";"); // you're current player until you make allowed action
            }
        } else {
            msgBuilder.append(0).append(";"); // simply not your turn at the moment
        }

        for (Player anotherPlayer : this.players) {
            if (anotherPlayer!=p) {
                msgBuilder.append(anotherPlayer.getName()).append(";");
                msgBuilder.append(anotherPlayer.getCurrentAction().toString()).append(";");
                msgBuilder.append(anotherPlayer.getPocket()).append(";");
            }
        }
        msgBuilder.append(p.getName()).append(";");
        msgBuilder.append(p.getPocket()).append(";");
        msgBuilder.append(p.getHand().toString()).append(";");
        msgBuilder.append(this.onTable).append(";");
        msgBuilder.append(this.wins);
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
                case 7: {
                    p.setCurrentAction(Player.Action.EXIT);
                    this.handleExit();
                    break;
                }
            }
        this.updateGameStatus();
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
        this.rateHands();
        this.updateAllowedActions();
        this.addPlayersToQueue();
        this.currentPlayer = this.playerQueue.peek();
    }

    protected void updateGameStatus() {
        // after every player action game has to check if any players in queue left
        if (playerQueue.isEmpty() && this.roundNumber==1) {
            this.handleExchange();
            this.startingPlayer = (this.startingPlayer+1) % this.players.size();
            this.setupRound();
        }
        else if (playerQueue.isEmpty() && this.roundNumber==2) {
            this.startingPlayer = (this.startingPlayer+1) % this.players.size();
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

    protected void handleExit() {
        this.players.remove(this.currentPlayer);
        this.handleFold();
    }

    protected void updateAllowedActions(Player.Action a) {
        this.allowedActions.clear();
        this.allowedActions.add(Player.Action.FOLD.value);
        this.allowedActions.add(Player.Action.CALL.value);
        this.allowedActions.add(Player.Action.ALLIN.value);
        this.allowedActions.add(Player.Action.EXIT.value);

        if (a == Player.Action.BET || a == Player.Action.RAISE)
            this.allowedActions.add(Player.Action.RAISE.value);
    }

    protected void updateAllowedActions() {
        this.allowedActions.clear();
        this.allowedActions.add(Player.Action.BET.value);
        this.allowedActions.add(Player.Action.FOLD.value);
        this.allowedActions.add(Player.Action.CHECK.value);
        this.allowedActions.add(Player.Action.ALLIN.value);
        this.allowedActions.add(Player.Action.EXIT.value);
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

    protected void rateHands() {}
}
