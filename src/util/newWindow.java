package util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class newWindow extends JFrame {
    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
    public newWindow() {
        setTitle("새로 띄운 창");
        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);

        setBounds(300,100,600,600);
        setResizable(false);
        setVisible(true);
    	}
    }