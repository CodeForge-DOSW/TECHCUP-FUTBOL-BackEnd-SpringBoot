

public class Card {

    private Long id;
    private Match match;
    private User player;
    private CardType cardType;
    private Integer minute;

    public Card() {}

    public Card(Long id, Match match, User player, CardType cardType, Integer minute) {
        this.id = id;
        this.match = match;
        this.player = player;
        this.cardType = cardType;
        this.minute = minute;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public User getPlayer() { return player; }
    public void setPlayer(User player) { this.player = player; }
    public CardType getCardType() { return cardType; }
    public void setCardType(CardType cardType) { this.cardType = cardType; }
    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }

    @Override
    public String toString() {
        return "Card{id=" + id + ", player=" + (player != null ? player.getFullName() : null) + ", cardType=" + cardType + ", minute=" + minute + "}";
    }
}