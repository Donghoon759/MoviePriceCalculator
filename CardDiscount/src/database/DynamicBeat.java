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
   //////////////////// 버튼 생성 ////////////////
   private ImageIcon MovieCalculateBasicImage = new ImageIcon(Main.class.getResource("/Images/Movie01.png"));
   private ImageIcon MovieCalculateEnteredImage = new ImageIcon(Main.class.getResource("/Images/Movie01-1.png"));
   
   private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/3020BOBO.png"));
   private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/3030BOBO.png"));
   ////////////////// 나갈때 버튼 //////////////////////
   private JButton exitButton = new JButton(exitButtonBasicImage);
   private JButton MovieCalculateButton = new JButton(MovieCalculateBasicImage);
   //private JButton exitButton = new JButton(exitButtonBasicImage);

   
   ///////////////////////////////////
   private int mouseX, mouseY;

   public DynamicBeat() {

      setUndecorated(true); // 실행시에 기본적으로 존재하는 메뉴바가 보이지 않게 된다.
      setTitle("Dynamic Beat");
      setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
      setResizable(false);
      setLocationRelativeTo(null); // 실행시 정중앙에 뜨게됨
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창 끄면 아에 끄게되는거
      setVisible(true); // 게임창이 정상적으로 출력되게 해 주는것.
      setBackground(new Color(0, 0, 0, 0)); // paintComponents할 때 배경이 회색이 아니라
                                    // 하얀색으로 바뀌게 만들어주는거다.
      setLayout(null); // 버튼이나 JLabel를 넣을 때 그 위치에 넣어지게 된다.

      ///////////////// 버튼 처리 메소드!!//////////////
      exitButton.setBounds(1170, 0, 30, 30);
      /////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
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
      /////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
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
            ///////// 시작버튼이 안보이게 하는거?
            MovieCalculateButton.setVisible(false);
            ////////// 이때 이미지를 바꿔버리는 코드이다. //////
            background = new ImageIcon(Main.class.getResource("/Images/buttonbackground.png")).getImage();
            // 게임시작 이벤트
         }
      });
      add(MovieCalculateButton);
      ///////////////////////////////////////////////////
      
      
      /////////////////////// ↓부터는 메뉴바 처리 부분 ////////////////////////
      menuBar.setBounds(0, 0, 1280, 30); // 위치와 크기를 정해준다.
      menuBar.addMouseListener(new MouseAdapter() {
         @Override // 마우스로 해당 버튼을 눌렀을 때의 이벤트 처리 //
         public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
         }
      });
      /////////////////////////////////////////
      menuBar.addMouseMotionListener(new MouseMotionAdapter() {
         @Override // 드래그 시에 위치를 바꿔주는 메소드
         public void mouseDragged(MouseEvent e) {
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            setLocation(x - mouseX, y - mouseY);
         }
      });
      ///////////////////////////////////////////
      add(menuBar); // JFrame에 메뉴바가 정해지는 것이다.

      // 이미지 변수의 초기화
   }

   public void paint(Graphics g) {
      screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 해당// 크기의                              // 넣음.
      screenGraphic = screenImage.getGraphics(); //
      screenDraw(screenGraphic); // 이 그래픽에 어떠한 그림을 그려주는것.
      g.drawImage(screenImage, 0, 0, null); // 0.0의 위치에 그려주는것.
   }

   public void screenDraw(Graphics g) {

      //////////////// drawImage는 screenImage라는 변수안에 background같은 Image를
      //////////////// 그려주는 것이다.
      g.drawImage(background, 0, 0, null);
      // paint는 JLabel과 관련있다.
      paintComponents(g); // 고정된 이미지, 움직이는게 아닌 것은 여기서 그린다.
      //////////////////////
      this.repaint(); // 전체 화면의 이미지를 매 순간마다 불러온다는 의미임.
   }

}