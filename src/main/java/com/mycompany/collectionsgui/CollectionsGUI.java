package com.mycompany.collectionsgui;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
 
public class CollectionsGUI extends JFrame {
 
    // Colors (match the design mockup)
    static final Color BLUE = new Color(59, 110, 153);
    static final Color BG = new Color(244, 247, 250);
    static final Color TAB_IDLE = new Color(227, 234, 241);
    static final Color BORDER = new Color(201, 213, 224);
    static final Color TEXT = new Color(31, 45, 58);
 
    // Fonts
    static final Font UI = new Font("SansSerif", Font.PLAIN, 14);
    static final Font UI_BOLD = new Font("SansSerif", Font.BOLD, 14);
    static final Font MONO = new Font("Monospaced", Font.PLAIN, 14);
 
    // Collections
    Map<String, String> studentMap = new LinkedHashMap<>();
    Set<String> studentSet = new LinkedHashSet<>();
    ArrayList<String> studentList = new ArrayList<>();
 
    // Map
    JTextField mapName = field(8);
    JTextField mapCourse = field(8);
    JTextArea mapArea = area();
 
    // Set
    JTextField setField = field(14);
    JTextArea setArea = area();
 
    // List
    JTextField listField = field(14);
    JTextArea listArea = area();
 
    public CollectionsGUI() {
 
        setTitle("Maps, Set, and Collections");
        setSize(760, 470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BG);
 
        // Header
        JLabel title = new JLabel("Maps, Set, and Collections Framework", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
 
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(new EmptyBorder(18, 24, 18, 24));
        header.add(title);
 
        add(header, BorderLayout.NORTH);
 
        // Tabs
        UIManager.put("TabbedPane.selected", BLUE);
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(UI_BOLD);
        tabs.setBackground(TAB_IDLE);
        tabs.setForeground(TEXT);
        tabs.add("Map", mapPanel());
        tabs.add("Set", setPanel());
        tabs.add("ArrayList", listPanel());
 
        add(tabs);
 
        // Sample Data
        studentMap.put("Juan", "BSIT");
        studentSet.add("Juan");
        studentList.add("Juan");
 
        refresh();
    }
 
    // MAP PANEL
    JPanel mapPanel() {
 
        JPanel p = new JPanel(new BorderLayout(0, 12));
        styleBody(p);
 
        JPanel top = row();
        top.add(label("Name"));
        top.add(mapName);
        top.add(label("Course"));
        top.add(mapCourse);
 
        JButton add = button("Add");
        JButton search = button("Search");
        JButton update = button("Update");
        JButton remove = button("Remove");
 
        top.add(add);
        top.add(search);
        top.add(update);
        top.add(remove);
 
        p.add(top, BorderLayout.NORTH);
        p.add(scroll(mapArea));
 
        // Pressing Enter in either field clicks Add
        mapName.addActionListener(e -> add.doClick());
        mapCourse.addActionListener(e -> add.doClick());
 
        add.addActionListener(e -> {
            String name = mapName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.");
                return;
            }
            // Duplicate check: a name can only appear once
            if (studentMap.containsKey(name)) {
                JOptionPane.showMessageDialog(this,
                        "\"" + name + "\" already exists. Use Update to change the course.");
                return;
            }
            studentMap.put(name, mapCourse.getText().trim());
            refresh();
        });
 
        search.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    studentMap.getOrDefault(mapName.getText().trim(), "Not Found"));
        });
 
        update.addActionListener(e -> {
            studentMap.put(mapName.getText().trim(), mapCourse.getText().trim());
            refresh();
        });
 
        remove.addActionListener(e -> {
            studentMap.remove(mapName.getText().trim());
            refresh();
        });
 
        return p;
    }
 
    // SET PANEL
    JPanel setPanel() {
 
        JPanel p = new JPanel(new BorderLayout(0, 12));
        styleBody(p);
 
        JPanel top = row();
        top.add(label("Name"));
        top.add(setField);
 
        JButton add = button("Add");
        JButton search = button("Search");
        JButton remove = button("Remove");
 
        top.add(add);
        top.add(search);
        top.add(remove);
 
        p.add(top, BorderLayout.NORTH);
        p.add(scroll(setArea));
 
        // Pressing Enter in the field clicks Add
        setField.addActionListener(e -> add.doClick());
 
        add.addActionListener(e -> {
            String name = setField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.");
                return;
            }
            // Set.add() returns false when the name is already in the set
            if (!studentSet.add(name)) {
                JOptionPane.showMessageDialog(this, "\"" + name + "\" already exists.");
                return;
            }
            refresh();
        });
 
        search.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    studentSet.contains(setField.getText().trim()) ? "Found" : "Not Found");
        });
 
        remove.addActionListener(e -> {
            studentSet.remove(setField.getText().trim());
            refresh();
        });
 
        return p;
    }
 
    // LIST PANEL
    JPanel listPanel() {
 
        JPanel p = new JPanel(new BorderLayout(0, 12));
        styleBody(p);
 
        JPanel top = row();
        top.add(label("Name"));
        top.add(listField);
 
        JButton add = button("Add");
        JButton search = button("Search");
        JButton remove = button("Remove");
 
        top.add(add);
        top.add(search);
        top.add(remove);
 
        p.add(top, BorderLayout.NORTH);
        p.add(scroll(listArea));
 
        // Pressing Enter in the field clicks Add
        listField.addActionListener(e -> add.doClick());
 
        add.addActionListener(e -> {
            String name = listField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.");
                return;
            }
            // An ArrayList allows duplicates, so check before adding
            if (studentList.contains(name)) {
                JOptionPane.showMessageDialog(this, "\"" + name + "\" already exists.");
                return;
            }
            studentList.add(name);
            refresh();
        });
 
        search.addActionListener(e -> {
            String name = listField.getText().trim();
            // indexOf() returns the position of the name, or -1 if it is not in the list
            int index = studentList.indexOf(name);
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "\"" + name + "\" was not found.");
            } else {
                JOptionPane.showMessageDialog(this,
                        "\"" + name + "\" is found at index " + index + ".");
            }
        });
 
        remove.addActionListener(e -> {
            studentList.remove(listField.getText().trim());
            refresh();
        });
 
        return p;
    }
 
    // ---------- Design helpers ----------
 
    static void styleBody(JPanel p) {
        p.setBackground(BG);
        p.setBorder(new EmptyBorder(16, 20, 20, 20));
    }
 
    static JPanel row() {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        r.setBackground(BG);
        return r;
    }
 
    static JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(UI_BOLD);
        l.setForeground(TEXT);
        return l;
    }
 
    static JTextField field(int cols) {
        JTextField f = new JTextField(cols);
        f.setFont(UI);
        f.setPreferredSize(new Dimension(f.getPreferredSize().width, 36));
        f.setBorder(new CompoundBorder(
                new LineBorder(new Color(143, 163, 181), 1, true),
                new EmptyBorder(0, 10, 0, 10)));
        return f;
    }
 
    static JTextArea area() {
        JTextArea a = new JTextArea(8, 30);
        a.setFont(MONO);
        a.setEditable(false);
        a.setMargin(new Insets(12, 14, 12, 14));
        return a;
    }
 
    static JScrollPane scroll(JTextArea a) {
        JScrollPane s = new JScrollPane(a);
        s.setBorder(new LineBorder(BORDER, 1, true));
        return s;
    }
 
    // Button Design
    JButton button(String text) {
        JButton b = new JButton(text);
        b.setFont(UI_BOLD);
        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(b.getPreferredSize().width + 8, 36));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }
 
    // Refresh Display
    void refresh() {
 
        mapArea.setText("Student Records\n\n");
        for (String key : studentMap.keySet()) {
            mapArea.append(key + " = " + studentMap.get(key) + "\n");
        }
 
        setArea.setText("Unique Names\n\n");
        for (String s : studentSet) {
            setArea.append(s + "\n");
        }
 
        listArea.setText("Student List\n\n");
        for (int i = 0; i < studentList.size(); i++) {
            listArea.append(i + " : " + studentList.get(i) + "\n");
        }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CollectionsGUI().setVisible(true));
    }
}
 