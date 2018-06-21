package database;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DynamicBeat extends JFrame implements ItemListener, ActionListener {

	DBConnection connection = new DBConnection();

	private Image screenImage;
	private Graphics screenGraphic;

	private Image background = new ImageIcon(Main.class.getResource("/Images/MainScreen.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("/Images/MenuBar_01.png")));
	private JLabel VerifiedImage = new JLabel(new ImageIcon(Main.class.getResource("/Images/Verified.png")));
	//////////////////// 버튼 생성 ////////////////
	private ImageIcon MovieCalculateBasicImage = new ImageIcon(Main.class.getResource("/Images/Discount.png"));
	private ImageIcon MovieCalculateEnteredImage = new ImageIcon(Main.class.getResource("/Images/Discount_01.png"));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/3020BOBO.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/3030BOBO.png"));

	private ImageIcon LoginButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/LoginButton09-2.png"));
	private ImageIcon LoginButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/LoginButton09-1.png"));

	private ImageIcon LoginVerifiedBasicImage = new ImageIcon(Main.class.getResource("/Images/LoginBtn_01.png"));
	private ImageIcon LoginVerifiedEnteredImage = new ImageIcon(Main.class.getResource("/Images/LoginBtn_02.png"));

//	private ImageIcon RegisterVerifiedBasicImage = new ImageIcon(Main.class.getResource("/Images/RegisterBtn.png"));
//	private ImageIcon RegisterVerifiedEnteredImage = new ImageIcon(
//			Main.class.getResource("/Images/RegisterBtn_01.png"));

	private ImageIcon BackButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/BackBtn_01.png"));
	private ImageIcon BackButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/BackBtn_02.png"));

	private ImageIcon RegisterButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/Register.png"));
	private ImageIcon RegisterButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/Register_01.png"));

	private ImageIcon RgstButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/RegisterBtn.png"));
	private ImageIcon RgstButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/RegisterBtn_01.png"));

	private ImageIcon NowButtonBasicImage = new ImageIcon(Main.class.getResource("/Images/NowEvent.png"));
	private ImageIcon NowButtonEnteredImage = new ImageIcon(Main.class.getResource("/Images/NowEvent_01.png"));

	private ImageIcon PopularityBtnBasicImage = new ImageIcon(Main.class.getResource("/Images/CardPopularity.png"));
	private ImageIcon PopularityBtnEnteredImage = new ImageIcon(
			Main.class.getResource("/Images/CardPopularity_01.png"));

	private JTextField TF = new JTextField(20);
	private JPasswordField TF2 = new JPasswordField(20);
	private JTextField Reg = new JTextField(20);
	private JPasswordField Reg2 = new JPasswordField(20);
	private JComboBox<String> PopularList = new JComboBox<String>();
	private JComboBox<String> CalcList_1 = new JComboBox<String>();
	private JComboBox<String> CalcList_2 = new JComboBox<String>();
	private JComboBox<String> CalcList_3 = new JComboBox<String>();
	private JComboBox<String> CalcList_4 = new JComboBox<String>();

	int count = 0;
	int cCount = 0; //
	int rCount = 0; // 회원가입 버튼에 대한 cnt
	int rgCount = 0; // 레지스터 등록버튼에 대한 cnt

	////////////////// 나갈때 버튼 //////////////////////
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton MovieCalculateButton = new JButton(MovieCalculateBasicImage);
	private JButton LoginButton = new JButton(LoginButtonBasicImage);
	private JButton LoginVerifiedButton = new JButton(LoginVerifiedBasicImage);
//	private JButton RegisterVerifiedButton = new JButton(LoginVerifiedBasicImage);
	private JButton BackButton = new JButton(BackButtonBasicImage);
	private JButton RegisterButton = new JButton(RegisterButtonBasicImage);
	private JButton NowButton = new JButton(NowButtonBasicImage);
	private JButton PopularityButton = new JButton(PopularityBtnBasicImage);
	private JButton RgstButton = new JButton(RgstButtonBasicImage);
	//////////////////////////////////////////////////
	private boolean isLoginScreen = false;
	private boolean isLoginVerified = false;
	private boolean isBackButton = false;
	private boolean isPopularButton = false;
	private boolean isCalculateButton = false;
	private boolean isRegisterButton = false;
	private boolean isRgstButton = false;

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
		exitButton.setBounds(1250, 0, 30, 30);
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
		MovieCalculateButton.setBounds(0, 25, 307, 180);
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
				LoginButton.setVisible(false);
				NowButton.setVisible(false);
				RegisterButton.setVisible(false);
				PopularityButton.setVisible(false);

				////////// 이때 이미지를 바꿔버리는 코드이다. //////
				isCalculateButton = true;
				cCount = 1;
				background = new ImageIcon(Main.class.getResource("/Images/PriceScreen.png")).getImage();
				// 위에 PriceScreen 으로 바뀌어있는지 확인
				LoginButton.setVisible(false);
				// 게임시작 이벤트
			}
		});
		add(MovieCalculateButton);
		///////////////////////////////////////////////////

		/////////////// LoginButton ////////////
		LoginButton.setBounds(940, 30, 342, 170);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		LoginButton.setBorderPainted(false);
		LoginButton.setContentAreaFilled(false);
		LoginButton.setFocusPainted(false);
		///////////////////////////////////////
		LoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LoginButton.setIcon(LoginButtonEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				LoginButton.setIcon(LoginButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?
				LoginButton.setVisible(false);
				NowButton.setVisible(false);
				MovieCalculateButton.setVisible(false);
				RegisterButton.setVisible(false);
				PopularityButton.setVisible(false);
				////////// 이때 이미지를 바꿔버리는 코드이다. //////
				isLoginScreen = true;
				// isLoginVerified = true;
				isBackButton = false;
				// isLoginVerified = true;
				background = new ImageIcon(Main.class.getResource("/Images/LogScreen.png")).getImage();
				// 로그인 버튼 이벤트
				MovieCalculateButton.setVisible(false);
				LoginVerifiedButton.setVisible(true);
				BackButton.setVisible(true);
				System.out.println("1번");
			}
		});
		add(LoginButton);
		///////////////////////////////////////////////////

		/////////////// LoginVerifiedButton ////////////
		LoginVerifiedButton.setBounds(500, 550, 111, 38);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		LoginVerifiedButton.setBorderPainted(false);
		LoginVerifiedButton.setContentAreaFilled(false);
		LoginVerifiedButton.setFocusPainted(false);
		///////////////////////////////////////
		LoginVerifiedButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LoginVerifiedButton.setIcon(LoginVerifiedEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				LoginVerifiedButton.setIcon(LoginVerifiedBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?

				isLoginVerified = true;

				// 게임시작 이벤트

				// ///////// 시작버튼이 안보이게 하는거?
				// // LoginVerifiedButton.setVisible(true);
				// // 이걸 쓰면 된다.
				// isLoginVerified = true;
				// // isLoginVerified
				// // background = new
				// //
				// ImageIcon(Main.class.getResource("/Images/buttonbackground.png")).getImage();
				// System.out.println("2번");
				// MovieCalculateButton.setVisible(false);
				// LoginButton.setVisible(false);
				// NowButton.setVisible(false);
				// RgstButton.setVisible(false);
				// PopularityButton.setVisible(false);
				//
				// // 게임시작 이벤트
			}
		});
		///////////////////////////////////////////////////

		///////////////// RegisterVerifiedButton ////////////
		// RegisterVerifiedButton.setBounds(500, 550, 111, 38);
		// /////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		// RegisterVerifiedButton.setBorderPainted(false);
		// RegisterVerifiedButton.setContentAreaFilled(false);
		// RegisterVerifiedButton.setFocusPainted(false);
		// ///////////////////////////////////////
		// RegisterVerifiedButton.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseEntered(MouseEvent e) {
		// RegisterVerifiedButton.setIcon(LoginVerifiedEnteredImage);
		// }
		//
		// public void mouseExited(MouseEvent e) {
		// RegisterVerifiedButton.setIcon(LoginVerifiedBasicImage);
		// }
		//
		// @Override
		// public void mousePressed(MouseEvent e) {
		// ///////// 시작버튼이 안보이게 하는거?
		// // LoginVerifiedButton.setVisible(true);
		// // 이걸 쓰면 된다.
		// //isRegisterVerified = true;
		// // isLoginVerified
		// // background = new
		// //
		///////////////// ImageIcon(Main.class.getResource("/Images/buttonbackground.png")).getImage();
		// System.out.println("2번");
		// MovieCalculateButton.setVisible(false);
		// LoginButton.setVisible(false);
		// NowButton.setVisible(false);
		// RgstButton.setVisible(false);
		// PopularityButton.setVisible(false);
		//
		// // 게임시작 이벤트
		// }
		// });
		// add(RegisterVerifiedButton);
		///////////////////////////////////////////////////

		/////////////// BackButton ////////////
		BackButton.setBounds(650, 550, 111, 38);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		BackButton.setBorderPainted(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setFocusPainted(false);
		///////////////////////////////////////
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BackButton.setIcon(BackButtonEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				BackButton.setIcon(BackButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?

				BackButton.setVisible(false);
				isBackButton = true;

				isLoginVerified = false;
				////////// 이때 이미지를 바꿔버리는 코드이다. //////
				// background = new
				////////// ImageIcon(Main.class.getResource("/Images/buttonbackground.png")).getImage();

				///////// 추후 backMain()으로 만들어야 한다. ////////////////////

				// 메인화면으로 돌아가는 이벤트
			}
		});

		/////////////// NowButtonBasicImage ////////////
		NowButton.setBounds(0, 200, 307, 160);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		NowButton.setBorderPainted(false);
		NowButton.setContentAreaFilled(false);
		NowButton.setFocusPainted(false);
		///////////////////////////////////////
		NowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				NowButton.setIcon(NowButtonEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				NowButton.setIcon(NowButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?
				NowButton.setVisible(false);
				LoginButton.setVisible(false);
				MovieCalculateButton.setVisible(false);
				RegisterButton.setVisible(false);
				PopularityButton.setVisible(false);
				////////// 이때 이미지를 바꿔버리는 코드이다. //////
				background = new ImageIcon(Main.class.getResource("/Images/buttonbackground.png")).getImage();
				LoginButton.setVisible(false);
				// 게임시작 이벤트
			}
		});
		add(NowButton);
		///////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////

		/////////////// RegisterButton ////////////
		RegisterButton.setBounds(940, 200, 342, 160);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		RegisterButton.setBorderPainted(false);
		RegisterButton.setContentAreaFilled(false);
		RegisterButton.setFocusPainted(false);
		///////////////////////////////////////
		RegisterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				RegisterButton.setIcon(RegisterButtonEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				RegisterButton.setIcon(RegisterButtonBasicImage);
			}

			@Override

			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?
				RegisterButton.setVisible(false);
				LoginButton.setVisible(false);
				MovieCalculateButton.setVisible(false);
				NowButton.setVisible(false);
				LoginButton.setVisible(false);
				PopularityButton.setVisible(false);
				// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
				isRegisterButton = true;
				rCount = 1;
				// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
				////////// 이때 이미지를 바꿔버리는 코드이다. //////
				background = new ImageIcon(Main.class.getResource("/Images/RgstScreen.png")).getImage();
				 RgstButton.setVisible(true);
				BackButton.setVisible(true);
				LoginButton.setVisible(false);
				// 게임시작 이벤트
			}
		});
		add(RegisterButton);
		///////////////////////////////////////////////////

		/////////////// PopularityButton ////////////
		PopularityButton.setBounds(0, 360, 307, 180);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		PopularityButton.setBorderPainted(false);
		PopularityButton.setContentAreaFilled(false);
		PopularityButton.setFocusPainted(false);
		///////////////////////////////////////
		PopularityButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PopularityButton.setIcon(PopularityBtnEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				PopularityButton.setIcon(PopularityBtnBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?
				PopularityButton.setVisible(false);
				LoginButton.setVisible(false);
				MovieCalculateButton.setVisible(false);
				RegisterButton.setVisible(false);
				NowButton.setVisible(false);
				LoginButton.setVisible(false);

				////////// 이때 이미지를 바꿔버리는 코드이다. //////
				background = new ImageIcon(Main.class.getResource("/Images/ReadyBG.png")).getImage();
				LoginButton.setVisible(false);
				isPopularButton = true;
				count = 1;
				// 게임시작 이벤트
			}
		});
		add(PopularityButton);

		/////////////// RgstButton ////////////
		RgstButton.setBounds(520, 550, 110, 37);
		/////////////////////// 네모모양이 아니고 bound를 없애주는 함수 /////////
		RgstButton.setBorderPainted(false);
		RgstButton.setContentAreaFilled(false);
		RgstButton.setFocusPainted(false);
		///////////////////////////////////////
		RgstButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				RgstButton.setIcon(RgstButtonEnteredImage);
			}

			public void mouseExited(MouseEvent e) {
				RgstButton.setIcon(RgstButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				///////// 시작버튼이 안보이게 하는거?
				PopularityButton.setVisible(false);
				MovieCalculateButton.setVisible(false);
				RegisterButton.setVisible(false);
				NowButton.setVisible(false);
				LoginButton.setVisible(false);
//				RgstButton.setVisible(true);
				isRgstButton = true;
				rgCount = 1;
				////////// 이때 이미지를 바꿔버리는 코드이다. //////

				// 게임시작 이벤트
			}
		});

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
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 해당//
		// 넣음.
		screenGraphic = screenImage.getGraphics(); //
		screenDraw(screenGraphic); // 이 그래픽에 어떠한 그림을 그려주는것.
		g.drawImage(screenImage, 0, 0, null); // 0.0의 위치에 그려주는것.
