package janken.window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import janken.enums.Hands;
import janken.enums.Status;

public class MainWindow {
	private final JFrame frame;

	private final JLabel messageLabel;

	private final JButton rockButton;

	private final JButton scissorsButton;

	private final JButton paperButton;

	private Status playState;

	private Hands opponHands;

	public MainWindow() {

		this.frame = new JFrame("じゃんけんゲーム");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setBounds(200, 200, 600, 400);

		var pane = this.frame.getContentPane();

		var canvas = new JPanel();

		canvas.setLayout(null);

		this.messageLabel = new JLabel("じゃーんけーん・・・");
		this.messageLabel.setBounds(20, 20, 600, 30);
		canvas.add(this.messageLabel);

		this.rockButton = new JButton(Hands.Rock.getDisplay());
		this.rockButton.setBounds(100, 100, 100, 40);
		this.rockButton.addActionListener((e) -> this.selectHand(Hands.Rock));
		canvas.add(this.rockButton);

		this.scissorsButton = new JButton(Hands.Scissors.getDisplay());
		scissorsButton.setBounds(250, 100, 100, 40);
		this.scissorsButton.addActionListener((e) -> this.selectHand(Hands.Scissors));
		canvas.add(scissorsButton);

		this.paperButton = new JButton(Hands.Paper.getDisplay());
		paperButton.setBounds(400, 100, 100, 40);
		this.paperButton.addActionListener((e) -> this.selectHand(Hands.Paper));
		canvas.add(paperButton);

		pane.add(canvas);
	}

	public void show() {
		this.init();
		this.frame.setVisible(true);
	}

	public void init() {
		this.opponHands = Hands.getRandomHands();
		this.playState = Status.Wait;

	}

	public void selectHand(Hands selected) {
		if (this.playState != Status.Wait) {
			return;
		}

		switch ((selected.getNumber() - opponHands.getNumber() + 3) % 3) {
		case 0:
			this.messageLabel.setText("あーいこーで・・・");
			this.init();
			break;

		case 1:
			this.messageLabel
					.setText(String.format("相手がだしたのは「%s」なのであなたの負けです。続けてもう一回。じゃーんけーん・・・", this.opponHands.getDisplay()));
//			this.playState = Status.Done;
			this.playState = Status.Wait;
			break;

		case 2:
			this.messageLabel
					.setText(String.format("相手が出したのは「%s」なのであなたの勝ちです。続けてもう一回。じゃーんけーん・・・", this.opponHands.getDisplay()));
//			this.playState = Status.Done;

			this.playState = Status.Wait;
			break;

		}
	}

}
