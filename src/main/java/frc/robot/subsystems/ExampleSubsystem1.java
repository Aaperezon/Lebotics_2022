package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
public class ExampleSubsystem1 extends SubsystemBase {
  final VictorSP example_motor1;
  public ExampleSubsystem1(int channel) {
    example_motor1 = new VictorSP(channel);
  }
  public void runForward(double speed){
    example_motor1.set(speed);
    System.out.println("SUBSYSTEM 1 **WORKING**");
  }
  public void runBackward(double speed){
    example_motor1.set(-speed);
    System.out.println("SUBSYSTEM 1 **WORKING**");
  }

  @Override
  public void periodic() {
   
  }

}