//		System.out.println("paint호출");
	}

	public void screenDraw(Graphics g) {
//		System.out.println("screenDraw호출");
		//////////////// drawImage는 screenImage라는 변수안에 background같은 Image를
		//////////////// 그려주는 것이다.
		g.drawImage(background, 0, 0, null);

		// paint는 JLabel과 관련있다.
		paintComponents(g); // 고정된 이미지, 움직이는게 아닌 것은 여기서 그린다.
		////////////////////////////////////

//		if (isLoginScreen && (count == 0)) {
//			// Login();
//			TF = new JTextField(20);
//			TF2 = new JPasswordField(20);
//			TF.setBounds(500, 330, 270, 30);
//			TF2.setBounds(500, 430, 270, 30);
//			TF2.setVisible(true);
//			TF.setVisible(true);
//			TF.setOpaque(true);
//			TF2.setOpaque(true);
//
//			this.add(TF);
//			this.add(TF2);
//
//			add(LoginVerifiedButton);
//			add(BackButton);
//			this.revalidate();
//			this.repaint();
//			count = 1;
//		}
		
		if (isLoginScreen&&(count==0)) {
			// Login();
//			TF = new JTextField(20);
//			TF2 = new JPasswordField(20);
			TF.setBounds(500, 330, 270, 30);
			TF2.setBounds(500, 430, 270, 30);
			TF2.setVisible(true);
			TF.setVisible(true);
			TF.setOpaque(true);
			TF2.setOpaque(true);

			this.add(TF);
			this.add(TF2);

			add(LoginVerifiedButton);
			add(BackButton);
			this.revalidate();
			count = 1;
			// this.repaint();
		}
		
		if (isLoginVerified) {
			// LoginVerified();
			if (isLoginVerified) {
				////////////////// 여기서 for문돌려서 id랑 pass 맞는지 검사해야함
				String id = TF.getText();
				String pw = String.valueOf(TF2.getPassword());
				if (connection.login(id, pw)) {
					System.out.println("로그인성공");
					isLoginVerified = false;
					isBackButton = true;

				} else {
					System.out.println("로그인실패");
					isLoginVerified = false;
				}

				////////////////// /////////////////////////
				VerifiedImage.setBounds(390, 240, 484, 47);
				add(VerifiedImage);
				this.revalidate();
				this.repaint();
			}
		}
		if (isBackButton) {
			backMain();
		}

		if (isPopularButton && (count == 1)) {

			PopularList.addItem("7년의밤");
			PopularList.addItem("택시운전사");
			PopularList.addItem("아몰라");
			System.out.println("여까지 들어왔냐  " + isPopularButton);
			PopularList.setBounds(500, 400, 200, 30);
			PopularList.setEditable(false);
			PopularList.addActionListener(this);
			PopularList.addItemListener(this);

			add(PopularList);
			count = 0;
			PopularList.setVisible(true);

		}

		if (isCalculateButton && (cCount == 1)) {
			MovieCalculate();
		}

		if (isRegisterButton && (rCount == 1)) {
			Register();
		}

		if (isRgstButton && (rgCount == 1)) {
			Rgst();
		}

		this.repaint();
		// 전체 화면의 이미지를 매 순간마다 불러온다는 의미임.
	}

	public void Login() {
		// TF = new JTextField(20);
		// TF2 = new JPasswordField(20);

		TF.setBounds(500, 330, 270, 30);
		TF2.setBounds(500, 430, 270, 30);
		TF2.setVisible(true);
		TF.setVisible(true);
		TF.setOpaque(true);
		TF2.setOpaque(true);

		this.add(TF);
		this.add(TF2);

		add(LoginVerifiedButton);
		add(BackButton);
	}

