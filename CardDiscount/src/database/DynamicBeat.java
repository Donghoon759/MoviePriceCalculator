package database;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

   private Image screenImage;
   private Graphics screenGraphic;

   
   
   private Image background = new ImageIcon(Main.class.getResource("/Images/Background02.png")).getImage();
   private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("/Images/JHbar01.png")));
   //////////////////// ��ư ���� ////////////////
   private ImageIcon MovieCalculateBasicImage = new ImageIcon(Main.class.getResource("/Images/Movie01.png"));
   private ImageIcon MovieCalculateEnteredImage = new ImageIcon(Main.class.getResource("/Images/Movie01-1.png"));
   
   private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/3020BOBO.png"));
   private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/3030BOBO.png"));
   ////////////////// ������ ��ư //////////////////////
   private JButton exitButton = new JButton(exitButtonBasicImage);
   private JButton MovieCalculateButton = new JButton(MovieCalculateBasicImage);
   //private JButton exitButton = new JButton(exitButtonBasicImage);

   
   ///////////////////////////////////
   private int mouseX, mouseY;

   public DynamicBeat() {

      setUndecorated(true); // ����ÿ� �⺻������ �����ϴ� �޴��ٰ� ������ �ʰ� �ȴ�.
      setTitle("Dynamic Beat");
      setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
      setResizable(false);
      setLocationRelativeTo(null); // ����� ���߾ӿ� �߰Ե�
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����â ���� �ƿ� ���ԵǴ°�
      setVisible(true); // ����â�� ���������� ��µǰ� �� �ִ°�.
      setBackground(new Color(0, 0, 0, 0)); // paintComponents�� �� ����� ȸ���� �ƴ϶�
                                    // �Ͼ������ �ٲ�� ������ִ°Ŵ�.
      setLayout(null); // ��ư�̳� JLabel�� ���� �� �� ��ġ�� �־����� �ȴ�.

      ///////////////// ��ư ó�� �޼ҵ�!!//////////////
      exitButton.setBounds(1170, 0, 30, 30);
      /////////////////////// �׸����� �ƴϰ� bound�� �����ִ� �Լ� /////////
      exitButton.setBorderPainted(false);
      exitButton.setContentAreaFilled(false);
      exitButton.setFocusPainted(false);
      ///////////////////////////////////////
      exitButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            exitButton.setIcon(exitButtonEnteredImage);
         }

         public void mouseExited(MouseEvent e) {
            exitButton.setIcon(exitButtonBasicImage);
         }

         @Override
         public void mousePressed(MouseEvent e) {
            System.exit(0);
         }
      });
      add(exitButton);
      ///////////////////////////////////////////////////

      /////////////// MovieCalculateButton ////////////
      MovieCalculateButton.setBounds(0, 20, 307, 180);
      /////////////////////// �׸����� �ƴϰ� bound�� �����ִ� �Լ� /////////
      MovieCalculateButton.setBorderPainted(false);
      MovieCalculateButton.setContentAreaFilled(false);
      MovieCalculateButton.setFocusPainted(false);
      ///////////////////////////////////////
      MovieCalculateButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            MovieCalculateButton.setIcon(MovieCalculateEnteredImage);
         }

         public void mouseExited(MouseEvent e) {
            MovieCalculateButton.setIcon(MovieCalculateBasicImage);
         }

         @Override
         public void mousePressed(MouseEvent e) {
            ///////// ���۹�ư�� �Ⱥ��̰� �ϴ°�?
            MovieCalculateButton.setVisible(false);
            ////////// �̶� �̹����� �ٲ������ �ڵ��̴�. //////
            background = new ImageIcon(Main.class.getResource("/Images/buttonbackground.png")).getImage();
            // ���ӽ��� �̺�Ʈ
         }
      });
      add(MovieCalculateButton);
      ///////////////////////////////////////////////////
      
      
      /////////////////////// ����ʹ� �޴��� ó�� �κ� ////////////////////////
      menuBar.setBounds(0, 0, 1280, 30); // ��ġ�� ũ�⸦ �����ش�.
      menuBar.addMouseListener(new MouseAdapter() {
         @Override // ���콺�� �ش� ��ư�� ������ ���� �̺�Ʈ ó�� //
         public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
         }
      });
      /////////////////////////////////////////
      menuBar.addMouseMotionListener(new MouseMotionAdapter() {
         @Override // �巡�� �ÿ� ��ġ�� �ٲ��ִ� �޼ҵ�
         public void mouseDragged(MouseEvent e) {
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            setLocation(x - mouseX, y - mouseY);
         }
      });
      ///////////////////////////////////////////
      add(menuBar); // JFrame�� �޴��ٰ� �������� ���̴�.

      // �̹��� ������ �ʱ�ȭ
   }

   public void paint(Graphics g) {
      screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // �ش�// ũ����                              // ����.
      screenGraphic = screenImage.getGraphics(); //
      screenDraw(screenGraphic); // �� �׷��ȿ� ��� �׸��� �׷��ִ°�.
      g.drawImage(screenImage, 0, 0, null); // 0.0�� ��ġ�� �׷��ִ°�.
   }

   public void screenDraw(Graphics g) {

      //////////////// drawImage�� screenImage��� �����ȿ� background���� Image��
      //////////////// �׷��ִ� ���̴�.
      g.drawImage(background, 0, 0, null);
      // paint�� JLabel�� �����ִ�.
      paintComponents(g); // ������ �̹���, �����̴°� �ƴ� ���� ���⼭ �׸���.
      //////////////////////
      this.repaint(); // ��ü ȭ���� �̹����� �� �������� �ҷ��´ٴ� �ǹ���.
   }

}