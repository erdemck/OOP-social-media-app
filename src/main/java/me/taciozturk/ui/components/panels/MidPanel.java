package me.taciozturk.ui.components.panels;

import me.taciozturk.User;
import me.taciozturk.ui.components.cards.PostCard;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MidPanel extends JPanel implements ICreatePanel {
    private User user;
    private int width;
    private int height;
    private JScrollPane scrollPane;

    public MidPanel(int _width, int _height, User _user) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
    }

    @Override
    public MidPanel create(){


        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width * 4 / 8, height));

        JPanel writePostPanel = new JPanel();
        writePostPanel.setLayout(new BoxLayout(writePostPanel,BoxLayout.Y_AXIS));
        JTextArea postArea = new JTextArea();
        postArea.setLineWrap(true);
        postArea.setWrapStyleWord(true);
        postArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        postArea.setRows(12);
        postArea.setBackground(new Color(240, 240, 240));
        postArea.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JScrollPane textPane = new JScrollPane(postArea);
        textPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton sendButton = new JButton("Send Post");
        sendButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        sendButton.addActionListener(_ -> {
            String postText = postArea.getText();
            user.addPost(postText);
            this.remove(scrollPane);
            scrollPane = drawPosts(user);
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        });

        writePostPanel.add(textPane);
        writePostPanel.add(sendButton);
        writePostPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        writePostPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(writePostPanel);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel title = new JLabel("POSTS");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(title);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        scrollPane = drawPosts(user);

        this.add(scrollPane);

        return this;
    }

    public JScrollPane drawPosts(User user) {
        JPanel posts = new JPanel();
        posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(posts);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        user.getPosts().reversed().forEach(post -> {
            PostCard postCard = new PostCard(post);
            postCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
            posts.add(postCard);
            JLabel username = new JLabel(user.getName());
            posts.add(username);
            posts.add(new JSeparator(SwingConstants.HORIZONTAL));
            posts.setBackground(Color.WHITE);
        });
        return scrollPane;
    }

    private class UserListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }
    }
}
