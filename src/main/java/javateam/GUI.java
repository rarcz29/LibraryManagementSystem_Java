import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener  {
    private static JLabel userLabel;
    private static JTextField userTxt;
    private static JLabel passwordLabel;
    private static JPasswordField passwordTxt;
    private static JButton loginButton;
    private static JLabel loginSuccess;
    private static JLabel libraryLabel;
    private static String fontName = "Salina";
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    public GUI() {
        JPanel secondPanel = new JPanel();
        JPanel thirdPanel = new JPanel();

        frame.setSize(350,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.red);
        frame.setTitle("Library");
        frame.setPreferredSize(new Dimension(400, 300));
        frame.add(panel);
        frame.setResizable(false);

        panel.setBackground(Color.darkGray);
        panel.setLayout(null);

        secondPanel.setBackground(Color.cyan);
        frame.add(secondPanel,BorderLayout.NORTH);
        thirdPanel.setBackground(Color.cyan);
        frame.add(thirdPanel,BorderLayout.SOUTH);

        libraryLabel = new JLabel("Log in to our library, please.");
        libraryLabel.setFont(new Font(fontName, Font.PLAIN, 20));
        libraryLabel .setBounds(60,10,300,25);
        libraryLabel.setForeground(Color.white);
        panel.add(libraryLabel );

        userLabel = new JLabel("User");
        userLabel.setFont(new Font(fontName, Font.PLAIN, 20));
        userLabel.setBounds(10, 60, 80, 25);
        userLabel.setForeground(Color.white);
        panel.add(userLabel);

        userTxt = new JTextField(20);
        userTxt.setBounds(100,60,165,25);
        panel.add(userTxt);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font(fontName, Font.PLAIN, 20));
        passwordLabel.setBounds(10,100,80,25);
        passwordLabel.setForeground(Color.white);
        panel.add(passwordLabel);

        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(100,100,165,25);
        panel.add(passwordTxt);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font(fontName, Font.PLAIN, 20));
        loginButton.setBounds(110,150,100,30);
        loginButton.setBackground(Color.cyan);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        loginSuccess = new JLabel("");
        panel.add(loginSuccess);


        frame.setVisible(true);
    }

    public static void main(String [] args){
        new GUI();
    }
    public static void findBook(){
        JPanel libraryPanel = new JPanel();
        libraryPanel.setBackground(Color.darkGray);
        libraryPanel.setLayout(null);
        frame.remove(panel);
        frame.add(libraryPanel);

        libraryLabel = new JLabel("Welcome! Find your book with us.");
        libraryLabel.setFont(new Font(fontName, Font.PLAIN, 20));
        libraryLabel .setBounds(40,10,300,25);
        libraryLabel.setForeground(Color.white);
        libraryPanel.add(libraryLabel );

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font(fontName, Font.PLAIN, 20));
        titleLabel.setBounds(10, 60, 80, 25);
        titleLabel.setForeground(Color.white);
        libraryPanel.add(titleLabel);

        JTextField titleTxt = new JTextField(20);
        titleTxt.setBounds(100,60,165,25);
        libraryPanel.add(titleTxt);

        JLabel authorLabel = new JLabel("Author");
        authorLabel.setFont(new Font(fontName, Font.PLAIN, 20));
        authorLabel.setBounds(10,100,80,25);
        authorLabel.setForeground(Color.white);
        libraryPanel.add(authorLabel);

        JTextField authorTxt = new JTextField(20);
        authorTxt.setBounds(100,100,165,25);
        libraryPanel.add(authorTxt);

        JButton findButton = new JButton("Find a book");
        findButton.setFont(new Font(fontName, Font.PLAIN, 20));
        findButton.setBounds(100,150,150,30);
        findButton.setBackground(Color.cyan);
        findButton.addActionListener(new GUI ());
        libraryPanel.add(findButton);

        String getTitleField = titleTxt.getText();
        String getAuthorField  = authorTxt.getText();
        System.out.println(getTitleField + ";" + getAuthorField);

        if(getTitleField.equals("zakochani") && getAuthorField.equals("jan")){
            System.out.println("Książka jest");

        }
        else{
            System.out.println("Książki nie ma");
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String getUserField = userTxt.getText();
        String getPasswordField  = new String(passwordTxt.getPassword());
        System.out.println(getUserField + "," + getPasswordField);

        if(getUserField.equals("Ola") && getPasswordField.equals("1234")){

            loginSuccess.setBounds(30,200,300,25);
            loginSuccess.setText("Good, you were logged successfully!");
            loginSuccess.setForeground(Color.white);
            findBook();

        }
        else{
            loginSuccess.setBounds(100,200,300,25);
            loginSuccess.setFont(new Font(fontName, Font.PLAIN, 20));
            loginSuccess.setText("Incorrect data!");
            loginSuccess.setForeground(Color.white);
        }

    }
}
