package me.taciozturk.ui.components.panels;

import me.taciozturk.User;
import me.taciozturk.ui.components.cards.UserCard;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel implements ICreatePanel {
    private User user;
    private int width;
    private int height;


    public RightPanel(int _width, int _height, User _user) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
    }

    public RightPanel create(){
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);

        this.setLayout(boxLayout);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width * 2 / 8, height ));


        JLabel title = new JLabel("FRIENDS");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(title);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        user.getConnections().forEach(connection -> {
            UserCard card = new UserCard(connection);
            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(card);
        });

        return this;
    }
}