//	public void LoginVerified() {
//		if (isLoginVerified) {
//			////////////////// 여기서 for문돌려서 id랑 pass 맞는지 검사해야함
//			////////////////// ///////////////////
//
//			////////////////// /////////////////////////
//			VerifiedImage.setBounds(390, 240, 484, 47);
//			add(VerifiedImage);
//
//		}
//	}

	public void MovieCalculate() {

		CalcList_1.addItem("어벤져스");
		CalcList_1.addItem("7년의밤");
		CalcList_1.addItem("택시운전사");
		CalcList_1.addItem("아몰라");

		CalcList_1.setBounds(178, 144, 150, 40);
		CalcList_1.setEditable(false);
		CalcList_1.addActionListener(this);
		CalcList_1.addItemListener(this);

		add(CalcList_1);

		CalcList_1.setVisible(true);

		CalcList_2.addItem("BC");
		CalcList_2.addItem("신한");
		CalcList_2.addItem("대구");
		CalcList_2.addItem("삼성카드");
		CalcList_2.addItem("현대");
		CalcList_2.addItem("농협");
		CalcList_2.addItem("문화누리");
		CalcList_2.addItem("카카오뱅크");
		CalcList_2.setBounds(178, 292, 150, 40);
		CalcList_2.setEditable(false);
		CalcList_2.addActionListener(this);
		CalcList_2.addItemListener(this);
		add(CalcList_2);
		CalcList_2.setVisible(true);

		CalcList_3.addItem("1");
		CalcList_3.addItem("2");
		CalcList_3.addItem("3");
		CalcList_3.addItem("4");
		CalcList_3.addItem("5");
		CalcList_3.addItem("6");
		CalcList_3.addItem("7");
		CalcList_3.addItem("8");
		CalcList_3.setBounds(178, 444, 150, 40);
		CalcList_3.setEditable(false);
		CalcList_3.addActionListener(this);
		CalcList_3.addItemListener(this);
		add(CalcList_3);
		CalcList_3.setVisible(true);

		CalcList_4.addItem("Economy");
		CalcList_4.addItem("Standard");
		CalcList_4.addItem("Prime");
		CalcList_4.addItem("SweetBox");
		CalcList_4.setBounds(178, 571, 150, 40);
		CalcList_4.setEditable(false);
		CalcList_4.addActionListener(this);
		CalcList_4.addItemListener(this);
		add(CalcList_4);
		CalcList_4.setVisible(true);

		cCount = 0;
	}

	public void setLogin(JTextField TF, JPasswordField TF2) {
		this.TF = TF;
		this.TF2 = TF2;
	}

	public JTextField getLoginID() {
		return TF;
	}

	public JPasswordField getPassword() {
		return TF2;
	}

	public void backMain() {
		isLoginVerified = false;
		isBackButton = false;
		isLoginScreen = false;

		VerifiedImage.setVisible(false);
		TF.setOpaque(false);
		TF.setVisible(false);
		TF2.setVisible(false);
		TF2.setOpaque(false);

		LoginVerifiedButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("/Images/MainImage_02.png")).getImage();
		LoginButton.setVisible(true);
		MovieCalculateButton.setVisible(true);
		RegisterButton.setVisible(true);
		NowButton.setVisible(true);
		BackButton.setVisible(false);
		PopularityButton.setVisible(true);
		Reg.setVisible(false);
		Reg2.setVisible(false);
		RgstButton.setVisible(false);
		CalcList_2.setVisible(false);
	}

	public void Register() {
		rCount = 0;
		isRegisterButton = false;
		Reg.setBounds(540, 293, 270, 30);
		Reg2.setBounds(540, 387, 270, 30);
		Reg.setVisible(true);
		Reg2.setVisible(true);
		Reg.setOpaque(true);
		Reg2.setOpaque(true);
		
		
		CalcList_2.addItem("BC");
		CalcList_2.addItem("신한");
		CalcList_2.addItem("대구");
		CalcList_2.addItem("삼성카드");
		CalcList_2.addItem("현대");
		CalcList_2.addItem("농협");
		CalcList_2.addItem("문화누리");
		CalcList_2.addItem("카카오뱅크");
		CalcList_2.setBounds(540, 468, 270, 30);
		CalcList_2.setEditable(false);
		CalcList_2.addActionListener(this);
		CalcList_2.addItemListener(this);
		add(CalcList_2);
		CalcList_2.setVisible(true);
		this.add(Reg);
		this.add(Reg2);
		RgstButton.setVisible(true);
		add(RgstButton);
		add(BackButton);
		System.out.println("Register 호출");
		
	}

	public void Rgst() {
		rgCount=0;
		String id = Reg.getText();
		String pw = String.valueOf(Reg2.getPassword());
		String value = CalcList_2.getSelectedItem().toString();
		if(connection.register(id, pw, value)==true)
		{
			// 등록이 완료되었으면
			////////////////////////////////////
			background = new ImageIcon(Main.class.getResource("/Images/MainImage_02.png")).getImage();
			BackButton.setVisible(false);
			Reg.setVisible(false);
			Reg2.setVisible(false);
			CalcList_2.setVisible(false);

			LoginButton.setVisible(true);
			MovieCalculateButton.setVisible(true);
			RegisterButton.setVisible(true);
			NowButton.setVisible(true);
			PopularityButton.setVisible(true);
			
			System.out.println("회원가입성공");
			isBackButton = true;
		}
		else
		{
			System.out.println("회원가입실패");
		}
	
		VerifiedImage.setBounds(390, 240, 484, 47);
		add(VerifiedImage);
		this.revalidate();
		this.repaint();
		/////////////// 여기서 등록에 대한 값 연결해야함 //////
		// if 등록이 완료됬다면 완료되었다 아니면 아니다 표시 이건 나중에
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

}
