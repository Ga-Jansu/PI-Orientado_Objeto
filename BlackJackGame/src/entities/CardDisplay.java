package entities;

import 




public class CardDisplay extends JPanel {
    
    private Image cardImage;

    // Construtor que recebe o caminho da imagem da carta (PNG)
    public CardDisplay(Card card) {
        String imagePath = getCardImagePath(card); // Obter o caminho da imagem da carta
        try {
            cardImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }
    }

    // Método que define o caminho do arquivo de imagem com base no naipe e face da carta
    private String getCardImagePath(Card card) {
        String face = card.getFace().toLowerCase(); 
        String suit = card.getSuitSymbol().toLowerCase(); 
        return "resources/cards/" + face + "_" + suit + ".png"; // Exemplo: "resources/cards/ace_hearts.png"
    }

    // Sobrescrevendo o método paintComponent para desenhar a imagem
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cardImage != null) {
            g.drawImage(cardImage, 0, 0, this); // Desenha a imagem da carta
        }
    }

    // Método para exibir as cartas em uma janela
    public static void displayCards(Card[] cards) {
        JFrame frame = new JFrame("Blackjack Cards");
        frame.setLayout(new FlowLayout()); // Layout para colocar as cartas na horizontal

        // Para cada carta no array, cria um painel de exibição
        for (Card card : cards) {
            CardDisplay cardPanel = new CardDisplay(card);
            cardPanel.setPreferredSize(new Dimension(100, 150)); // Defina o tamanho da carta
            frame.add(cardPanel); // Adiciona a carta à janela
        }

        frame.setSize(600, 300); // Ajuste o tamanho da janela conforme necessário
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
