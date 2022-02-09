// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
  }

  public void lift(double speed){
    if(!Constants.intake_upper_switch.get()){
      Constants.intake_servo.set(speed);
    }else{
      stop_servo();
    }
  }
  public void lower(double speed){
    if(!Constants.intake_lower_switch.get()){
      Constants.intake_servo.set(-speed);
    }else{
      stop_servo();
    }
  }
  public void stop_servo(){
    Constants.intake_servo.set(0);
  }
  public void forward(){
    Constants.intake_motor.set(.3);
  }
  public void backward(){
    Constants.intake_motor.set(-.3);
  }
  public void stop_motor(){
    Constants.intake_motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
