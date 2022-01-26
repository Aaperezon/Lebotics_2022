package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem2 extends SubsystemBase {
  final VictorSP example_motor2;
  public ExampleSubsystem2(int channel) {
    example_motor2 = new VictorSP(channel);
  }
  public void runForward(double speed){
    example_motor2.set(speed);
    System.out.println("SUBSYSTEM 2 **WORKING**");
  }
  public void runBackward(double speed){
    example_motor2.set(-speed);
    System.out.println("SUBSYSTEM 2 **WORKING**");
  }
  @Override
  public void periodic() {
  }
}
