// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  
  private static Spark intake_servo = new Spark(8);
  private static DigitalInput intake_upper_switch = new DigitalInput(7);
  private static Spark intake_motor = new Spark(7);
  
  public IntakeSubsystem() {
  }

  public void lift(double speed){
    if(intake_upper_switch.get()){
      intake_servo.set(speed);
    }else{
      stop_servo();
    }
  }
  public void lower(double speed){
    intake_servo.set(-speed);
  }
  public void stop_servo(){
    intake_servo.set(0);
  }
  public void forward(){
    intake_motor.set(.8);
  }
  public void forward(double s){
    intake_motor.set(s);
  }
  public void backward(){
    intake_motor.set(-1);
  }
  public void stop_motor(){
    intake_motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
