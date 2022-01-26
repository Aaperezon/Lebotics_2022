package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class LeboticsController {
    XboxController joystick;
    boolean A;
    boolean B;
    boolean X;
    boolean Y;
    boolean LB;
    boolean RB;
    boolean BACK;
    boolean START;
    boolean LS;
    boolean RS;
    double LY;
    double LX;
    double RY;
    double RX;
    double LTRIGGER;
    double RTRIGGER;
  


    public LeboticsController(XboxController joystick){
        this.joystick = joystick;
    }
    public boolean getA(){
        return this.joystick.getRawButton(1);
    }

    public boolean getB(){
        return this.joystick.getRawButton(2);
    }

    public boolean getX(){
        return this.joystick.getRawButton(3);
    } 

    public boolean getY(){
        return this.joystick.getRawButton(4);
    }

    public boolean getLB(){
        return this.joystick.getRawButton(5);
    }

    public boolean getRB(){
        return this.joystick.getRawButton(6);
    }

    public boolean getBACK(){
        return this.joystick.getRawButton(7);
    }

    public boolean getSTART(){
        return this.joystick.getRawButton(8);
    }

    public boolean getLS(){
        return this.joystick.getRawButton(9);
    }

    public boolean getRS(){
        return this.joystick.getRawButton(10);
    }



    public double getLX(){
        return joystick.getRawAxis(0);
    }
    public double getLY(){
        return joystick.getRawAxis(1);
    }
    public double getLTRIGGER(){
        return joystick.getRawAxis(2);
    }
    public double getRTRIGGER(){
        return joystick.getRawAxis(3);
    }
    public double getRX(){
        return joystick.getRawAxis(4);
    }
    public double getRY(){
        return joystick.getRawAxis(5);
    }


}
