// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
public class SeatMotorSubsystem extends SubsystemBase {
  final VictorSP seat_motor;
  public SeatMotorSubsystem(int channel) {
    seat_motor = new VictorSP(channel);
  }



  public void activateMotor(boolean state, double speed){
    if(state){
      seat_motor.set(speed);
      System.out.println("VictorSP motor in pwm: "+seat_motor.getChannel()+ "is running by button...");;

    }
  } 


  public void activateMotor(double speed){
    if(speed >= .1 || speed <= -.1){
      seat_motor.set(speed);
      System.out.println("VictorSP motor in pwm: "+seat_motor.getChannel()+ "is running by joytick...");;
    }
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
