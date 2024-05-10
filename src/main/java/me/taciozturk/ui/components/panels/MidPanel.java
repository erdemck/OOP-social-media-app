package me.taciozturk.ui.components.panels;

import me.taciozturk.Post;
import me.taciozturk.Search;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.components.cards.PostCard;


import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;


public class MidPanel extends JPanel implements ICreatePanel {
    private User user;
    private int width;
    private int height;
    private JScrollPane scrollPane;
    private UserList userList;
    private JTextField searchField;
    private JPanel searchResultPanel;
    private List<User> searchResults;

    public MidPanel(int _width, int _height, User _user, UserList _userList) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
        this.userList = _userList;
    }

    @Override
    public MidPanel create() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width * 4 / 8, height));

        searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height));
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                }
            }
        });

        this.add(searchField);

        searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchResultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(searchResultPanel);

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
            Post post = new Post(postText,user.getId(),new Date());
            user.addPost(post);
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

    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        searchResultPanel.removeAll();
        searchResults = new Search().search(query, userList);

        for (User resultUser : searchResults) {
            JLabel usernameLabel = new JLabel(resultUser.getName());
            JButton viewProfileButton = new JButton("Add Friend");

            viewProfileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userList.addConnection(resultUser,user);
                }
            });

            searchResultPanel.add(usernameLabel);
            searchResultPanel.add(viewProfileButton);
        }

        searchResultPanel.revalidate();
        searchResultPanel.repaint();
    }

    public JScrollPane drawPosts(User user) {
        JPanel posts = new JPanel();
        posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(posts);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        for (User user1 : userList.getAllUsers()){
            if (user.getPosts() != null && (user.getConnections().contains(user1.getId()) || user.getId() == user1.getId())) {
                user1.getPosts().forEach(post -> {
                    PostCard postCard = new PostCard(post.getMessage());
                    postCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
                    posts.add(postCard);
                    JLabel username = new JLabel(user1.getName());
                    posts.add(username);
                    posts.add(new JSeparator(SwingConstants.HORIZONTAL));
                    posts.setBackground(Color.WHITE);
                });
            }
        }

        return scrollPane;
    }

    private class UserListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }
    }
}
