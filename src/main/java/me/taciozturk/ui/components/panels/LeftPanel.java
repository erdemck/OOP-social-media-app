package me.taciozturk.ui.components.panels;

import me.taciozturk.User;
import me.taciozturk.ui.views.UserView;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel implements ICreatePanel{

    private User user;
    private int width;
    private int height;

    public LeftPanel(int _width, int _height, User _user) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
    }

    @Override
    public LeftPanel create() {

        int panelWidth = width * 2 / 8;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(panelWidth, height));

        // Read the avatar image, resize and add to JLabel to use it as a component in JPanel

        JLabel avatar = new JLabel();
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatar.setAlignmentY(Component.CENTER_ALIGNMENT);
        avatar.setIcon(user.getAvatar());
        avatar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
        avatar.setOpaque(false);
        this.add(avatar);

        JLabel username = new JLabel(user.getName());
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(username);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel title = new JLabel("GROUPS");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        user.getGroups().forEach(e->{

            JLabel groupName = new JLabel(e.getName());
            this.add(groupName);

        });
        JButton logOut = new JButton("Log out");
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logOut);

        logOut.addActionListener(e->{
            System.exit(0);
        });

        return this;
    }

}
