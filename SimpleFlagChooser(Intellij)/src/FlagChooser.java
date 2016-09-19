import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Created by alex on 9/1/2016.
 */
public class FlagChooser implements Runnable
{
    // List of country names
    private static final String[] FLAGS = {"Belarus", "Czech Republic", "Denmark", "Gibraltar", "Italy", "San Marino", "Switzerland", "United Kingdom"};

    // Images of the flags will be held in this
    private Image[] images;
    // Names of the countries will be displayed with this
    private JComboBox<String> flagBox;
    // Where the flag images will be displayed
    private JLabel flagDisplay = new JLabel();

    /**
     * Constructor.
     */
    public FlagChooser()
    {
        // Sets the array of images for selection
        try
        {
            images = new Image[]
                    {
                            // Images located in the main resources folder
                            ImageIO.read(getClass().getResource("resources/belarus_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/czech_republic_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/denmark_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/gibraltar_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/italy_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/san_marino_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/switzerland_flag.gif")),
                            ImageIO.read(getClass().getResource("resources/united_kingdom_flag.gif"))
                    };
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        // ComboBox with list of country names
        flagBox = new JComboBox<>(FLAGS);
        // Setting font size
        flagBox.setFont(new Font(null, 0, 36));
        // Draw flag on screen to fit flag display JLabel
        flagBox.addActionListener(event -> {
            flagDisplay.setIcon(new ImageIcon(images[flagBox.getSelectedIndex()].getScaledInstance(flagDisplay.getWidth(), flagDisplay.getHeight(),0)));
        });
    }

    /**
     * Main method - Start of program
     * @param args
     *      Required but unused parameter
     */
    public static void main(String[] args)
    {
        // Basically, run program
        SwingUtilities.invokeLater(new FlagChooser());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run()
    {
        // Main frame for program
        JFrame frame = new JFrame("Flags!");
        frame.addComponentListener(new ComponentAdapter()
        {
            /**
             * {@inheritDoc}
             * Redraws flag to accommodate for resized window
             */
            @Override
            public void componentResized(ComponentEvent e)
            {
                super.componentResized(e);
                flagDisplay.setIcon(new ImageIcon(images[flagBox.getSelectedIndex()].getScaledInstance(flagDisplay.getWidth(), flagDisplay.getHeight(),0)));
            }
        });

        // Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Single column design
        frame.setLayout(new GridLayout(0, 1));
        // Flag images displayed on top
        frame.add(flagDisplay);
        // Flag selections displayed on bottom
        frame.add(flagBox);
        // Minimum Size set so programs pops up at reasonable size
        frame.setMinimumSize(new Dimension(300, 250));
        // Packing...
        frame.pack();
        // Display!
        frame.setVisible(true);
    }
}
